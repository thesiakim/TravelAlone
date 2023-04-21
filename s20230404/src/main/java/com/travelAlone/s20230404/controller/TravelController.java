package com.travelAlone.s20230404.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.sm.TravelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TravelController {

	private final TravelService sm;
	
	// ===================여행지===================
	//여행지 메인 보기
	@RequestMapping(value = "tra")
	public String notice(Travel travel , String currentPage, Model model) {
		log.info("smController Start travel...");
		int totalTravel = sm.totalTravel();		
		
		//페이징
		Paging page = new Paging(totalTravel, currentPage);
		travel.setStart(page.getStart());
		travel.setEnd(page.getEnd());
		
		
		//여기에 게시판 코드 불러오는 코드 작성
		List<CommonCode> commonCode = sm.getCommonCode();
		log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
		model.addAttribute("boardList",commonCode);
		
		
		//지역 코드 가져오기
		List<CommonCode> commonLocCode = sm.getCommonLocCode();
		log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
		model.addAttribute("boardLocList",commonLocCode);
		
		
		
		//여행지리스트
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
	
	//정보글작성  페이지 이동
	@GetMapping(value = "traWriteForm")
	public String traWriteForm(Travel travel, Model model) {
		log.info("smController  traWriteForm Start..." );
		return "smTra/traWriteForm";
	}
	
	
	//정보글작성
		@PostMapping(value = "traWriteForm")
		public String traWrite(Travel travel , Model model) {
			log.info("smController  traWrite Start...");
			
			int insertResult = sm.insertTravel(travel);
			log.info("smController traWrite insertResult->"+insertResult );
			
			if (insertResult > 0) {
				return "redirect:tra";}
			else {
				model.addAttribute("msg","입력 실패 확인해 보세요");
				return "forward:smTra/traWriteForm";
			}
			
		}
	
		//정보글수정 페이지이동
		@GetMapping(value = "traUpdateForm")
		public String travelUpdateForm(int travel_id, Model model) {
			log.info("TravelController Start updateForm...");
			Travel travel = sm.detailTravel(travel_id);			
			log.info("smController updateFormTravel travel->" + travel);			
			model.addAttribute("travel", travel);	
			return "smTra/traUpdateForm";
		}
		
		
		//정보글수정 처리
		@PostMapping(value = "updateTravel")
		public String updateTravel(Travel travel , Model model) {
			log.info("TravelController Start update");
			int updateCount = sm.updateTravel(travel);
			log.info("TravelController updateTravel updateCount ->" + updateCount);
			
			model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
			model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
			
			return "forward:tra";
		}	
		
		//정보 글 삭제
		@RequestMapping(value = "deleteTravel")
		public String deleteTravel(int travel_id, Model model) {
			log.info("TravelController Start delete... n_id :" +travel_id);
			int result = sm.deleteTravel(travel_id);			
			return "redirect:tra";
		}
		
		//여행지 검색
		@RequestMapping(value = "travelSearch")
		public String travelSearch(Travel travel, String currentPage, Model model) {
			log.info("TravelController travelSearch Start ..." );
			
			int totalTravel = sm.conditionTravelCount(travel);
			log.info("TravelController travelSearch totalInquire =>" + totalTravel);
			// Paging 작업
			Paging page = new Paging(totalTravel, currentPage);
			
			travel.setStart(page.getStart());
			travel.setEnd(page.getEnd());
			
			List<Travel> listSearchTravel = sm.listSearchTravel(travel);
			log.info("TravelController travelSearch listSearchTravel.size()=>" + 
					listSearchTravel.size());
			
			model.addAttribute("totalTravel", totalTravel);
			model.addAttribute("travelList", listSearchTravel);
			model.addAttribute("page", page);
			model.addAttribute("search", travel.getKeyword());
			
			return "smTra/tra";
		}
		
		//여행지종류 구분 필터
		@GetMapping(value = "travelCodeFilter")
		public String travelCodeFilter(@RequestParam(name = "code") 
		String code, Travel travel, String currentPage, Model model  ) {
			log.info("TravelController travelCodeFilter Start" );
			
			//여행지코드
			List<CommonCode> commonCode = sm.getCommonCode();
			log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
			model.addAttribute("boardList",commonCode);
			
			
			
			
			int totalTravel = sm.conditionOptionCount(code);
			log.info("TravelController travelCodeFilter totalTravel =>" + totalTravel);
			
			
			//페이징
			Paging page = new Paging(totalTravel, currentPage);
			travel.setStart(page.getStart());
			travel.setEnd(page.getEnd());
			travel.setCode(code);
			
			List<Travel> listFilterTravel = sm.listFilterOptionTravel(travel);
			log.info("TravelController  listFilterTravel.size()=>" + listFilterTravel.size());
			model.addAttribute("totalTravel", totalTravel);
			model.addAttribute("travelList", listFilterTravel);
			model.addAttribute("page", page);
			model.addAttribute("search", travel.getKeyword());
									
			return "smTra/tra";
		}
		
		
		
		//지역 구분 필터
		@GetMapping(value = "traLocCodeFilter")
		public String locCodeFilter(@RequestParam(name = "code") 
		String code, Travel travel, String currentPage, Model model) {
			log.info("TravelController locCodeFilter Start" );
			
			//지역 코드 불러오기
			List<CommonCode> commonLocCode = sm.getCommonLocCode();
			log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
			model.addAttribute("boardLocList",commonLocCode);
			
			
			int totalLoc = sm.conditionOptionLocCount(code);
			log.info("TravelController locCodeFilter totalLoc =>" + totalLoc);
			
			
			//페이징
			Paging page = new Paging(totalLoc, currentPage);
			travel.setStart(page.getStart());
			travel.setEnd(page.getEnd());
			travel.setCode(code);
			
			List<Travel> listFilterLoc = sm.listFilterOptionLoc(travel);
			log.info("TravelController  listFilterLoc.size()=>" + listFilterLoc.size());
			model.addAttribute("totalLoc", totalLoc);
			model.addAttribute("travelList", listFilterLoc);
			model.addAttribute("page", page);
			model.addAttribute("search", travel.getKeyword());
			
			return "smTra/tra";
		}
		

		

		


		
		
		
		
}
