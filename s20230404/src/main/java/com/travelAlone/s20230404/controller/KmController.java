package com.travelAlone.s20230404.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.*;
import com.travelAlone.s20230404.service.km.MypageService;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.travelAlone.s20230404.service.km.MemberService;
import com.travelAlone.s20230404.vaildator.km.CheckEmailValidator;
import com.travelAlone.s20230404.vaildator.km.CheckNicknameValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class KmController {

    private final MemberService memberService;
    private final CheckEmailValidator checkEmailValidator;
    private final CheckNicknameValidator checkNicknameValidator;
    private final PasswordEncoder passwordEncoder;
    private final MypageService mypageService;


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
     * 설명 : 회원가입창 이동
     * */
    @GetMapping("/join")
    public String goJoin(Model model){
        model.addAttribute("memberDto", new MemberFormDto());

        return "th/join";
    }

    /**
     * 2023-04-17 조경민
     * 설명 : 회원가입 요청 처리 메서드
     * */
    @PostMapping("api/v1/join")
    public String join(@Valid @ModelAttribute("memberDto") MemberFormDto requestDto, BindingResult errors, Model model){
        if (errors.hasErrors()){
            // 회원가입 실패시 입력 데이터값 유지
            model.addAttribute("memberDto", requestDto);


            // 유효성 통과 못한 필드와 메세지 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            // 반복문 이용하여 애러메세지 담기
            for (String key : validatorResult.keySet()){
                model.addAttribute(key, validatorResult.get(key));
            }

            // 회원가입 페이지로 다시 리턴
            return "th/join";

        }

        // 검사에 이상이 없을경우 암호화 하여 저장
        memberService.save(requestDto,passwordEncoder);

        return "redirect:/login";
    }


    /**
     * 2023-04-19 조경민
     * 설명 : 로그인 페이지 이동
     * */
    @GetMapping("/login")
    public String goLogin(){

        return "th/login";
    }

    /**
     * 2023-04-19 조경민
     * 설명 : 로그인 처리 에러시 표시될 메세지 출력
     * */
    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "th/login";
    }


    /**
     * 2023-04-20 조경민
     * 설명 : 아이디 찾기 화면 이동
     * */
    @GetMapping("/id")
    public String goFindId() {

        return "find-id";
    }


    /**
     * 2023-04-20 조경민
     * 설명 : form 이용하여 이름과 휴대폰번호를 전달, 가입 이메일을 찾아온다
     *      전달값 : String name , String phone
     *      반환값 : String email
     * */
    @PostMapping("api/v1/id")
    public String findId(@ModelAttribute MemberFindIdRequestDto requestDto, Model model){

        // 이메일을 찾아온다
        List<String> findEmails = memberService.findEmailByNameAndPhone(requestDto);

        // 비어있다면 error 모델에 받아 다시 페이지에 출력
        if (findEmails == null || findEmails.isEmpty()){
            model.addAttribute("error", "조건에 맞는 아이디가 없습니다.");
            return "find-id";
        }

        // 모델에 담아준다
        model.addAttribute("email", findEmails);

        // 찾은 id 조회페이지로 이동
        return "checking-id";
    }

    /**
     *  2023-04-20 조경민
     *  설명 : 비밀번호 찾기 화면 이동
     * */
    @GetMapping("/password")
    public String goFindPassword(){

        return "find-password";
    }

    /**
     * 2023-04-20 조경민
     * 설명 : form 이용하여 이메일, 이름과 휴대폰번호를 전달, 해당하는 유저를 찾고 id를 반환하여 비밀번호 변경 페이지로 이동한다
     *      전달값 : String email, String name , String phone
     *      반환값 : Long id, String email, String name, String phone ,아이디 존재 여부
     * */
    @GetMapping("/password/info")
    public String findPassword(@ModelAttribute MemberFindPasswordRequestDto requestDto, Model model){

        // 해당하는 member id 를 찾아온다
        Long memberId = memberService.findMemberIdByEmailNamePhone(requestDto);

        if (memberId == null){

            //찾아온 memberId가 null이라면 다시 페이지 반환
            return "find-password";

        }else {

            // 있다면 정보를 담아 비밀번호 변경창으로 이동
            model.addAttribute("info", requestDto);
            model.addAttribute("memberId", memberId);

            return "change-password";
        }
    }

    /**
     * 2023-04-20 조경민
     * 설명 : 비밀번호 찾기 후 변경 페이지에서 정보들을 받아 확인하고 변경한다(ajax 사용)
     * */
    @PostMapping("api/v1/password/{id}")
    public String changePassword(@PathVariable Long id,@RequestBody MemberFindAndChangePasswordRequestDto requestDto){

        memberService.checkMemberAndChangePassword(id, requestDto, passwordEncoder);

        return "th/login";
    }

    //마이페이지 Controller (MyBatis 사용)---------------------------------------------------------------

    /**
     * 2023-04-24 조경민
     * 설명 : 마이페이지 정보 불러오기
     * */
    @GetMapping("/mypage")
    public String mypageMain(@LoginUser MemberJpa memberJpa, Model model){

        MypageResponseDto responseDto = mypageService.mypageMain(memberJpa.getId());
        responseDto.addMemberInfo(memberJpa);

        model.addAttribute("response", responseDto);


        return "km/mypage";
    }

    /**
     * 2023-04-26 조경민
     * 설명 : 마이페이지 회원정보 수정창 띄우기
     * */
    @GetMapping("/mypage/member-info")
    public String mypageMemberInfo(@LoginUser MemberJpa memberJpa, Model model){
        model.addAttribute("email", memberJpa.getEmail());
        model.addAttribute("name", memberJpa.getName());
        model.addAttribute("nickName", memberJpa.getNickname());
        model.addAttribute("phone", memberJpa.getPhone());

        return "km/mypage-member-info";
    }

    /**
     * 2023-04-26 조경민
     * 설명 : 마이페이지 회원정보 변경
     * */
    @PostMapping("/api/v1/mypage/info")
    @ResponseBody
    public String mypageMemberInfoUpdate(@RequestBody Member member, @LoginUser MemberJpa memberJpa){

        member.setMember_id(memberJpa.getId());


        mypageService.memberInfoUpdate(member);

        return "성공";
    }

    /**
     * 2023-04-26 조경민
     * 설명 : 마이페이지 프로필 사진 수정
     * */
    @PostMapping("/api/v1/mypage/profile")
    public String mypageMemberProfileUpdate(List<MultipartFile> pictureFile, @LoginUser MemberJpa memberJpa) throws Exception {

        Member member = new Member();
        member.of(memberJpa);

        mypageService.memberProfileUpdate(pictureFile, member);

        return "/mypage";
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 마이페이지 프로필 사진 기본으로 변경
     * */
    @PostMapping("/api/v1/mypage/profile/normal")
    public String mypageMemberProfileReset(@LoginUser MemberJpa memberJpa){

        Member member = new Member();
        member.of(memberJpa);

        mypageService.memberProfileReset(member);
        return "/mypage";
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 회원 탈퇴
     * */
    @DeleteMapping("/api/v1/mypage/withdrawal")
    public String mypageMemberWithdrawal(@ModelAttribute String password, @LoginUser MemberJpa memberJpa, Model model){

        if (passwordEncoder.matches(password,memberJpa.getPassword())){
            mypageService.memberWithdrawal(memberJpa.getId());

            return "redirect:/logout";
        }else {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "memberWithDrawal";
        }

    }

    /**
     * 2023-05-01 조경민
     * 설명 : 작성 리뷰(종류별) 가져오기
     * category는 주소창으로, page는 쿼리스트링으로
     * category : {travel, house, restaurant}
     * */
    @GetMapping("/mypage/review/{category}")
    public String mypageReview(@PathVariable(required = false) String category,
                               @RequestParam(required = false) Integer page,
                               @LoginUser MemberJpa memberJpa,
                               Model model){
        if (category == null){
            category = "travel";
        }

        if (page == null){
            page = 1;
        }
        List<MypageReviewResponseDto> responseDtos =
                mypageService.mypageReviewShow(new MypageReviewRequestDto(memberJpa.getId(), category, page));

        model.addAttribute("category", category);
        model.addAttribute("page", page);
        model.addAttribute("responseDtos", responseDtos);

        return "mypage-review";
    }



    // 관리자 페이지----------------------------------------------------------------
    /**
     * 2023-05-01 조경민
     * 설명 : 관리자 페이지 회원 목록 조회, 쿼리 스트링을 이용한 검색기능 추가
     * */
    @GetMapping("/admin")
    public String adminMain(@RequestParam(value = "search", required = false) String search, Model model){

        if (search==null){
            // search 값이 null이면 전체 조회
            model.addAttribute("members",memberService.adminMemberListShow());
        }else {
            // search값 존재하면 검색어 조회
            model.addAttribute("members",memberService.adminMemberSearchAndListShow(search));
        }

        return "admin-main";
    }

    /**
     * 2023-05-02 조경민
     * 설명 : 관리자 페이지 회원 권한 변경
     * */
    @PatchMapping("/api/v1/admin/role")
    @ResponseBody
    public Long adminRoleChange(@RequestBody AdminMemberRoleRequestDto requestDto){

        // 회원 권한 변경 후 아이디 반환
        return memberService.adminMemberRoleChange(requestDto);
    }

    /**
     * 2023-05-02 조경민
     * 설명 : 관리자 회원 정보 변경
     * */
    @PatchMapping("/api/v1/admin/info")
    @ResponseBody
    public Long adminInfoChange(@RequestBody AdminMemberInfoChangeRequestDto requestDto){

        return memberService.adminMemberinfoChange(requestDto);
    }



}
