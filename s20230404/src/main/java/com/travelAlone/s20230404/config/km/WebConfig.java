package com.travelAlone.s20230404.config.km;
import com.travelAlone.s20230404.config.km.login.CustomHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 2023-05-03 조경민
 * 설명 : LoginUserArgumentResolver가 스프링에서 인식될 수 있도록 WebMvcConfigurer에 추가
 *
 * 2023-05-09 조경민
 * 설명 : customHandlerInterceptor가 스프링에서 인식될 수 있도록 추가.
 *      "/css/*","/js/*","/img/*","/display" 는 인터셉터가 작동하지 않도록 기재
 **/

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;
    private final CustomHandlerInterceptor customHandlerInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customHandlerInterceptor).excludePathPatterns("/css/*","/js/*","/img/*","/display");
    }
}

