package com.travelAlone.s20230404.config.km.login;

import com.travelAlone.s20230404.config.km.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 2023-05-09 조경민
 * 설명 : 헤더에 로그인 정보를 표시하기 위한 인터셉터 추가. 컨트롤러에서 작동 후 실행되는 postHandle만 오버라이딩
 * */
@Component
@RequiredArgsConstructor
public class CustomHandlerInterceptor implements HandlerInterceptor {
    private final HttpSession httpSession;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
       

        if (user != null) {
            if (modelAndView != null){
                modelAndView.addObject("user", user);
                
            } else {
                modelAndView = new ModelAndView();
                modelAndView.addObject("user", user);
                
            }
        }

    }

}
