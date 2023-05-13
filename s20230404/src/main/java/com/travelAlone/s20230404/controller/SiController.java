package com.travelAlone.s20230404.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.model.si.RecentSearch;
import com.travelAlone.s20230404.model.si.ResultList;
import com.travelAlone.s20230404.service.si.SiServiceJpa;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.si.MailSendService;
import com.travelAlone.s20230404.service.si.SiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SiController {
	
	private final SiService siService;
	private final SiServiceJpa siServiceJpa;
	private final MailSendService mailService;
	ResultList resultList;
	
	//검색창에 검색한 경우
	@GetMapping(value = "search")
	public String search(@RequestParam("searchName") String keyword, 
			             @RequestParam("category") String category, 
			             @LoginUser MemberJpa memberJpa,
			             String currentPage, Model model) {
		
		//log.info("siController search start");
		
		Travel travel = new Travel();
		House house = new House();
		Res res = new Res();
		Board board = new Board();
		
		Paging paging = null;
		
		//검색 키워드 저장
		travel.setKeyword(keyword);
		house.setKeyword(keyword);
		res.setKeyword(keyword);
		board.setKeyWord(keyword);
		
		//Travel, House, Restaurant, Board가 List 타입으로 선언된 클래스 객체 선언
		resultList = new ResultList();
		
		//검색 결과 개수를 저장하기 위한 변수 선언
		int count = 0;
		int travelCount = 0;
		int houseCount = 0;
		int resCount = 0;
		int boardCount = 0;
		
		
		//카테고리가 전체인 경우
		if(category.equals("category_total")) {
			
			//각 카테고리별 검색 결과 개수 저장
			travelCount = siService.getTravelCount(travel);
			houseCount = siService.getHouseCount(house);
			resCount = siService.getResCount(res);
			boardCount = siService.getBoardCount(board);
			
			//총 개수 저장
			count = travelCount + houseCount + resCount + boardCount;
			paging = new Paging(count, currentPage);
			travel.setStart(paging.getStart());
			travel.setEnd(paging.getEnd());
			house.setStart(paging.getStart());
			house.setEnd(paging.getEnd());
			res.setStart(paging.getStart());
			res.setEnd(paging.getEnd());
			board.setStart(paging.getStart());
			board.setEnd(paging.getEnd());
			
			
			//검색 키워드에 대한 데이터 가져오기
			resultList = siService.search(keyword, category, travel, house, res, board);
			
			//검색 키워드가 하나라도 존재한다면 Search 테이블에 insert or update
			if(resultList.getTravelList().size() != 0 || resultList.getHouseList().size() != 0 || resultList.getRestaurantList().size() != 0 || resultList.getBoardList().size() != 0) {
				siService.upsertSearch(keyword);
			} 
		
		//카테고리가 여행지인 경우
		} else if(category.equals("category_travel")) {
			
			//검색 결과수 조회
			count = siService.getTravelCount(travel);
			
			//페이징
			paging = new Paging(count, currentPage);
			travel.setStart(paging.getStart());
			travel.setEnd(paging.getEnd());
			
			//검색 결과 데이터 조회
			resultList = siService.search(keyword, category, travel, house, res, board);
			
			if(resultList.getTravelList().size() != 0) {
				siService.upsertSearch(keyword);
			} 
			
		//카테고리가 숙소인 경우	
		} else if(category.equals("category_house")) {
			
			//검색 결과수 조회
			count = siService.getHouseCount(house);
			
			//페이징
			paging = new Paging(count, currentPage);
			house.setStart(paging.getStart());
			house.setEnd(paging.getEnd());
			
			//검색 결과 데이터 조회
			resultList = siService.search(keyword, category, travel, house, res, board);

			if(resultList.getHouseList().size() != 0) {
				siService.upsertSearch(keyword);
			}
			
		//카테고리가 맛집인 경우
		} else if(category.equals("category_res")) {
			
			//검색 결과수 조회
			count = siService.getResCount(res);
			
			//페이징
			paging = new Paging(count, currentPage);
			res.setStart(paging.getStart());
			res.setEnd(paging.getEnd());
			
			//검색 결과 데이터 조회
			resultList = siService.search(keyword, category, travel, house, res, board);
			if(resultList.getRestaurantList().size() != 0) {
				siService.upsertSearch(keyword);
			} 
			
		//카테고리가 커뮤니티인 경우	
		} else if(category.equals("category_comm")) {
			
			//검색 결과수 조회
			count = siService.getBoardCount(board);
						 
			//페이징
			paging = new Paging(count, currentPage);
			board.setStart(paging.getStart());
			board.setEnd(paging.getEnd());
						
			//검색 결과 데이터 조회
			resultList = siService.search(keyword, category, travel, house, res, board);
			
			if(resultList.getBoardList().size() != 0) {
				siService.upsertSearch(keyword);
			} 
			
		}
		
		//일간 인기 검색어 구하기
		List<String> dailyPopularKeywords = siService.getDailyPopularSearches();

		//주간 인기 검색어 구하기
		List<String> weeklyPopularKeywords = siService.getWeeklyPopularSearches();
		
		//월별 인기 검색어 구하기
		List<String> monthlyPopularKeywords = siService.getMonthlyPopularSearches();
		
		//최근 검색어 저장하기
		if(memberJpa != null) {
			RecentSearch recentSearch = new RecentSearch(memberJpa.getId(), keyword);
			List<RecentSearch> recentSearchList = siService.getRecentSearchList(recentSearch);
			model.addAttribute("recentSearchList", recentSearchList);
		}

		
		model.addAttribute("paging", paging);
		model.addAttribute("category", category);
		model.addAttribute("keyword", keyword);
		model.addAttribute("count", count);
		model.addAttribute("travelCount", travelCount);
		model.addAttribute("houseCount", houseCount);
		model.addAttribute("resCount", resCount);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCount", resultList.getResultCount());
		model.addAttribute("dailyPopularKeywords", dailyPopularKeywords);
		model.addAttribute("weeklyPopularKeywords", weeklyPopularKeywords);
		model.addAttribute("monthlyPopularKeywords", monthlyPopularKeywords);
			
		return "si/searchResult";
	}
	
	
	
	
	//자동완성
	@GetMapping(value = "autocomplete")
	public @ResponseBody List<String> autocomplete(@RequestParam("keyword") String keyword, @RequestParam("category") String category) {
        
		List<String> data = new ArrayList<String>();
		
		if(category.equals("category_total")) {
					
			data.addAll(siServiceJpa.autoTravelSearch(keyword));
			data.addAll(siServiceJpa.autoHouseSearch(keyword));
			data.addAll(siServiceJpa.autoResSearch(keyword));
			
		} else if(category.equals("category_travel")) {
			data = siServiceJpa.autoTravelSearch(keyword);
			
		} else if(category.equals("category_house")) {
			data = siServiceJpa.autoHouseSearch(keyword);
			
		} else if(category.equals("category_res")) {
			data = siServiceJpa.autoResSearch(keyword);
		}
		
		return data;
		
		} 
	
	
	//회원 가입 시 인증을 위한 이메일 전송
	@PostMapping("/checkEmail")
	@ResponseBody
	public Map<String, String> checkEmail(@RequestParam("email") String email) {
		Map<String, String> resultMap = new HashMap<>();
		String authCode = mailService.joinEmail(email);
		if(authCode != null) {
			resultMap.put("result", "success");
			resultMap.put("authCode", authCode);
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	
	//회원 가입 시 인증코드 일치 여부 확인
	@PostMapping("/checkAuthCode")
	@ResponseBody
	public Map<String, String> checkAuthCode(HttpServletRequest request, @RequestParam("authCode") String authCode) {
		Map<String, String> result = new HashMap<String, String>();
	    HttpSession session = request.getSession();

	    String savedAuthCode = (String) session.getAttribute("authCode");
	    
	    if (savedAuthCode != null && savedAuthCode.equals(authCode)) {
	      result.put("result", "success");
	      session.removeAttribute("authCode");      
	    } else {
	      result.put("result", "fail");
	    }
	    return result;
	  }
	
		
}
	
	
	





