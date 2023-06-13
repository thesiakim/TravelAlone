package com.travelAlone.s20230404.controller;

import java.util.List;

import com.travelAlone.s20230404.model.dto.km.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.travelAlone.s20230404.service.km.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminController {

    private final MemberService memberService;


    // 관리자 페이지----------------------------------------------------------------
    /**
     * 2023-05-01 조경민
     * 설명 : 관리자 페이지 회원 목록 조회, 쿼리 스트링을 이용한 검색기능 추가, Jpa Pageable 이용하여 페이징 처리(page = 0부터 시작)
     *
     * @pageableDefault : 페이지에 출력되는 데이터 사이즈 설정(default = 10, 보여주기위해 입력)
     * Pageable : Jpa에서 제공하는 페이징 인터페이스로 레포지토리 쿼리에 인수로 전해주면 Page 객체를 반환
     * */
    @GetMapping("/admin")
    public String adminMain(@RequestParam(value = "search", required = false) String search,
                            @PageableDefault(size = 10) Pageable pageable,
                            Model model){
        // 현재 페이지 담기
        model.addAttribute("currentPage", pageable.getPageNumber());

        if (search==null){
            // search 값이 null이면 전체 조회
            Page<AdminMemberResponseDto> responseDtos = memberService.adminMemberListShow(pageable);

            // 페이지 전체 갯수 담기
            model.addAttribute("totalPage", responseDtos.getTotalPages());

            // 해당 페이지 멤버 정보 담기
            model.addAttribute("members",responseDtos.getContent());
        }else {
            // search값 존재하면 검색어 조회
            Page<AdminMemberResponseDto> responseDtos = memberService.adminMemberSearchAndListShow(search, pageable);

            // 페이지 전체 갯수 담기
            model.addAttribute("totalPage", responseDtos.getTotalPages());

            // 해당 페이지 멤버정보 담기
            model.addAttribute("members", responseDtos.getContent());
        }

        return "km/admin-main";
    }


    /**
     * 2023-05-02 조경민
     * 설명 : 관리자 페이지 회원 권한 변경
     * */
    @PatchMapping("/api/v1/admin/role")
    @ResponseBody
    public Long adminRoleChange(@RequestBody AdminMemberRoleRequestDto requestDto){

        System.out.println("requestDto = " + requestDto.getRole());
        System.out.println("requestDto.getId() = " + requestDto.getId());
        // 회원 권한 변경 후 아이디 반환
        return memberService.adminMemberRoleChange(requestDto);
    }

    /**
     * 2023-05-05 조경민
     * 설명 : 관리자 페이지 회원 정보 변경창 이동
     * */
    @GetMapping("/admin/info/{id}")
    public String adminInfo(@PathVariable Long id, Model model){

        model.addAttribute("member", memberService.adminMemberInfoById(id));

        return "km/admin-info";
    }

    /**
     * 2023-05-02 조경민
     * 설명 : 관리자 회원 정보 변경
     * */
    @PatchMapping("/api/v1/admin/info")
    @ResponseBody
    public Long adminInfoChange(@RequestPart(value = "file", required = false) List<MultipartFile> file,
                                @RequestPart(value = "key") AdminMemberInfoDto requestDto) throws Exception {

        return memberService.adminMemberinfoChange(file,requestDto);
    }



}



