package com.travelAlone.s20230404.model.dto.km;

import com.travelAlone.s20230404.domain.km.MemberJpa;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminMemberResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private String name;
    private String gender;
    private String phone;

    private String role;
    private String imgContext;
    private String imgOriginalFile;
    private String imgStoredFile;
    private String imagesType;
    private String createdDate;
    private String modifiedDate;

    public AdminMemberResponseDto(MemberJpa memberJpa) {
        this.id = memberJpa.getId();
        this.email = memberJpa.getEmail();
        this.nickname = memberJpa.getNickname();
        this.password = memberJpa.getPassword();
        this.name = memberJpa.getName();
        this.phone = memberJpa.getPhone();
        this.role = memberJpa.getRole().toString();
        this.imgContext = memberJpa.getImgContext();
        this.imgOriginalFile = memberJpa.getImgOriginalFile();
        this.imgStoredFile = memberJpa.getImgStoredFile();
        this.imagesType = memberJpa.getImagesType();
        this.createdDate = memberJpa.getCreatedDate().toString();
        this.modifiedDate = memberJpa.getModifiedDate().toString();

        // 성별 보여주기
        if (memberJpa.getGender().equals("1")){
            this.gender = "여자";
        }else {
            this.gender = "남자";
        }

    }

}