package com.travelAlone.s20230404.config.km;

import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.Member;
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

    public SessionUser(Member member) {
        this.id = member.getMember_id();
        this.email = member.getM_email();
        this.nickname = member.getM_nickname();
        this.password = member.getM_password();
        this.name = member.getM_name();
        this.gender = member.getM_common_gender();
        this.phone = member.getM_phone();
        this.role = member.getM_common_role();
        this.imgContext = member.getM_img_context();
        this.imgOriginalFile = member.getM_img_original_file();
        this.imgStoredFile = member.getM_img_stored_file();
        this.imagesType = member.getM_common_imagestype();
    }
}
