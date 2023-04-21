package com.travelAlone.s20230404.service.sm;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.sm.TravelDao;
import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Travel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelServiceImpl implements TravelService {
	private final TravelDao sm;

	@Override
	public int totalTravel() {
		log.info("smServiceImpl start totalTravel ");
		int totTravelCnt = sm.totalTravel();
		log.info("smServiceImpl  totalTravel totTravelCnt->" + totTravelCnt );		
		return totTravelCnt;
	}

	@Override
	public List<Travel> listTravel(Travel travel) {
		log.debug("smServiceImpl Start listTravel...");
		List<Travel> travelList = sm.selectTravelList(travel);
		log.debug("smServiceImpl End listTravel...");
		return travelList;
	}

	@Override
	public Travel detailTravel(int tid) {
		log.info("smServiceImpl detail");
		Travel travel = null;
		travel = sm.detailTravel(tid);		
		return travel;
	}

	@Override
	public int insertTravel(Travel travel) {
		int result = 0;
		log.info("smServiceImpl insert Start...");
		result = sm.insertTravel(travel);
		return result;
	}

	@Override
	public int updateTravel(Travel travel) {
		log.info("TravelServiceImpl update");
		int updateCount = 0;
		updateCount = sm.updateTravel(travel);		
		return updateCount;
	}

	@Override
	public int deleteTravel(int travel_id) {
		int result = 0;
		log.info("TravelServiceImpl delete Start");
		result = sm.deleteTravel(travel_id);		
		return result;
	}

	@Override
	public int conditionTravelCount(Travel travel) {
		log.info("TravelServiceImpl  conditionTravelCount Start" );
		int conditionTravelCnt = sm.condTravelCnt(travel);
		log.info("TravelServiceImpl  conditionTravelCount conditionTravelCnt" + conditionTravelCnt);						
		return conditionTravelCnt;
	}

	@Override
	public List<Travel> listSearchTravel(Travel travel) {
		List<Travel> travelSearchList = null;
		log.info("TravelServiceImpl listSearchTravel Start...");
		travelSearchList = sm.travelSearchList(travel);
		log.info("TravelServiceImpl listSearchTravel inquireTravelList.size()"+ travelSearchList.size());						
		return travelSearchList;
	}

	//공통여행지코드 리스트
	@Override
	public List<CommonCode> getCommonCode() {
		List<CommonCode> result = sm.getCommonCode();		
		return result;
	}
		//여행지종류갯수
	@Override
	public int conditionOptionCount(String code) {
		log.info("TravelServiceImpl  conditionOptionCount Start" );
		int conditionInquireCnt = sm.condOptionInqCnt(code);
		log.info("TravelServiceImpl  conditionOptionCount conditionInquireCnt" + conditionInquireCnt);	
		return conditionInquireCnt;
	}

	
	@Override
	public List<Travel> listFilterOptionTravel(Travel travel) {
		List<Travel> travelOptionFilterList = null;
		log.info("TravelServiceImpl listFilterOptionTravel Start...");
		travelOptionFilterList = sm.smOptionTravelList(travel);
		log.info("TravelServiceImpl listFilterOptionTravel travelOptionFilterList.size()"+ travelOptionFilterList.size());		
		return travelOptionFilterList;
	}

	
	
	
	//지역 코드가져오기
	@Override
	public List<CommonCode> getCommonLocCode() {
		List<CommonCode> result = sm.getCommonLocCode();
		return result;
	}

	@Override
	public List<Travel> listFilterOptionLoc(Travel travel) {
		List<Travel> locOptionFilterList = null;
		log.info("TravelServiceImpl listFilterOptionLoc Start...");
		locOptionFilterList = sm.smOptionLocList(travel);
		log.info("TravelServiceImpl listFilterOptionLoc locOptionFilterList.size()"+ locOptionFilterList.size());			
		return locOptionFilterList;
	}

	@Override
	public int conditionOptionLocCount(String code) {
		log.info("TravelServiceImpl  conditionOptionLocCount Start" );
		int conditionLocCnt = sm.condOptionLocCnt(code);
		log.info("TravelServiceImpl  conditionOptionLocCount conditionLocCnt" + conditionLocCnt);	
		return conditionLocCnt;
	}


	
	
	
}
