package com.travelAlone.s20230404.dao.sm;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Travel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TravelDaoImpl implements TravelDao {

	private final SqlSession session;
	
	
	@Override
	public int totalTravel() {
		int totTravelCount = 0;
		log.info("smDaoImpl Start total...");
		try {
			totTravelCount = session.selectOne("smTravelTotal");
			log.info("smDaoImpl totalTravel totTravelCount->" + totTravelCount);
		} catch (Exception e) {
			log.info("smDaoImpl totalTravel Exception " +e.getMessage());
		}						
		return totTravelCount;
	}


	@Override
	public List<Travel> selectTravelList(Travel travel) {
		List<Travel> travelList = new ArrayList<Travel>();
		
	try {
		log.info("smDaoImpl selectTravelList smTravelList Start...");					
		travelList = session.selectList("smTravelList",travel);
		log.info("smDaoImpl selectTravelList smTravelList End...");
	} catch (Exception e) {
		log.info("smDaoImpl smTravelList Exception " +e.getMessage());
	}
		return travelList;
	}

	//정보글 자세히보기
	@Override
	public Travel detailTravel(int tid) {
		log.info("smDaoImpl detail start..");
		Travel travel = new Travel();
		try {
			travel = session.selectOne("smTravelSelOne", tid);
			log.info("smDaoImpl detail travel.getT_name()->"+ travel.getT_name());
		} catch (Exception e) {
			log.info("smDaoImpl smTravelSelOne Exception " +e.getMessage());
		}				
		return travel;
	}


	@Override
	public int insertTravel(Travel travel) {
		int result = 0;
		log.info("TravelDaoImpl insert Start...");
		try {
			result = session.insert("insertTravel",travel);
		} catch (Exception e) {
			log.info("TravelDaoImpl insert Exception" + e.getMessage());
		}
		
		return result;
	}


	@Override
	public int updateTravel(Travel travel) {
		log.info("TravelDaoImpl updateTravel  start");
		int updateCount= 0;
		try {
			updateCount = session.update("smTravelUpdate",travel);
		} catch (Exception e) {
			log.info("TravelDaoImpl updateTravel Exception->"+e.getMessage());
		}		
		
		return updateCount;
	}


	@Override
	public int deleteTravel(int travel_id) {
		log.info("TravelDaoImpl delete start..");
		int result = 0;
		log.info("TravelDaoImpl delete travel_id->"+ travel_id);
		try {
			result = session.delete("deleteTravel",travel_id);
			log.info("TravelDaoImpl delete result->"+ result);
		} catch (Exception e) {
			log.info("TravelDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}


	//숙소검색결과갯수
	@Override
	public int condTravelCnt(Travel travel) {
		int conditionTravelCount = 0;
		log.info("TravelDaoImpl Start total...");
		try {
			conditionTravelCount = session.selectOne("condTravelCnt",travel);
			log.info("TravelDaoImpl conditionInquireCount->"+conditionTravelCount);		
		} catch (Exception e) {
			log.info("TravelDaoImpl Exception"+ e.getMessage());
		}			
		return conditionTravelCount;
	}

	
	//숙소 검색결과
	@Override
	public List<Travel> travelSearchList(Travel travel) {
		List<Travel> travelSearchList = null;
		log.info("TravelDaoImpl travelSearchList start");
		try {
			travelSearchList = session.selectList("smtravelSearchList", travel);
		} catch (Exception e) {
			log.info("TravelDaoImpl travelSearchList Exception " + e.getMessage());
		}
		
		return travelSearchList;
	}

	
	
	
	
	//숙소필터구분
	@Override
	public List<CommonCode> getCommonCode() {
		log.info("getCommonCode 호출부 .......");
		List<CommonCode> result = session.selectList("travelCommonCode");
		log.info("getCommonCode data {},{} .......",result.get(0).getCode(),result.get(0).getValue());
		return result;
	}

	//숙소 필터갯수
	@Override
	public int condOptionInqCnt(String code) {
		int count = 0;		
		log.info("TravelDaoImpl Start total...");
		try {
			count = session.selectOne("condOptionTravelCnt",code);
			log.info("TravelDaoImpl condOptionInquireCnt->"+count);		
		} catch (Exception e) {
			log.info("TravelDaoImpl Exception"+ e.getMessage());
		}			
		return count;
	}

	//숙소필터
	@Override
	public List<Travel> smOptionTravelList(Travel travel) {
		List<Travel> travelFilterList = null;
		log.info("TravelDaoImpl travelFilterList start");
		try {
			travelFilterList = session.selectList("smOptionTravelList",travel);

		} catch (Exception e) {
			log.info("TravelDaoImpl travelFilterList Exception " + e.getMessage());
		}

		return travelFilterList;
	}

	//지역코드가져오기
	@Override
	public List<CommonCode> getCommonLocCode() {
		log.info("getCommonLocCode 호출부 .......");
		List<CommonCode> result = session.selectList("locCommonCode");
		log.info("getCommonLocCode data {},{} .......",result.get(0).getCode(),result.get(0).getValue());
		return result;
	}


	@Override
	public List<Travel> smOptionLocList(Travel travel) {
		List<Travel> locFilterList = null;
		log.info("TravelDaoImpl locFilterList start");
		try {
			locFilterList = session.selectList("smOptionLocList",travel);
			
		} catch (Exception e) {
			log.info("TravelDaoImpl locFilterList Exception " + e.getMessage());
		}
		
		return locFilterList;
	}


	
	@Override
	public int condOptionLocCnt(String code) {
		int count = 0;
		log.info("TravelDaoImpl Start total");
		try {
			count = session.selectOne("condOptionLocCnt",code);
			log.info("TravelDaoImpl condOptionLocCnt->"+count);		
		} catch (Exception e) {
			log.info("TravelDaoImpl Exception"+ e.getMessage());
		}
		
		return count;
	}


	
	
	
}
