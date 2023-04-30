package com.travelAlone.s20230404.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travelAlone.s20230404.model.si.ResultList;
import com.travelAlone.s20230404.service.si.SiServiceJpa;
import com.travelAlone.s20230404.service.si.SiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SiController {
	
	private final SiService siService;
	private final SiServiceJpa siServiceJpa;
	private final Logger logger = LoggerFactory.getLogger(SiController.class);
	
	//검색창에 검색한 경우
	@GetMapping(value = "search")
	public String search(@RequestParam("searchName") String keyword, @RequestParam("category") String category, Model model) {
		
		logger.info("siController search start");
		
		//Travel, House, Restaurant, Board가 List 타입으로 선언된 클래스 객체 선언
		ResultList resultList = new ResultList();
		
		//카테고리가 전체인 경우
		if(category.equals("category_total")) {
			resultList = siService.search(keyword, category);
			//검색 키워드가 하나라도 존재한다면 Search 테이블에 insert or update
			if(resultList.getTravelList().size() != 0 || resultList.getHouseList().size() != 0 || resultList.getRestaurantList().size() != 0 || resultList.getBoardList().size() != 0) {
				siService.upsertSearch(keyword);
			} else {
				return "si/noSearchResult";
			}
		
		//카테고리가 여행지인 경우
		} else if(category.equals("category_travel")) {
			logger.info("siController category_travel/ siService.search Start");
			resultList = siService.search(keyword, category);
			if(resultList.getTravelList().size() != 0) {
				siService.upsertSearch(keyword);
			} else {
				logger.info("siController category_travel/ siService.search 결과 resultList.getTravelList().size()가 0이기 때문에 noResultList.jsp로 이동");

				return "si/noSearchResult";
			}
			
		//카테고리가 숙소인 경우	
		} else if(category.equals("category_house")) {
			logger.info("siController category_house/ siService.search Start");
			resultList = siService.search(keyword, category);
			logger.info("siController category_house/ siService.search 결과 resultList.getHouseList().size() : " + resultList.getHouseList().size());
			if(resultList.getHouseList().size() != 0) {
				siService.upsertSearch(keyword);
			} else {
				logger.info("siController category_house/ siService.search 결과 resultList.getHouseList().size()가 0이기 때문에 noResultList.jsp로 이동");
				return "si/noSearchResult";
			}
			
		
		//카테고리가 맛집인 경우
		} else if(category.equals("category_res")) {
			resultList = siService.search(keyword, category);
			if(resultList.getRestaurantList().size() != 0) {
				siService.upsertSearch(keyword);
			} else {
				return "si/noSearchResult";
			}
			
		//카테고리가 커뮤니티인 경우	
		} else if(category.equals("category_comm")) {
			logger.info("siController category_comm/ siService.search Start");
			resultList = siService.search(keyword, category);
			logger.info("siController category_comm/ siService.search End");
			if(resultList.getBoardList().size() != 0) {
				siService.upsertSearch(keyword);
			} else {
				logger.info("siController category_comm/ siService.search 결과 resultList.getBoardList().size()가 0이기 때문에 noResultList.jsp로 이동");
				return "si/noSearchResult";
			}
			
		}
		
		//일간 인기 검색어 구하기
		List<String> dailyPopularKeywords = siService.getDailyPopularSearches();

		//주간 인기 검색어 구하기
		List<String> weeklyPopularKeywords = siService.getWeeklyPopularSearches();
		
		//월별 인기 검색어 구하기
		List<String> monthlyPopularKeywords = siService.getMonthlyPopularSearches();
		
		
		
		model.addAttribute("category", category);
		model.addAttribute("keyword", keyword);
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


