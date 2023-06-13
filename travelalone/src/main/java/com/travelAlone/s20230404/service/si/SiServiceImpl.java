package com.travelAlone.s20230404.service.si;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.si.SiDao;
import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.model.si.RecentSearch;
import com.travelAlone.s20230404.model.si.ResultList;
import com.travelAlone.s20230404.model.si.Search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiServiceImpl implements SiService {

	private final SiDao siDao;
	
	//검색 시 선택한 카테고리에 따라 DB 데이터에  검색 키워드가 존재하는지 조회
	@Override
	public ResultList search(String keyword, String category, Travel travel, House house, Res res, Board board) {
		
		//log.info("siServiceImpl search start");
		ResultList resultList = new ResultList();
		
		  //카테고리가 전체인 경우
		  if(category.equals("category_total")) {
		
			  resultList.setTravelList(siDao.travelSearch(travel));
			  resultList.setHouseList(siDao.houseSearch(house));
			  resultList.setRestaurantList(siDao.resSearch(res));
			  resultList.setBoardList(siDao.boardSearch(board));
		  
		  //카테고리가 여행지인 경우	  
		  } else if(category.equals("category_travel")) {
			  resultList.setTravelList(siDao.travelSearch(travel));
		  
		  //카테고리가 숙소인 경우			  
		  } else if(category.equals("category_house")) {
			  resultList.setHouseList(siDao.houseSearch(house));
		  
		  //카테고리가 맛집인 경우	  
		  } else if(category.equals("category_res")) {
			  resultList.setRestaurantList(siDao.resSearch(res));
		  
		  //카테고리가 커뮤니티인 경우		  
		  } else if(category.equals("category_comm")) {
			  resultList.setBoardList(siDao.boardSearch(board));
	      }
		  
		 return resultList;
		 
	}
	
	
	//검색 키워드가 Search 테이블에 있는지 조회한 뒤 있으면 update, 없으면 insert
	@Override
	public void upsertSearch(String keyword) {
		//log.info("siServiceImpl upsertSearch Start");
		siDao.upsertSearch(keyword);
		
	}

	//일간 인기 검색어 구하기
	@Override
	public List<String> getDailyPopularSearches() {
		//log.info("siServiceImpl getDailyPopularSearches Start");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = now.with(LocalTime.MIN); //2023-04-27T00:00
		LocalDateTime end = now.with(LocalTime.MAX);   //2023-04-27T23:59:59.99999999
		
		ZonedDateTime utcStart = start.atZone(ZoneOffset.UTC);
		ZonedDateTime utcEnd = end.atZone(ZoneOffset.UTC);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String startOfToday = utcStart.format(formatter);   //2023-04-27 00:00:00
		String endOfToday = utcEnd.format(formatter);       //2023-04-27 23:59:59
		 
		
		List<Search> dailySearches = siDao.getDailyPopularSearches(startOfToday, endOfToday);
		
		return dailySearches.stream().map(Search::getS_keyword).collect(Collectors.toList());		
	}


	//주간 인기 검색어 구하기
	@Override
	public List<String> getWeeklyPopularSearches() {
		//log.info("siServiceImpl getWeeklyPopularSearches Start");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);   //2023-04-24T00:00
		LocalDateTime end = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).with(LocalTime.MAX);         //2023-04-30T23:59:59.999999999
		
		ZonedDateTime utcStart = start.atZone(ZoneOffset.UTC);
		ZonedDateTime utcEnd = end.atZone(ZoneOffset.UTC);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String startOfWeek = utcStart.format(formatter);   //2023-04-24 00:00:00
		String endOfWeek = utcEnd.format(formatter);       //2023-04-30 23:59:59
		
		List<Search> weeklySearches = siDao.getWeeklyPopularSearches(startOfWeek, endOfWeek);
		
		return weeklySearches.stream().map(Search::getS_keyword).collect(Collectors.toList());
	}


	//월간 인기 검색어 구하기
	@Override
	public List<String> getMonthlyPopularSearches() {
		//log.info("siServiceImpl getMonthlyPopularSearches Start");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = now.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);   //2023-04-01T00:00
		LocalDateTime end = now.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);      //2023-04-30T23:59:59.999999999
		
		ZonedDateTime utcStart = start.atZone(ZoneOffset.UTC);
		ZonedDateTime utcEnd = end.atZone(ZoneOffset.UTC);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String startOfMonth = utcStart.format(formatter);   //2023-04-01 00:00:00
		String endOfMonth = utcEnd.format(formatter);       //2023-04-30 23:59:59
		
		List<Search> monthlySearches = siDao.getMonthlyPopularSearches(startOfMonth, endOfMonth);
		
		return monthlySearches.stream().map(Search::getS_keyword).collect(Collectors.toList());
		
	}


	//인기 명소 구하기
	@Override
	public List<Travel> getPopularTravel() {
		return siDao.getPopularTravel();
	}


	//인기 맛집 구하기
	@Override
	public List<Res> getPopularRes() {
		return siDao.getPopularRes();
	}

    //인기 숙소 구하기
	@Override
	public List<House> getPopularHouse() {
		return siDao.getPopularHouse();
	}

	//여행지 검색 결과 개수 조회
	@Override
	public int getTravelCount(Travel travel) {
		return siDao.getTravelCount(travel);
	}


	//숙소 검색 결과 개수 조회
	@Override
	public int getHouseCount(House house) {
		return siDao.getHouseCount(house);
	}


	//맛집 검색 결과 개수 조회
	@Override
	public int getResCount(Res res) {
		return siDao.getResCount(res);
	}


	//커뮤니티 검색 결과 개수 조회
	@Override
	public int getBoardCount(Board board) {
		return siDao.getBoardCount(board);
	}


	//최근 검색어 목록 조회
	@Override
	public List<RecentSearch> getRecentSearchList(RecentSearch recentSearch) {
		int recentCount = siDao.countRecentSearch(recentSearch);
		if(recentCount >= 10) {
			siDao.deleteRecentSearch(recentSearch);
		}
		
		int findRecentSearch = siDao.findRecentSearch(recentSearch);
		if(findRecentSearch == 0) siDao.insertRecentSearch(recentSearch);
		
		return siDao.getRecentSearchList(recentSearch);
		
	}


}
