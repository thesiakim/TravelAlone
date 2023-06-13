package com.travelAlone.s20230404.model.dto.km;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MypageMemberInfoUpdateRequestDto {

    long id;
    String email;
    String name;
    String nickname;
    String phone;

    public void addMemberId(long id){
        this.id = id;
    }
}
