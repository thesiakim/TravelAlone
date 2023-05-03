package com.travelAlone.s20230404.config.km;

import com.travelAlone.s20230404.domain.km.MemberJpa;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

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

    public SessionUser(MemberJpa memberJpa) {
        this.id = memberJpa.getId();
        this.email = memberJpa.getEmail();
        this.nickname = memberJpa.getNickname();
        this.password = memberJpa.getPassword();
        this.name = memberJpa.getName();
        this.gender = memberJpa.getGender();
        this.phone = memberJpa.getPhone();
        this.role = memberJpa.getRole().toString();
        this.imgContext = memberJpa.getImgContext();
        this.imgOriginalFile = memberJpa.getImgOriginalFile();
        this.imgStoredFile = memberJpa.getImgStoredFile();
        this.imagesType = memberJpa.getImagesType();
    }
}
