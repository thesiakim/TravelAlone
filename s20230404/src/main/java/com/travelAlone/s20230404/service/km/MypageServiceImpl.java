package com.travelAlone.s20230404.service.km;

import com.travelAlone.s20230404.config.km.SessionUser;
import com.travelAlone.s20230404.dao.km.MypageDao;
import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.*;

import com.travelAlone.s20230404.model.mh.Inquire;
import com.travelAlone.s20230404.service.jh.UploadHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public int memberProfileUpdate(List<MultipartFile> pictureFile, Member member) throws Exception {
        // 이미지 저장
        BodImg bodImg = UploadHandler.parseFileInfo(pictureFile, member.getMember_id()).get(0);

        // 기존 이미지 삭제
        if (!member.getM_img_stored_file().equals("src/main/resources/static/img/user-picture.png")){
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
    public int memberProfileReset(Member member) {

        // 기존 이미지 삭제
        if (!member.getM_img_stored_file().equals("src/main/resources/static/img/user-picture.png")){
            //기본 이미지가 아닐 경우 실제 이미지 파일 삭제
            UploadHandler.delete(member.getM_img_stored_file());

        }

        // 기본 이미지 저장
        member.updateProfile("normal","userPicture","src/main/resources/static/img/user-picture.png","img300");
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
    public int memberWithdrawal(long id) {
        return mypageDao.memberWithdrawal(id);
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 마이페이지 내가쓴 리뷰 화면 보여주기
     * */
    @Override
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
    public int myPageInquireListCnt(Long memberId) {
        int myPageInquireListCnt = 0;
        log.info("mhServiceImpl myPageInquireListCnt Start...");
        myPageInquireListCnt = mypageDao.myPageInquireListCnt(memberId);
        log.info("mhServiceImpl myPageInquireListCnt -> "+ myPageInquireListCnt);

        return myPageInquireListCnt;
    }

    //마이페이지 문의내역 리스트
    @Override
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

    @Override
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


}