package com.travelAlone.s20230404.config.km.Dto;

import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.domain.km.Role;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

/**
 * 2023-04-19 조경민
 * 설명 : Session에서 로그인정보를 간편하게 불러오기 위한 객체
 *      메서드 파라미터에 @LoginUser 메서드를 입력하여 가져올 수 있음
 *      Member 클래스를 사용하지 않은 이유는 회원 테이블을 보호하기 위함.
 * */
@Getter
public class CustomSecurityUser extends User implements Serializable {

    private MemberJpa memberJpa;

    public CustomSecurityUser(MemberJpa memberJpa){
        super(memberJpa.getEmail(), memberJpa.getPassword(),
                List.of(new SimpleGrantedAuthority(memberJpa.getRoleKey())));

        this.memberJpa = memberJpa;
    }

}
