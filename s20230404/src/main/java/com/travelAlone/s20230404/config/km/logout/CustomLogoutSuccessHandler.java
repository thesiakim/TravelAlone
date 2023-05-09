package com.travelAlone.s20230404.config.km.logout;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 2023-05-09 조경민
 * 설명 : 로그아웃시 해야될 프로세스 커스텀 클래스. 추후 삭제 , 유지 결정
 * */
@RequiredArgsConstructor
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final HttpSession httpSession;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        httpSession.removeAttribute("user");
        httpSession.invalidate();
        response.sendRedirect(request.getContextPath() + "/");
    }
}
