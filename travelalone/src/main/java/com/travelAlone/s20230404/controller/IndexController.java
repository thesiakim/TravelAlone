package com.travelAlone.s20230404.controller;

import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.service.si.SiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

	private final SiService siService;

	    //메인 페이지에서 인기 명소/숙소/맛집 보여주기
		@GetMapping(value = {"/", "/index"})
		public String popular(Model model) {
			System.out.println("SiController popular start");
			
			//인기 명소
			List<Travel> popularTravel = siService.getPopularTravel();

			//인기 숙소
			List<House> popularHouse = siService.getPopularHouse();    
			
			//인기 맛집
			List<Res> popularRes = siService.getPopularRes();
					     
			
		
			
			
			
			model.addAttribute("popularTravel", popularTravel);
			model.addAttribute("popularRes", popularRes);
			model.addAttribute("popularHouse", popularHouse);

			
			return "index";
		}
		
    /**
     * 2023-04-25 조경민
     * 설명: 저장된 이미지를 /display?file=~~~~ 형식으로 주소를 적으면 사진을 보여줌
     * */
    @GetMapping("/display")
    public ResponseEntity<Resource> GetProfileImage(@RequestParam("file") String fileName) {

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


