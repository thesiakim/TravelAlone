package com.travelAlone.s20230404.service.km;

import com.travelAlone.s20230404.dao.km.MypageDao;
import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.MypageResponseDto;

import com.travelAlone.s20230404.model.dto.km.MypageReviewRequestDto;
import com.travelAlone.s20230404.model.dto.km.MypageReviewResponseDto;
import com.travelAlone.s20230404.service.jh.UploadHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 2023-04-26 조경민
 * 설명 : 마이페이지 관련 serviceimpl
 * */
@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService{

    private final MypageDao mypageDao;

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


        return mypageDao.memberInfoUpdate(member);
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

        return mypageDao.memberProfileUpdate(member);
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 프로필 사진을 기본 이미지로 변경한다
     * */
    @Override
    public int memberProfileReset(Member member) {
        // 기존 이미지 삭제
        UploadHandler.delete(member.getM_img_stored_file());

        // 기본 이미지 저장
        member.updateProfile("normal","userPicture","src/main/resources/static/img/user-picture.png","img300");

        // DB 변경
        return mypageDao.memberProfileUpdate(member);

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


}
