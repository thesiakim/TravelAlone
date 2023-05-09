package com.travelAlone.s20230404.model.dto.km;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MypageMemberWithdrawalRequestDto {
    private String memberEmail;
    private String password;

}