package com.travelAlone.s20230404.config.km.Dto;

import com.travelAlone.s20230404.config.km.SessionUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.domain.km.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    /**
     * 2023-04-18 조경민
     * 설명 : 로그인, 로그아웃기능 구현 위한 메소드. UserDetailService 구현하면 오버라이딩됨
     * */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberJpa memberJpa = memberRepository.findByEmail(email).orElse(null);

        // 회원이 없으면 예외 처리
        if (memberJpa == null) {
            throw new UsernameNotFoundException(email);
        }

        httpSession.setAttribute("user", new SessionUser(memberJpa));

        return new CustomSecurityUser(memberJpa);

    }
}
