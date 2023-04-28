package com.travelAlone.s20230404.service.km;

import com.travelAlone.s20230404.dao.km.MypageDao;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.MypageMemberInfoUpdateRequestDto;
import com.travelAlone.s20230404.model.dto.km.MypageResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


}
