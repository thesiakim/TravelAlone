package com.travelAlone.s20230404.config.km;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2023-04-19 조경민
 * 설명 : 컨트롤러 파라미터에서 @LoginUser MemberJpa memberJpa 라고 입력하면 로그인된 멤버를 얻을 수 있음
 *
 * Target(ElementType.PARAMETER) : 이 어노테이션이 생성될 수 있는 위치 지정. 여기선 파라미터로 지정
 * Retention(RetentionPolicy.RUNTIME) : 이 어노테이션을 런타임까지 보존
 * @interface : 이 파일을 어노테이션 클래스로 지정함. 즉 LoginUser이라는 어노테이션 생성
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login2User {


}
