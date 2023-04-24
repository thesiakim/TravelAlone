package com.travelAlone.s20230404.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.sk.SkService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SkController {

	private final SkService sk;
	
	// 맛집 메인
	@RequestMapping(value = "res")
	public String notice(Res res, String currentPage, Model model) {
		log.info("SkController Start res");
		int totalRes = sk.totalRes();
		
	// 페이징
		Paging page = new Paging(totalRes, currentPage);
		res.setStart(page.getStart());
		res.setEnd(page.getEnd());
		
		List<Res> listRes = sk.listRes(res);
		log.info("SkController list listRes.size()=>"+ listRes.size());
		
		model.addAttribute("totalRes",totalRes);
		model.addAttribute("resList", listRes);
		model.addAttribute("page", page);
		return "sk/res";
	}
	
	// 맛집 정보글
	@GetMapping(value = "resDetail")
	public String resDetail(int rid , Model model ) {
		log.info("resController Start resDetail");
		log.info("resController resDetail res_id->"+ rid );
		Res res = sk.detailRes(rid);		
		model.addAttribute("restaurant", res);
		return "sk/resDetail";
	}
	
	// 정보글 작성 페이지
	@GetMapping(value = "resWriteForm")
	public String resWriteForm(Res res, Model model) {
		log.info("resController  resWriteForm Start" );
		return "sk/resWriteForm";
	}
	
	// 맛집 정보글 작성
	@PostMapping(value = "resWriteForm")
	public String resWrite(Res res, Model model) {
		log.info("SkController resWrite start");
		
		int insertResult = sk.insertRes(res);
		log.info("SkController resWrite insertResult->"+insertResult);
		
		if(insertResult > 0) {
			return "redirect:res";
			}
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:sk/resWriteForm";
		}
	}
	
	// 맛집 정보글 수정 페이지
	@GetMapping(value = "resUpdateForm")
	public String resUpdateForm(int restaurant_id, Model model) {
		log.info("SkController Start updateForm");
		Res res = sk.detailRes(restaurant_id);
		log.info("SkController updateFormRes res->" + res);			
		model.addAttribute("res", res);	
		return "sk/resUpdateForm";
	}
	
	
	// 맛집 정보글 수정 처리
	@PostMapping(value = "updateRes")
	public String updateNotice(Res res , Model model) {
		log.info("SkController Start update");
		int updateCount = sk.updateRes(res);
		log.info("SkController updateRes updateCount ->" + updateCount);
		
		model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
		model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
		
		return "forward:res";
	}	
	
	// 맛집 정보글 삭제
	@RequestMapping(value = "deleteRes")
	public String deleteRes(int restaurant_id, Model model) {
		log.info("SkController Start delete... r_id :" + restaurant_id);
		int result = sk.deleteRes(restaurant_id);			
		return "redirect:res";
		}
}