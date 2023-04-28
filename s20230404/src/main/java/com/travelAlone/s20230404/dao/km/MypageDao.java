package com.travelAlone.s20230404.dao.km;

import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.MypageMemberInfoUpdateRequestDto;
import com.travelAlone.s20230404.model.Interest;
import com.travelAlone.s20230404.model.dto.km.ScoreCount;
import com.travelAlone.s20230404.model.dto.km.WritingCount;

import java.util.List;

public interface MypageDao {

    List<Interest> interestList(long memberId);

    List<ScoreCount> totalScore(long memberId);

    WritingCount wirtingCount(long memberId);

    int memberInfoUpdate(Member member);
}
