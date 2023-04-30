package com.travelAlone.s20230404.service.km;

import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.MypageMemberInfoUpdateRequestDto;
import com.travelAlone.s20230404.model.dto.km.MypageResponseDto;

public interface MypageService {

    public MypageResponseDto mypageMain(long memberId);

    public int memberInfoUpdate(Member member);

}
