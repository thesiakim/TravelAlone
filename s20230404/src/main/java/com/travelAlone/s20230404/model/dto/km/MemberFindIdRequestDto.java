package com.travelAlone.s20230404.model.dto.km;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberFindIdRequestDto {

    private String name;
    private String phone;

    @Builder
    public MemberFindIdRequestDto(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

}
