package com.travelAlone.s20230404.service.km;


import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.domain.km.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 2023-04-17 조경민
 * 설명 : 회원 관련 기능 Service
 */
@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    /**
     * 2023-04-17 조경민
     * 설명 : 유효성 검증을 마친 회원을 저장한다.
     * 반환 : 생성한 회원 id
     */
    @Transactional
    public Long save(MemberJpa memberJpa) {

        // 생성된 닉네임이 중복일 경우 자동생성
        if (memberJpa.getNickname() == null) {
            memberJpa.updateNickname(createNickname());
        }

        // 생성된 member 저장
        MemberJpa savedMemberJpa = memberRepository.save(memberJpa);

        // 저장된 member id 반환
        return savedMemberJpa.getId();
    }

    /**
     * 2023-04-17 조경민
     * 설명 : 회원가입시 유효성, 중복 검사
     * 반환 : key / valid_{dto필드명}
     * message / dto에서 작성한 messge값
     */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        // 유효성검사 실패한 필드 목록을 받음
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());

        }

        return validatorResult;
    }


    /**
     * 2023-04-17 조경민
     * 설명 : 자동생성되는 닉네임 중복확인
     * */
    @Transactional(readOnly = true)
    public String createNickname() {
        // 랜덤으로 영문 10문자 생성
        Random random = new Random();
        String generatedString;

        // 닉네임 중복확인
        do {
            generatedString = random.ints(97, 122 + 1)
                    .limit(10)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        } while (memberRepository.existsByNickname(generatedString));

        // 자동 생성 닉네임 반환
        return generatedString;
    }


}
