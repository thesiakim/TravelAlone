package com.travelAlone.s20230404.vaildator.km;

import com.travelAlone.s20230404.domain.km.MemberRepository;
import com.travelAlone.s20230404.model.dto.km.MemberFormDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * 2023-04-17 조경민
 * 설명 : AbstractVaildator를 상속받은 email 중복 유효성검사 클래스
 *      doValidate를 구현해 검증로직 작성 및 @Component로 빈 등록
 * */
@Component
@RequiredArgsConstructor
public class CheckEmailValidator extends AbstractValidator<MemberFormDto>{

    private final MemberRepository memberRepository;


    @Override
    protected void doValidate(MemberFormDto dto, Errors errors) {

        if (memberRepository.existsByEmail(dto.getEmail())) {
            errors.rejectValue("email", "아이디 중복 오류", "이미 사용중인 아이디(이메일)입니다.");
        }
    }
}
