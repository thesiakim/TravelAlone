package com.travelAlone.s20230404.dao.si;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.model.si.TimeDTO;
import com.travelAlone.s20230404.model.si.RecentSearch;
import com.travelAlone.s20230404.model.si.Search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SiDaoImpl implements SiDao {
	
	private final SqlSession session;
	
	//Travel 테이블에 검색 키워드가 있는지 조회
	@Override
	public List<Travel> travelSearch(Travel travel) {
		//log.info("siDaoImpl travelSearch start");
		List<Travel> travelList = null;
		
		try {
			//Travel 테이블에 검색 키워드가 있는지 조회한 뒤 결과를 List<Travel>로 반환
			travelList = session.selectList("siTravelSearch", travel);
			System.out.println("siDaoImpl travelList : " + travelList);
			
			//Travel 테이블에 검색 키워드가 존재한다면 해당 키워드에 대해  t_count ++
			if(!travelList.isEmpty()) {
				session.update("siTravelUpdate", travel);
			}
			
		} catch(Exception e) {
			log.info("siDaoImpl travelSearch e.getMessage() : " + e.getMessage());
		}
		return travelList;
	}
	
	
	//House 테이블에 검색 키워드가 있는지 조회
	@Override
	public List<House> houseSearch(House house) {
		//log.info("siDaoImpl houseSearch start");
		List<House> houseList = null;
		
		try {
			houseList = session.selectList("siHouseSearch", house);
			
			if(!houseList.isEmpty()) {
				session.update("siHouseUpdate", house);
			}
			
		} catch(Exception e) {
			log.info("siDaoImpl houseSearch e.getMessage() : " + e.getMessage());
		}
		return houseList;
	}
	


	//Restaurant 테이블에 검색 키워드가 있는지 조회
	@Override
	public List<Res> resSearch(Res res) {
		//log.info("siDaoImpl resSearch start");
		List<Res> resList = null;
		
		try {
			resList = session.selectList("siResSearch", res);	
			
			if(!resList.isEmpty()) {
				session.update("siResUpdate", res);
			}
			
		} catch(Exception e) {
			log.info("siDaoImpl houseSearch e.getMessage() : " + e.getMessage());
		}
		return resList;
	}


	//Board 테이블에 검색 키워드가 있는지 조회
	@Override
	public List<Board> boardSearch(Board board) {
		//log.info("siDaoImpl boardSearch start");
		List<Board> boardList = null;
		
		try {
			boardList = session.selectList("siBoardSearch", board);			
			
		} catch(Exception e) {
			log.info("siDaoImpl boardSearch e.getMessage() : " + e.getMessage());
		}
		
		return boardList;
	}
	
	
	//Search 테이블에 검색 키워드가 있다면 update, 없으면 insert
	@Override
	public void upsertSearch(String keyword) {
		//log.info("siDaoImpl upsertSearch start");
		
		try {
			session.insert("siUpsert", keyword);
			
		} catch(Exception e) {
			log.info("siDaoImpl upsertSearch e.getMessage() : " + e.getMessage());
		}
		
	}

	
	//일간 인기 검색어 구하기
	@Override
	public List<Search> getDailyPopularSearches(String startOfToday, String endOfToday) {
		//log.info("siDaoImpl getDailyPopularSearches Start");
		
		List<Search> dailyPopularKeywords = null;
		TimeDTO timeDto = new TimeDTO();
		timeDto.setStartOfToday(startOfToday);
		timeDto.setEndOfToday(endOfToday);

		try {
			dailyPopularKeywords = session.selectList("siGetPopularSearches", timeDto);
		} catch(Exception e) {
			log.info("siDaoImpl getDailyPopularSearches e.getMessage() : " + e.getMessage());
		}
		
		return dailyPopularKeywords;
	}


	//주간 인기 검색어 구하기
	@Override
	public List<Search> getWeeklyPopularSearches(String startOfWeek, String endOfWeek) {
		//log.info("siDaoImpl getWeeklyPopularSearches Start");
		
		List<Search> weeklyPopularKeywords = null;
		TimeDTO timeDto = new TimeDTO();
		timeDto.setStartOfToday(startOfWeek);
		timeDto.setEndOfToday(endOfWeek);
		
		try {
			weeklyPopularKeywords = session.selectList("siGetPopularSearches", timeDto);
		} catch(Exception e) {
			log.info("siDaoImpl getWeeklyPopularSearches e.getMessage() : " + e.getMessage());
		}
		return weeklyPopularKeywords;
	}


	//월간 인기 검색어 구하기
	@Override
	public List<Search> getMonthlyPopularSearches(String startOfMonth, String endOfMonth) {
		//log.info("siDaoImpl getMonthlyPopularSearches Start");
		
		List<Search> monthlyPopularKeywords = null;
		TimeDTO timeDto = new TimeDTO();
		timeDto.setStartOfToday(startOfMonth);
		timeDto.setEndOfToday(endOfMonth);
		
		try {
			monthlyPopularKeywords = session.selectList("siGetPopularSearches", timeDto);
		} catch(Exception e) {
			log.info("siDaoImpl getMonthlyPopularSearches e.getMessage() : " + e.getMessage());
		}
		return monthlyPopularKeywords;
	}


	//인기 명소 구하기
	@Override
	public List<Travel> getPopularTravel() {
		//log.info("siDaoImpl getPopularTravel Start ");
		List<Travel> popularTravel = null;
		try {
			System.out.println("siDaoImpl getPopularTravel selectList start");
			popularTravel = session.selectList("sigetPopularT");
		} catch(Exception e) {
			log.info("siDaoImpl getPopularTravel e.getMessage() : " + e.getMessage());
		}
		return popularTravel;
	}


	//인기 맛집 구하기
	@Override
	public List<Res> getPopularRes() {
		List<Res> popularRes = null;
		try {
			System.out.println("siDaoImpl getPopularRes selectList start");
			popularRes = session.selectList("sigetPopularR");
		} catch(Exception e) {
			log.info("siDaoImpl getPopularTravel e.getMessage() : " + e.getMessage());
		}
		return popularRes;
	}

	//인기 숙소 구하기
	@Override
	public List<House> getPopularHouse() {
		System.out.println("SiDaoImpl getPopularHouse Start");
		List<House> popularHouse = null;
		try {
			System.out.println("siDaoImpl getPopularHouse selectList start");
			popularHouse = session.selectList("sigetPopularH");
		} catch(Exception e) {
			log.info("siDaoImpl getPopularTravel e.getMessage() : " + e.getMessage());
		}
		return popularHouse;
	}

	
	//여행지 검색 결과 수
	@Override
	public int getTravelCount(Travel travel) {
		int travelListCount = 0;
		
		try {
			travelListCount = session.selectOne("SiGetTravelCount", travel);
		} catch(Exception e) {
			log.info("siDaoImpl getTravelCount e.getMessage() : " + e.getMessage());
		}
		return travelListCount;
	}

	
	//숙소 검색 결과 수
	@Override
	public int getHouseCount(House house) {
		int houseListCount = 0;
		
		try {
			houseListCount = session.selectOne("SiGetHouseCount", house);
		} catch(Exception e) {
			log.info("siDaoImpl getHouseCount e.getMessage() : " + e.getMessage());
		}
		return houseListCount;
		
	}


	//맛집 검색 결과 수
	@Override
	public int getResCount(Res res) {
		int resListCount = 0;
		
		try {
			resListCount = session.selectOne("SiGetResCount", res);
		} catch(Exception e) {
			log.info("siDaoImpl getResCount e.getMessage() : " + e.getMessage());
		}
		return resListCount;
		
	}


	//커뮤니티 검색 결과 수
	@Override
	public int getBoardCount(Board board) {
		int boardListCount = 0;
		
		try {
			boardListCount = session.selectOne("SiGetBoardCount", board);
		} catch(Exception e) {
			log.info("siDaoImpl getBoardCount e.getMessage() : " + e.getMessage());
		}
		return boardListCount;
		
	}


	//최근 검색어 수
	@Override
	public int countRecentSearch(RecentSearch recentSearch) {
		int recentCount = 0;
		try {
			recentCount = session.selectOne("siRecentCount", recentSearch);
		} catch(Exception e) {
			log.info("siDaoImpl countRecentSearch e.getMessage() : " + e.getMessage());
		}
		return recentCount;
	}


	//최근 검색어 중 가장 오래된 검색어 삭제
	@Override
	public void deleteRecentSearch(RecentSearch recentSearch) {
		try {
			session.delete("siDeleteRecent", recentSearch);
		} catch(Exception e) {
			log.info("siDaoImpl deleteRecentSearch e.getMessage() : " + e.getMessage());
		}
		
	}


	//최근 검색어 저장
	@Override
	public void insertRecentSearch(RecentSearch recentSearch) {
		try {
			session.insert("siInsertRecent", recentSearch);
		} catch(Exception e) {
			log.info("siDaoImpl insertRecent e.getMessage() : " + e.getMessage());
		}
		
	}


	//검색어가 테이블에 존재하는지 확인
	@Override
	public int findRecentSearch(RecentSearch recentSearch) {
		int findRecentSearch = 0;
		try {
			findRecentSearch = session.selectOne("siFindRecent", recentSearch);
		} catch(Exception e) {
			log.info("siDaoImpl findRecentSearch e.getMessage() : " + e.getMessage());
		}
		return findRecentSearch;
	}
	
	
	//최근 검색어 목록 반환
	@Override
	public List<RecentSearch> getRecentSearchList(RecentSearch recentSearch) {
		List<RecentSearch> recentSearchList = null;
		try {
			recentSearchList = session.selectList("siRecentList", recentSearch);
		} catch(Exception e) {
			log.info("siDaoImpl getRecentSearchList e.getMessage() : " + e.getMessage());
		}
		return recentSearchList;
	}


}
