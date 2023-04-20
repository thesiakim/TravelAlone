package com.travelAlone.s20230404.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.travelAlone.s20230404.model.dto.km.MemberFormDto;
import com.travelAlone.s20230404.service.km.MemberService;
import com.travelAlone.s20230404.vaildator.km.CheckEmailValidator;
import com.travelAlone.s20230404.vaildator.km.CheckNicknameValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class kmController {

    private final MemberService memberService;
    private final CheckEmailValidator checkEmailValidator;
    private final CheckNicknameValidator checkNicknameValidator;
    private final PasswordEncoder passwordEncoder;

    /**
     * 2023-04-17 조경민
     * 설명 : 커스텀 유효성 검증을 위해 추가
     *
     * @InitBinder : 특정 컨트롤러에서 바인딩 또는 검증 설정을 변경하고 싶을때 사용
     * WebDataBinder : Http 요청정보를 컨트롤러 메소드의 파라미터나 모델에 바인딩할 때 사용되는 바인딩 객체
     * */
    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkEmailValidator);
        binder.addValidators(checkNicknameValidator);
    }



    /**
     * 2023-04-17 조경민
     * 설명 : 메인페이지 이동
     * */
    
    /*
      @GetMapping("/")
      public String main(@LoginUser MemberJpa memberJpa, Model model){
          System.out.println(" mian ");
          
          if (memberJpa != null){
              model.addAttribute("memberId", memberJpa.getId());
              System.out.println("user.getNickName() = " + memberJpa.getNickname());
          }
          
          return "th/main";
      }
*/

    /**
     * 2023-04-17 조경민
     * 설명 : 회원가입창 이동
     * */
    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("memberDto", new MemberFormDto());

        return "th/join";
    }

    /**
     * 2023-04-17 조경민
     * 설명 : 회원가입 요청 처리 메서드
     * */
    @PostMapping("/join")
    public String joining(@Valid @ModelAttribute("memberDto") MemberFormDto requestDto, BindingResult errors, Model model){
        if (errors.hasErrors()){
            // 회원가입 실패시 입력 데이터값 유지
            model.addAttribute("memberDto", requestDto);


            // 유효성 통과 못한 필드와 메세지 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            for (String key : validatorResult.keySet()){
                model.addAttribute(key, validatorResult.get(key));
            }

            // 회원가입 페이지로 다시 리턴
            return "th/join";

        }

        // 검사에 이상이 없을경우 암호화 하여 저장
        memberService.save(requestDto.toEntity(passwordEncoder));

        return "redirect:/login";
    }


    /**
     * 2023-04-19 조경민
     * 설명 : 로그인 페이지 이동
     * */
    @GetMapping("/login")
    public String Login(){

        return "th/login";
    }

    /**
     * 2023-04-19 조경민
     * 설명 : 로그인 처리 에러시 표시될 메세지 출력
     * */

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "th/login";
    }
}
