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
    private String passwd;
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
    public MemberJpa(String email, String nickname, String passwd, String name, String gender, String phone) {
        // 회원가입시 설정
        this.email = email;
        this.nickname = nickname;
        this.passwd = passwd;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.role = Role.rol100;

    }

    public void updateProfilePicture(){

    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }



}
