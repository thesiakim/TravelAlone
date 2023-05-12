package com.travelAlone.s20230404.model.dto.km;

import lombok.*;

@NoArgsConstructor
@Data
public class MemberFindIdRequestDto {

    private String name;
    private String phone;

    @Builder
    public MemberFindIdRequestDto(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
