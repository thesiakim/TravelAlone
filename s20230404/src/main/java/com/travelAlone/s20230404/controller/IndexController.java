package com.travelAlone.s20230404.controller;

import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
public class IndexController {

		/**
		 * 2023-04-17 조경민 설명 : 메인페이지 이동
		 */
/*
		  @GetMapping("/") public String main(@LoginUser MemberJpa memberJpa, Model
		  model){

		  if (memberJpa != null){
			  model.addAttribute("memberId", memberJpa.getId());
			  System.out.println("user.getNickName() = " + memberJpa.getNickname());
		  }

		  return "th/main";
		  }
*/
    /**
     * 2023-04-25 조경민
     * 설명: 저장된 이미지를 /display?file=~~~~ 형식으로 주소를 적으면 사진을 보여줌
     * */
    @GetMapping("/display")
    public ResponseEntity<Resource> GetProfileImage(@RequestParam("file") String fileName) {

        System.out.println("fileName = " + fileName);

        String absolutePath = new File("").getAbsolutePath() + File.separator+ File.separator;
        Resource resource = new FileSystemResource(absolutePath + fileName);
        if(!resource.exists())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        HttpHeaders header = new HttpHeaders();
        try{
            Path filePath = Paths.get(absolutePath + fileName);
            header.add("Content-type", Files.probeContentType(filePath));
        }catch(IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }
}

