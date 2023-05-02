package com.travelAlone.s20230404.service.km;


import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.domain.km.MemberRepository;
import com.travelAlone.s20230404.model.dto.km.*;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;
import java.util.stream.Collectors;

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
    public Long save(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {

        // MemberJpa 객체 생성
        MemberJpa memberJpa = memberFormDto.toEntity(passwordEncoder);


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

    /**
     * 2023-04-20 조경민
     * 설명 : email들을 name과 phone을 이용해서 찾아온다
     * */
    @Transactional(readOnly = true)
    public List<String> findEmailByNameAndPhone(MemberFindIdRequestDto requestDto) {

        // 조건에 맞는 memberJpa들을 찾아오고 매핑해서 email List로 만듬
        return memberRepository.findEmailByNameAndPhone(requestDto.getName(), requestDto.getPhone())
                .stream().map(member -> member.getEmail()).collect(Collectors.toList());



    }


    /**
     * 2023-04-20 조경민
     * 설명 : 해당하는 member의 id를 가져온다
     * */
    @Transactional(readOnly = true)
    public Long findMemberIdByEmailNamePhone(MemberFindPasswordRequestDto requestDto) {

        // 조건에 맞는 momberJpa를 찾아옴
        MemberJpa memberJpa = memberRepository.findMemberIdByEmailNamePhone(requestDto.getEmail(), requestDto.getEmail(), requestDto.getPhone())
                .orElse(null);


        if (memberJpa !=null){

            // 조건에 맞는 회원이 있다면 id 반환
            return memberJpa.getId();

        }else {

            // 없다면 null 반환
            return null;
        }
    }


    /**
     * 2023-04-20 조경민
     * 설명 : 비밀번호 찾기 후 변경화면에서 변경하기
     * */
    @Transactional
    public Long checkMemberAndChangePassword(Long id, MemberFindAndChangePasswordRequestDto requestDto, PasswordEncoder passwordEncoder) {

        // 제대로 된 접근이 맞는지 member 찾아오기
        MemberJpa member = memberRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 유저가 없습니다"));

        // member 확인
        if (member.CheckMember(requestDto.getEmail(), requestDto.getName(), requestDto.getPhone())){

            // 비밀번호 암호화 하여 저장
            member.updatePassword(passwordEncoder.encode(requestDto.getPassword()));

        }

        return member.getId();
    }

    /**
     * 2023-05-02 조경민
     * 설명 : 관리자 페이지 메인화면 회원 리스트 조회
     * */
    @Transactional(readOnly = true)
    public List<AdminMemberResponseDto> adminMemberListShow() {

        // 멤버 리스트를 조회해와서 AdminMemberResponseDto로 변환 후 반환
        return memberRepository.findAllDesc().stream()
                .map(memberJpa -> new AdminMemberResponseDto(memberJpa))
                .collect(Collectors.toList());

    }

    /**
     * 2023-05-02 조경민
     * 설명 : 관리자 페이지 검색 회원 리스트 조회
     * */
    @Transactional(readOnly = true)
    public List<AdminMemberResponseDto> adminMemberSearchAndListShow(String search) {

        return memberRepository.findSearchAndAllDesc(search).stream()
                .map(memberJpa -> new AdminMemberResponseDto(memberJpa))
                .collect(Collectors.toList());
    }

    /**
     * 2023-05-02 조경민
     * 설명 : 관리자 페이지 회원 권한 변경
     * */
    @Transactional
    public Long adminMemberRoleChange(AdminMemberRoleRequestDto requestDto) {

        // 해당 아이디 member 조회
        MemberJpa memberJpa = memberRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalStateException("해당 회원이 존재하지 않습니다. : " + requestDto.getId()));

        // member 권한 변경
        memberJpa.updateRole(requestDto.getRole());


        return memberJpa.getId();
    }

    /**
     * 2023-05-02 조경민
     * 설명 : 관리자 페이지 회원 정보 변경
     * */
    @Transactional
    public Long adminMemberinfoChange(AdminMemberInfoChangeRequestDto requestDto) {
        // 해당 아이디 member 조회
        MemberJpa memberJpa = memberRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalStateException("해당 회원이 존재하지 않습니다. : " + requestDto.getId()));

        // member 정보 변경
        memberJpa.updateInfo(requestDto.getNickname(),
                requestDto.getName(),
                requestDto.getGender(),
                requestDto.getPhone(),
                requestDto.getImgContext(),
                requestDto.getImgOriginalFile(),
                requestDto.getImgStoredFile(),
                requestDto.getImagesType());

        return memberJpa.getId();

    }
}
