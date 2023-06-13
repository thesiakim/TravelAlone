package com.travelAlone.s20230404.service.km;

import com.travelAlone.s20230404.config.km.SessionUser;
import com.travelAlone.s20230404.dao.km.MypageDao;
import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.*;

import com.travelAlone.s20230404.model.dto.km.MypageReviewRequestDto;
import com.travelAlone.s20230404.model.dto.km.MypageReviewResponseDto;
import com.travelAlone.s20230404.model.dto.km.UserPageResponseDto;

import com.travelAlone.s20230404.model.mh.Inquire;

import com.travelAlone.s20230404.service.board.UploadHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2023-04-26 조경민
 * 설명 : 마이페이지 관련 serviceimpl
 * */
@Slf4j
@RequiredArgsConstructor
@Service
public class MypageServiceImpl implements MypageService{

    private final MypageDao mypageDao;
    private final HttpSession httpSession;

    /**
     * 2023-04-26 조경민
     * 설명 : 마이페이지에서 보여주는 정보들을 가져온다
     * */
    @Override
    @Transactional(readOnly = true)
    public MypageResponseDto mypageMain(long memberId) {

        MypageResponseDto responseDto = new MypageResponseDto();

        responseDto.addInterest(mypageDao.interestList(memberId));

        responseDto.addScoreCount(mypageDao.totalScore(memberId));

        responseDto.addWritingCount(mypageDao.wirtingCount(memberId));


        return responseDto;
    }

    /**
     * 2023-04-26 조경민
     * 설명 : 마이페이지 회원정보 변경
     * */
    @Override
    @Transactional
    public int memberInfoUpdate(Member member) {
        // 회원정보 업데이트
        int updateResult = mypageDao.memberInfoUpdate(member);

        // 세션 회원정보 업데이트
        if (!memberInfoRefresh(member.getMember_id())){
            throw new IllegalArgumentException("회원정보가 세션에 정상적으로 저장되지 않았습니다. id : " + member.getMember_id());
        }

        return updateResult;
    }


    /**
     * 2023-05-01 조경민
     * 설명 : 마이페이지 회원 사진 변경
     *
     * */
    @Override
    @Transactional
    public int memberProfileUpdate(List<MultipartFile> pictureFile, Member member) throws Exception {
        // 이미지 저장
        BodImg bodImg = UploadHandler.parseFileInfo(pictureFile, member.getMember_id()).get(0);

        // 기존 이미지 삭제
        if (!member.getM_img_stored_file().equals("src/main/resources/static/img/gosunee.png")
                ||!member.getM_img_stored_file().equals("src/main/resources/static/img/godoree.png")){
            //기본 이미지가 아닐 경우 실제 이미지 파일 삭제
            UploadHandler.delete(member.getM_img_stored_file());

        }

        // DB 이미지 정보 변경
        member.updateProfile(bodImg.getImg_context(), bodImg.getImg_original_file(), bodImg.getImg_stored_file(),bodImg.getCommon_imagesType());
        int updateResult = mypageDao.memberProfileUpdate(member);

        // 세션 정보 변경
        if (!memberInfoRefresh(member.getMember_id())){
            throw new IllegalArgumentException("회원정보가 세션에 정상적으로 저장되지 않았습니다. id : " + member.getMember_id());
        }

        return updateResult;
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 프로필 사진을 기본 이미지로 변경한다
     * */
    @Override
    @Transactional
    public int memberProfileReset(Member member) {
    	String gosunee = "src/main/resources/static/img/gosunee.png";
    	String godoree = "src/main/resources/static/img/godoree.png";
    	
    	
        // 기존 이미지 삭제
        if (!member.getM_img_stored_file().equals(gosunee)||!member.getM_img_stored_file().equals(godoree)){
            //기본 이미지가 아닐 경우 실제 이미지 파일 삭제
            UploadHandler.delete(member.getM_img_stored_file());

        }

        // 기본 이미지 저장
        if(member.getM_common_gender().equals("0")) {
        	member.updateProfile("normal","userPicture",godoree,"img300");
        }else {
        	member.updateProfile("normal","userPicture",gosunee,"img300");
        }
        
        
        int updateResult = mypageDao.memberProfileUpdate(member);

        // 세션 정보 변경
        if (!memberInfoRefresh(member.getMember_id())){
            throw new IllegalArgumentException("회원정보가 세션에 정상적으로 저장되지 않았습니다. id : " + member.getMember_id());
        }

        // DB 변경
        return updateResult;

    }

    /**
     * 2023-05-01 조경민
     * 설명 : 마이페이지 내 회원탈퇴
     * */
    @Override
    @Transactional
    public int memberWithdrawal(long id, String profileImg) {

        // 모든 사진 테이블 id를 이용해 조회 후 삭제
        List<ImgDto> imgDtos = mypageDao.memberAllImgSearchForWithdrawal(id);

        for (ImgDto imgDto : imgDtos) {
            UploadHandler.delete(imgDto.getImg_stored_file());
        }

        // 사진테이블에서 삭제
        int i = mypageDao.deleteMemberAllImgForWithdrawal(id);

        // 프로필 사진 삭제
        if (!profileImg.equals("src/main/resources/static/img/gosunee.png")
                ||!profileImg.equals("src/main/resources/static/img/godoree.png")){
            //기본 이미지가 아닐 경우 실제 이미지 파일 삭제
            UploadHandler.delete(profileImg);
        }
        // 나머지 테이블 데이터 삭제
        return mypageDao.memberWithdrawal(id);
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 마이페이지 내가쓴 리뷰 화면 보여주기
     * */
    @Override
    @Transactional(readOnly = true)
    public List<MypageReviewResponseDto> mypageReviewShow(MypageReviewRequestDto requestDto) {

        return mypageDao.mypageReviewShow(requestDto);
    }

    /**
     * 2023-05-03 조경민
     * 설명 : 마이페이지 내 회원정보 수정 후 세션에 갱신하여 저장
     * */
    @Override
    @Transactional
    public boolean memberInfoRefresh(long memberId) {

        Member member = mypageDao.memberInfo(memberId);

        if (member ==null){

            return false;

        }else {

            httpSession.setAttribute("user", new SessionUser(member));

            return true;
        }
    }
    
    // 누락 푸쉬
	@Override
    @Transactional
	public UserPageResponseDto userPage(long member_id) {
		UserPageResponseDto userPageResponseDto = new UserPageResponseDto();
		
		// 관심사 불러오기
		userPageResponseDto.addInterest(mypageDao.interestList(member_id));
		// 받은 점수 불러오기
		userPageResponseDto.addScoreCount(mypageDao.totalScore(member_id));
		// 멤버정보 불러오기
		userPageResponseDto.addMemberInfo(mypageDao.memberInfo(member_id));
		
		return userPageResponseDto;
	}

    //마이페이지 문의내역 리스트 개수 및 페이지확인
    @Override
    @Transactional(readOnly = true)
    public int myPageInquireListCnt(Long memberId) {
        int myPageInquireListCnt = 0;
        log.info("mhServiceImpl myPageInquireListCnt Start...");
        myPageInquireListCnt = mypageDao.myPageInquireListCnt(memberId);
        log.info("mhServiceImpl myPageInquireListCnt -> "+ myPageInquireListCnt);

        return myPageInquireListCnt;
    }

    //마이페이지 문의내역 리스트
    @Override
    @Transactional(readOnly = true)
    public List<Inquire> myPageInquireList(Inquire inquire) {
        List<Inquire> myPageInquireList = null;
        log.info("mhServiceImpl myPageInquireList Start...");
        myPageInquireList = mypageDao.myPageInquireList(inquire);
        log.info("mhServiceImpl myPageInquireList size -> "+ myPageInquireList.size());

        return myPageInquireList;
    }

    /**
     * 2023-05-10 조경민
     * 설명 : 마이페이지 즐겨찾기 불러오기
     * */
    @Override
    @Transactional(readOnly = true)
    public List<MypageFavoriteResponseDto> mypageFavorites(Long id, String category, int page) {

        // 페이징 처리를 위한 가져올 첫번째 게시글 번호 산출
        int startNum = (page - 1) * 10;

        // 리턴할 변수 선언
        List<MypageFavoriteResponseDto> mypageFavoriteResponseDtos;

        // 카테고리 별 매퍼 실행
        if (category.equals("hou")){
             mypageFavoriteResponseDtos = mypageDao.kmMypageFavoritesHou(id, startNum);
        }else if(category.equals("res")){
            mypageFavoriteResponseDtos = mypageDao.kmMypageFavoritesRes(id, startNum);
        }else{
            mypageFavoriteResponseDtos = mypageDao.kmMypageFavoritesTra(id, startNum);
        }


        // 리스트 리턴
        return mypageFavoriteResponseDtos;
    }

    /**
     * 2023-05-13 조경민
     * 설명 : 마이페이지 내 즐겨찾기 페이징 처리를 위한 total 갯수 불러오기
     * */
    @Override
    @Transactional(readOnly = true)
    public int mypageFavoritesPageCount(Long id, String category) {
        int totalCount;
        if (category.equals("hou")){
            totalCount = mypageDao.kmMypageFavoritesCountHou(id);
        }else if(category.equals("res")){
            totalCount = mypageDao.kmMypageFavoritesCountRes(id);
        }else{
            totalCount = mypageDao.kmMypageFavoritesCountTra(id);
        }
        return totalCount;
    }


    /**
     * 2023-05-13 조경민
     * 설명 : 마이페이지 내 관심사 페이지 불러오기
     * */
    @Override
    @Transactional
    public MypageTagResponseDto mypageInterest(Long id) {

        return mypageDao.mypageInterest(id);
    }


    /**
     * 2023-05-13 조경민
     * 설명 : 마이페이지 내 관심사 설정
     * */
    @Override
    public int mypageInterestUpdate(MypageInterestUpdateRequestDto requestDto) {

        List<String> requestDtoMapping = requestDto.getSavedTagIds().stream()
                .map(tagId -> "int" + tagId.substring(tagId.length() - 3)).collect(Collectors.toList());

        requestDto.setSavedTagIds(requestDtoMapping);

        return mypageDao.mypageInterestUpdate(requestDto);
    }


}