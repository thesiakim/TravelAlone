package com.travelAlone.s20230404.service.km;

import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.domain.km.MemberRepository;
import com.travelAlone.s20230404.model.dto.km.MemberFormDto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public MemberFormDto createMember() {
        MemberFormDto memberFormDto = MemberFormDto.builder()
                .email("test@example.com")
                .password("test pw")
                .name("test name")
                .gender("0")
                .phone("01012345678")
                .build();

        return memberFormDto;
    }


    @Test
    public void 회원가입(){
        MemberFormDto memberFormDto = createMember();

        Long savedMemberId = memberService.save(memberFormDto,passwordEncoder);

        MemberJpa savedMemberJpa = memberRepository.findById(savedMemberId)
                .orElse(null);

        Assertions.assertThat(savedMemberJpa.getEmail()).isEqualTo(memberFormDto.getEmail());
        Assertions.assertThat(savedMemberJpa.getName()).isEqualTo(memberFormDto.getName());
        Assertions.assertThat(savedMemberJpa.getGender()).isEqualTo(memberFormDto.getGender());
        Assertions.assertThat(savedMemberJpa.getPhone()).isEqualTo(memberFormDto.getPhone());
        Assertions.assertThat(savedMemberJpa.getNickname()).isNotBlank();
    }


}