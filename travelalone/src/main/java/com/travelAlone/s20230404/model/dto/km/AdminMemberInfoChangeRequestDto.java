package com.travelAlone.s20230404.model.dto.km;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminMemberInfoChangeRequestDto {

    private Long id;
    private String email;
    private String nickname;
    private String name;
    private String gender;
    private String phone;
    private String role;
    private String imgContext;
    private String imgOriginalFile;
    private String imgStoredFile;
    private String imagesType;


}