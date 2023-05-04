package com.travelAlone.s20230404.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.model.si.ResultList;
import com.travelAlone.s20230404.service.si.SiServiceJpa;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.si.SiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SiController {
	
	private final SiService siService;
	private final SiServiceJpa siServiceJpa;
	ResultList resultList;
	
	//검색창에 검색한 경우
	@GetMapping(value = "search")
	public String search(@RequestParam("searchName") String keyword, 
			             @RequestParam("category") String category, 
			             String currentPage, Model model) {
		
		log.info("siController search start");
		
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
			
			System.out.println("카테고리 전체 travelList size : " + resultList.getTravelList());
			System.out.println("카테고리 전체 houseList size : " + resultList.getHouseList());
			System.out.println("카테고리 전체 resList size : " + resultList.getRestaurantList());
			System.out.println("카테고리 전체 boardList size : " + resultList.getBoardList());
			
			//검색 키워드가 하나라도 존재한다면 Search 테이블에 insert or update
			if(resultList.getTravelList().size() != 0 || resultList.getHouseList().size() != 0 || resultList.getRestaurantList().size() != 0 || resultList.getBoardList().size() != 0) {
				siService.upsertSearch(keyword);
			} 
		
		//카테고리가 여행지인 경우
		} else if(category.equals("category_travel")) {
			log.info("siController category_travel/ siService.search Start");
			
			//검색 결과수 조회
			count = siService.getTravelCount(travel);
			System.out.println("travel count : " + count);
			
			//페이징
			paging = new Paging(count, currentPage);
			travel.setStart(paging.getStart());
			travel.setEnd(paging.getEnd());
			
			//검색 결과 데이터 조회
			resultList = siService.search(keyword, category, travel, house, res, board);
			
			System.out.println("카테고리 여행지 travelList size : " + resultList.getTravelList());
			if(resultList.getTravelList().size() != 0) {
				siService.upsertSearch(keyword);
			} 
			
		//카테고리가 숙소인 경우	
		} else if(category.equals("category_house")) {
			log.info("siController category_house/ siService.search Start");
			
			//검색 결과수 조회
			count = siService.getHouseCount(house);
			
			//페이징
			paging = new Paging(count, currentPage);
			house.setStart(paging.getStart());
			house.setEnd(paging.getEnd());
			
			//검색 결과 데이터 조회
			resultList = siService.search(keyword, category, travel, house, res, board);
			log.info("siController category_house/ siService.search 결과 resultList.getHouseList().size() : " + resultList.getHouseList().size());
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
			log.info("siController category_comm/ siService.search Start");
			
			//검색 결과수 조회
			System.out.println("boardListCount 시작");
			count = siService.getBoardCount(board);
			
			System.out.println("board 페이징 시작");
			 
			//페이징
			paging = new Paging(count, currentPage);
			board.setStart(paging.getStart());
			board.setEnd(paging.getEnd());
			
			System.out.println("boardlist 페이징된걸로 list뽑기 시작");
			
			//검색 결과 데이터 조회
			resultList = siService.search(keyword, category, travel, house, res, board);
			log.info("siController category_comm/ siService.search End");
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
		
		System.out.println("SiController resultList.getBoardList : " + resultList.getBoardList());
	
		return "si/searchResult";
	}
	
	
	
	
	//자동완성
	@GetMapping(value = "autocomplete")
	public @ResponseBody List<String> autocomplete(@RequestParam("keyword") String keyword, @RequestParam("category") String category) {
        
		System.out.println("siController autocomplete start");
		List<String> data = new ArrayList<String>();
		
		if(category.equals("category_total")) {
			
			System.out.println("siController autocomplete category_total start");
		
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
		
		
}
	
	
	





