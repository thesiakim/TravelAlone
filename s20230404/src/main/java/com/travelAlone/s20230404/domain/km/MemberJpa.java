package com.travelAlone.s20230404.domain.km;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 2023-04-14 조경민
 * 설명 : 회원클래스. BaseTimeEntity를 상속받아 생성일 수정일 자동생성, 시퀀스 Member_ID_SEQ 사용
 * */

@Getter
@NoArgsConstructor
@SequenceGenerator(
        name="MEMBER_ID_SEQ", //시퀀스 제너레이터 이름
        sequenceName="MEMBER_ID_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
@Entity
@Table(name = "MEMBER")
public class MemberJpa extends BaseTimeEntity{
    @Id
    @Column(name = "member_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="MEMBER_ID_SEQ" //식별자 생성기를 설정해놓은  MEMBER_ID_SEQ으로 설정
            )
    private Long id;
    @Column(name = "m_email")
    private String email;
    @Column(name = "m_nickname")
    private String nickname;
    @Column(name = "m_passwd")
    private String password;
    @Column(name = "m_name")
    private String name;
    @Column(name = "m_common_gender")
    private String gender;
    @Column(name = "m_phone")
    private String phone;

    @Column(name = "m_common_role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "m_img_context")
    private String imgContext;
    @Column(name = "m_img_original_file")
    private String imgOriginalFile;
    @Column(name = "m_img_stored_file")
    private String imgStoredFile;
    @Column(name = "m_common_imagestype")
    private String imagesType;

    @Builder
    public MemberJpa(String email, String nickname, String password, String name, String gender, String phone,
                     String imgContext, String imgOriginalFile, String imgStoredFile, String imagesType) {
        // 회원가입시 설정
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.role = Role.rol100;
        this.imgContext = imgContext;
        this.imgOriginalFile = imgOriginalFile;
        this.imgStoredFile = imgStoredFile;
        this.imagesType = imagesType;

    }

    /**
     * 2023-04-20 조경민
     * 설명 : 닉네임을 변경한다
     * */
    public void updateNickname(String nickname){
        this.nickname = nickname;
    }


    /**
     * 2023-04-20 조경민
     * 설명 : 비밀번호 찾기 후 변경에서 변경하기 전에 멤버가 맞는지 확인한다
     * */
    public Boolean CheckMember(String email, String name ,String phone){
        if (this.email == email && this.name == name && this.phone == phone){

            // 정보가 맞으면 true 반환
            return true;

        }else {
            // 정보가 맞으면 false 반환
            return false;
        }
    }

    /**
     * 2023-04-20 조경민
     * 설명 : 비밀번호를 변경한다
     * */
    public void updatePassword(String password){
        this.password = password;
    }

    /**
     * 2023-05-02 조경민
     * 설명 : 권한을 변경한다
     * */
    public void updateRole(String role){
        if (role.equals("ban")){
            this.role = Role.rol300;
        }else if (role.equals("admin")){
            this.role = Role.rol200;
        }else{
            this.role = Role.rol100;
        }
    }

    /**
     * 2023-05-02 조경민
     * 설명 : 회원정보 변경
     * */
    public void updateInfo(String nickname,
                           String name,
                           String phone) {
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;

    }

    /**
     * 2023-05-06 조경민
     * 설명 : 회원 프로필 사진 변경
     * */
    public void updateProfile(String imgContext,
                           String imgOriginalFile,
                           String imgStoredFile,
                           String imagesType) {
        this.imgContext = imgContext;
        this.imgOriginalFile = imgOriginalFile;
        this.imgStoredFile = imgStoredFile;
        this.imagesType = imagesType;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}