package com.travelAlone.s20230404.model.dto.km;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberFindAndChangePasswordRequestDto {

    private String email;
    private String name;
    private String phone;
    private String password;

    @Builder
    public MemberFindAndChangePasswordRequestDto(String email, String name, String phone, String password) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

}
