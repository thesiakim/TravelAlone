package com.travelAlone.s20230404.vaildator.km;

import com.travelAlone.s20230404.domain.km.MemberRepository;
import com.travelAlone.s20230404.model.dto.km.MemberFormDto;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.Errors;
import org.springframework.stereotype.Component;

/**
 * 2023-04-17 조경민
 * 설명 : AbstractVaildator를 상속받은 Nickname 중복 유효성검사 클래스
 *      doValidate를 구현해 검증로직 작성 및 @Component로 빈 등록
 * */


@RequiredArgsConstructor
@Component
public class CheckNicknameValidator extends AbstractValidator<MemberFormDto>{

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberFormDto dto, Errors errors) {

        if (memberRepository.existsByNickname(dto.getNickName())) {
            errors.rejectValue("nickName", "닉네임 중복 오류", "이미 사용중인 닉네임입니다.");
        }
    }
}
