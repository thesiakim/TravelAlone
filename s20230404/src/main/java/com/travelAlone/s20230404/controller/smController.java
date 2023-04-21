package com.travelAlone.s20230404.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.sm.smService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class smController {

	private final smService sm;
	
	// ===================여행지===================
	//여행지 메인 보기
	@RequestMapping(value = "tra")
	public String travel(Travel travel , String currentPage, Model model) {
		log.info("smController Start travel...");
		int totalTravel = sm.totalTravel();		
		
		//페이징
		Paging page = new Paging(totalTravel, currentPage);
		travel.setStart(page.getStart());
		travel.setEnd(page.getEnd());
		

		List<Travel> listTravel = sm.listTravel(travel);
		log.info("smController list listTravel.size()=>"+ listTravel.size());
				
		model.addAttribute("totalTravel", totalTravel);
		model.addAttribute("travelList", listTravel);
		model.addAttribute("page", page);
		return "smTra/tra";
	}
	
	//여행지   정보글조회
	@GetMapping(value = "traDetail")
	public String travelDetail(int tid , Model model ) {
		log.info("smController Start travelDetail");
		log.info("smController travelDetail travel_id->"+ tid );
		Travel travel = sm.detailTravel(tid);		
		model.addAttribute("travel", travel);		
		return "smTra/traDetail";
	}
	
	//정보글 작성  페이지 이동
	@GetMapping(value = "traWriteForm")
	public String TravelWriteForm(Travel travel, Model model) {
		log.info("smController  TravelWriteForm Start..." );
		return "smTra/traWriteForm";
	}
	
	
	//정보글 작성
		@PostMapping(value = "traWriteForm")
		public String TravelWrite(Travel Travel , Model model) {
			log.info("smController  traWrite Start...");
			
			int insertResult = sm.insertTravel(Travel);
			log.info("smController traWrite insertResult->"+insertResult );
			
			if (insertResult > 0) {
				return "redirect:tra";}
			else {
				model.addAttribute("msg","입력 실패 확인해 보세요");
				return "forward:smTra/traWriteForm";
			}
			
		}
		

		//정보글 수정 페이지 이동
		@GetMapping(value = "traUpdateForm")
		public String travelUpdateForm(int travel_id, Model model) {
			log.info("TravelController Start updateForm...");
			Travel travel = sm.detailTravel(travel_id);			
			log.info("smController updateFormTravel travel->" + travel);			
			model.addAttribute("travel", travel);	
			return "smTra/traUpdateForm";
		}	
		
		//정보글 수정 처리
		@PostMapping(value = "updateTravel")
		public String updateTravel(Travel travel , Model model) {
			log.info("smController Start update");
			int updateCount = sm.updateTravel(travel);
			log.info("smController updateTravel updateCount ->" + updateCount);
			
			model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
			model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
			
			return "forward:tra";
		}	
		
		//정보 글 삭제
		@RequestMapping(value = "deleteTravel")
		public String deleteTravel(int travel_id, Model model) {
			log.info("smController Start delete... t_id :" +travel_id);
			int result = sm.deleteTravel(travel_id);			
			return "redirect:tra";
		}

}