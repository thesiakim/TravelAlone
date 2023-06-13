package com.travelAlone.s20230404.config.km;

import com.travelAlone.s20230404.config.km.logout.CustomLogoutSuccessHandler;
import com.travelAlone.s20230404.domain.km.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 23-04-14 조경민
 *
 * @EnableWebSecurity : WebSecurityConfigurerAdapter를 상속받는 클래스에 해당 어노테이션을 선언하면 SpringSecurityFilterChain이 자동으로 포함
 * filterChain() : http 보안 설정
 * PasswordEncoder : 비밀번호 BCryptPasswordEncoder 해시 함수를 이용하여 암호화처리함. 객체 생성해서 빈으로 관리
 *
 * 2023-05-09 조경민
 * 로그인 성공 후 기존 페이지로 이동을 위한 AuthenticationSuccessHandler 설정 추가
 */

//@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true) //@Secured 어노테이션 활성화 , @preAuthorize @PostAuthorize 어노테이션 활성화
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    /**
     * 2023-04-19 조경민
     * 설명 : 비밀번호 인코더(비밀번호를 암호화한다)
     * */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 2023-04-14 조경민
     * 설명: http 설정 메서드
     *
     * 2023-04-18 조경민
     * 설명 : 46~52번째 줄 로그인, 로그아웃 설정 추가
     *
     * 2023-05-02 조경민
     * 설명 : 55번째 줄 hasAnyRole-> hasAnyAuthority 변경
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().disable().csrf().disable();// csrf, cors 기능 사용 안함.

        http
            .headers().frameOptions().disable()
            .and()
                .authorizeRequests() // URL별 권한 관리 시작
                // 문의게시판
                .antMatchers("inquireDetail","inquireWriteForm","inquireUpdateForm").hasAnyAuthority(Role.rol100.getKey(), Role.rol200.getKey())
                .antMatchers("noticeWriteForm","noticeUpdateForm","inquireReplyForm").hasAnyAuthority(Role.rol200.getKey())
                // 여숙맛
                .antMatchers("houRevWriteForm","houRevUpdateForm","resRevWriteForm","resRevUpdateForm","traRevWriteForm","traRevUpdateForm").hasAnyAuthority(Role.rol100.getKey(), Role.rol200.getKey())
                .antMatchers("resWriteForm","resUpdateForm","houWriteForm","houUpdateForm","traWriteForm","traUpdateForm").hasAnyAuthority(Role.rol100.getKey(), Role.rol200.getKey())
                // 커뮤니티
                .antMatchers("writeBoardForm","updateBoardForm","writeBoardRe","WriteBoardReLevel","UpdateBoardRe","deleteBoard","deleteBoardRe","/boardlike","reportMember").hasAnyAuthority(Role.rol100.getKey(), Role.rol200.getKey())
                // 마이페이지
                .antMatchers("myPageCommunityList","/reviewPageTra","/reviewPageRes","/reviewPageHou","/mypage/**","/api/v1/mypage/**").authenticated()
                // 관리자페이지
                .antMatchers("/api/v1/admin/**", "/admin/**").hasAnyAuthority(Role.rol200.getKey())
                .anyRequest().permitAll() // 모든 사이트는 모든 사용자 허가
            .and() // 일반 로그인 설정
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .usernameParameter("email")
                .failureUrl("/login/error")
            .and()
                .logout()
//                    .logoutSuccessHandler(customLogoutSuccessHandler)
                .logoutSuccessUrl("/");



        return http.build();
    }


    /**
     * 2023-04-17 조경민
     * 설명 : resource 자원의 권한,인증을 무시하는 메서드. filterChain에서 설정해서 사용할지 고민할것
     * */
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/assets/**", "/h2-console/**","/api/hello2"));
//    }


}
