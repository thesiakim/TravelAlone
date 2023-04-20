package com.travelAlone.s20230404.model.dto.km;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberFindPasswordRequestDto {
    private String email;
    private String name;
    private String phone;


    @Builder
    public MemberFindPasswordRequestDto(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

}
