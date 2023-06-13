package com.travelAlone.s20230404.model.dto.km;

import com.travelAlone.s20230404.domain.km.MemberJpa;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 2023-04-17 조경민
 * 설명: 회원가입시 필요한 데이터 유효성 검사, DTO 역할
 *      entity -> dto : of() 메서드로 정의
 *      dto -> entity : toEntity() 메서드로 정의
 * */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberFormDto {

    // 아이디는 이메일 형식으로 받음
    @NotEmpty(message = "이메일 아이디는 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;


    // 닉네임을 입력할 경우 아래조건과 같음. 입력하지 않으면 랜덤으로 10문자 닉네임 생성
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String nickName;

    // 비밀번호는 아래 조건과 같음
    @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
    @Length(min = 6, max = 16, message = "비밀번호는 6자 이상, 16자 이하로 입력해주세요")
    private String password;


    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "성별은 필수 입력값입니다.")
    private String gender;

    // 전화번호는 - 를 제외하고 10~11자리 숫자만 입력 가능
    @NotBlank(message = "전화번호는 필수 입력값입니다.")
    @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
    private String phone;

    @Builder
    public MemberFormDto(String email, String nickName, String password, String name, String gender, String phone) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }


    public MemberJpa toEntity(PasswordEncoder passwordEncoder){
    	String imgSrc;
    	if(this.gender=="0") {
    		imgSrc = "src/main/resources/static/img/gosunee.png";
    	}else {
    		imgSrc = "src/main/resources/static/img/godoree.png";
    	}
    	
        return MemberJpa.builder().email(this.email)
                .nickname(this.nickName)
                .password(passwordEncoder.encode(this.password))
                .name(this.name)
                .gender(this.gender)
                .phone(this.phone)
                .imgContext("normal")
                .imagesType("img300")
                .imgOriginalFile("userPicture")
                .imgStoredFile(imgSrc)
                .build();
    }

}
