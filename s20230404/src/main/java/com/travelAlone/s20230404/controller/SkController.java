package com.travelAlone.s20230404.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	@GetMapping("res")
	public String notice(Res res, String currentPage, Model model) {
		log.info("SkController Start restaurant");
		int totalRes = sk.totalRes();
		
		Paging page = new Paging(totalRes, currentPage);
		res.setStart(page.getStart());
		res.setEnd(page.getEnd());
		
		List<Res> listRes = sk.listRes(res);
		log.info("SkController list listRestaurnat.size()=>"+ listRes.size());
		
		model.addAttribute("totalRes",totalRes);
		model.addAttribute("restList", listRes);
		model.addAttribute("page", page);
		return "sk/res";
	}
	
	@GetMapping("ResWriteForm")
	public String ResWriteForm(Res res,Model model) {
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
}