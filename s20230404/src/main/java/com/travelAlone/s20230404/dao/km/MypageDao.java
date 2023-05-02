package com.travelAlone.s20230404.dao.km;

import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.*;
import com.travelAlone.s20230404.model.Interest;

import java.util.List;

public interface MypageDao {

    List<Interest> interestList(long memberId);

    List<ScoreCount> totalScore(long memberId);

    WritingCount wirtingCount(long memberId);

    int memberInfoUpdate(Member member);

    int memberProfileUpdate(Member member);

    int memberWithdrawal(long id);

    List<MypageReviewResponseDto> mypageReviewShow(MypageReviewRequestDto requestDto);
}
