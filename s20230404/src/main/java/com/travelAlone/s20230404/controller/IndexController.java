package com.travelAlone.s20230404.controller;

import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {


    /**
     * 2023-04-17 조경민
     * 설명 : 메인페이지 이동
     * */
	
    @GetMapping("/")
    public String main(@LoginUser MemberJpa memberJpa, Model model){

        if (memberJpa != null){
            model.addAttribute("memberId", memberJpa.getId());
            System.out.println("user.getNickName() = " + memberJpa.getNickname());
        }

        return "th/main";
    }
    
}