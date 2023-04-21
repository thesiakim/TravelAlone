package com.travelAlone.s20230404.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.mh.HouseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HouseController {

	private final HouseService mh;
	
	// ===================숙소===================
	//숙소 메인 보기
	@RequestMapping(value = "hou")
	public String notice(House house , String currentPage, Model model) {
		log.info("mhController2 Start house...");
		int totalHouse = mh.totalHouse();		
		
		//페이징
		Paging page = new Paging(totalHouse, currentPage);
		house.setStart(page.getStart());
		house.setEnd(page.getEnd());
		
		
		//여기에 게시판 코드 불러오는 코드 작성
		List<CommonCode> commonCode = mh.getCommonCode();
		log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
		model.addAttribute("boardList",commonCode);
		
		
		//지역 코드도 가져와볼까나
		List<CommonCode> commonLocCode = mh.getCommonLocCode();
		log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
		model.addAttribute("boardLocList",commonLocCode);
		
		
		
		//숙소리스트
		List<House> listHouse = mh.listHouse(house);
		log.info("mhController2 list listHouse.size()=>"+ listHouse.size());
				
		model.addAttribute("totalHouse", totalHouse);
		model.addAttribute("houseList", listHouse);
		model.addAttribute("page", page);
		return "mhHou/hou";
	}
	
	//숙소   정보글조회
	@GetMapping(value = "houDetail")
	public String houseDetail(int hid , Model model ) {
		log.info("mhController2 Start houseDetail");
		log.info("mhController2 houseDetail house_id->"+ hid );
		House house = mh.detailHouse(hid);		
		model.addAttribute("house", house);		
		return "mhHou/houDetail";
	}
	
	//정보글작성  페이지 이동
	@GetMapping(value = "houWriteForm")
	public String houWriteForm(House house, Model model) {
		log.info("mhController2  houWriteForm Start..." );
		return "mhHou/houWriteForm";
	}
	
	
	//정보글작성
		@PostMapping(value = "houWriteForm")
		public String houWrite(House house , Model model) {
			log.info("mhController2  houWrite Start...");
			
			int insertResult = mh.insertHou(house);
			log.info("mhController2 houWrite insertResult->"+insertResult );
			
			if (insertResult > 0) {
				return "redirect:hou";}
			else {
				model.addAttribute("msg","입력 실패 확인해 보세요");
				return "forward:mhHou/houWriteForm";
			}
			
		}
	
		//정보글수정 페이지이동
		@GetMapping(value = "travelUpdateForm")
		public String houseUpdateForm(int house_id, Model model) {
			log.info("HouseController Start updateForm...");
			House house = mh.detailHouse(house_id);			
			log.info("mhController updateFormHouse house->" + house);			
			model.addAttribute("house", house);	
			return "mhHou/houUpdateForm";
		}
		
		
		//정보글수정 처리
		@PostMapping(value = "updateHouse")
		public String updateNotice(House house , Model model) {
			log.info("HouseController Start update");
			int updateCount = mh.updateHouse(house);
			log.info("HouseController updateHouse updateCount ->" + updateCount);
			
			model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
			model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
			
			return "forward:hou";
		}	
		
		//정보 글 삭제
		@RequestMapping(value = "deleteHouse")
		public String deleteHouse(int house_id, Model model) {
			log.info("HouseController Start delete... n_id :" +house_id);
			int result = mh.deleteHouse(house_id);			
			return "redirect:hou";
		}
		
		//숙소 검색
		@RequestMapping(value = "houseSearch")
		public String houseSearch(House house, String currentPage, Model model) {
			log.info("HouseController houseSearch Start ..." );
			
			int totalHouse = mh.conditionHouseCount(house);
			log.info("HouseController houseSearch totalInquire =>" + totalHouse);
			// Paging 작업
			Paging page = new Paging(totalHouse, currentPage);
			
			house.setStart(page.getStart());
			house.setEnd(page.getEnd());
			
			List<House> listSearchHouse = mh.listSearchHouse(house);
			log.info("HouseController houseSearch listSearchHouse.size()=>" + 
					listSearchHouse.size());
			
			model.addAttribute("totalHouse", totalHouse);
			model.addAttribute("houseList", listSearchHouse);
			model.addAttribute("page", page);
			model.addAttribute("search", house.getKeyword());
			
			return "mhHou/hou";
		}
		
		//숙소종류 구분 필터
		@GetMapping(value = "houseCodeFilter")
		public String houseCodeFilter(@RequestParam(name = "code") 
		String code, House house, String currentPage, Model model  ) {
			log.info("HouseController houseCodeFilter Start" );
			
			//숙소코드
			List<CommonCode> commonCode = mh.getCommonCode();
			log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
			model.addAttribute("boardList",commonCode);
			
			
			
			
			int totalHouse = mh.conditionOptionCount(code);
			log.info("HouseController houseCodeFilter totalHouse =>" + totalHouse);
			
			
			//페이징
			Paging page = new Paging(totalHouse, currentPage);
			house.setStart(page.getStart());
			house.setEnd(page.getEnd());
			house.setCode(code);
			
			List<House> listFilterHouse = mh.listFilterOptionHouse(house);
			log.info("HouseController  listFilterHouse.size()=>" + listFilterHouse.size());
			model.addAttribute("totalHouse", totalHouse);
			model.addAttribute("houseList", listFilterHouse);
			model.addAttribute("page", page);
			model.addAttribute("search", house.getKeyword());
									
			return "mhHou/hou";
		}
		
		
		
		//지역 구분 필터
		@GetMapping(value = "houLocCodeFilter")
		public String locCodeFilter(@RequestParam(name = "code") 
		String code, House house, String currentPage, Model model) {
			log.info("HouseController locCodeFilter Start" );
			
			//지역 코드도 가져와볼까나
			List<CommonCode> commonLocCode = mh.getCommonLocCode();
			log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
			model.addAttribute("boardLocList",commonLocCode);
			
			
			int totalLoc = mh.conditionOptionLocCount(code);
			log.info("HouseController locCodeFilter totalLoc =>" + totalLoc);
			
			
			//페이징
			Paging page = new Paging(totalLoc, currentPage);
			house.setStart(page.getStart());
			house.setEnd(page.getEnd());
			house.setCode(code);
			
			List<House> listFilterLoc = mh.listFilterOptionLoc(house);
			log.info("HouseController  listFilterLoc.size()=>" + listFilterLoc.size());
			model.addAttribute("totalLoc", totalLoc);
			model.addAttribute("houseList", listFilterLoc);
			model.addAttribute("page", page);
			model.addAttribute("search", house.getKeyword());
			
			return "mhHou/hou";
		}
		

		

		


		
		
		
		
}
