package com.travelAlone.s20230404.vaildator.km;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import java.util.List;
import java.util.Set;

/**
 * 2023-04-17 조경민
 * 설명 : 기존 유효성 검사로 중복 검사까진 불가능. 이메일과 닉네임 중복검사를 하기 위해 커스텀 유효성 validator 생성
 * supports() : 요구하는 클래스가 맞으면 true
 * SuppressWarnings("unchecked") : 컴파일러에서 경고하지 않도록 하기 위해 사용
 * */
@Slf4j
public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void validate(Object target, Errors errors) {
        try {

            doValidate((T) target, errors);

        } catch (RuntimeException e){

            log.error("중복검증 에러",e);
            throw e;
        }
    }

    protected abstract void doValidate(final T dto, final Errors errors);

}
