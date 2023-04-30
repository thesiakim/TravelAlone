package com.travelAlone.s20230404.service.sm;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.sm.TravelDao;
import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Tra_Img;
import com.travelAlone.s20230404.model.Tra_Rev;
import com.travelAlone.s20230404.model.Travel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelServiceImpl implements TravelService {
	private final TravelDao sm;

	@Override
	public int traTotal() {
		log.info("smServiceImpl start totalTravel ");
		int totTravelCnt = sm.traTotal();
		log.info("smServiceImpl  totalTravel totTravelCnt->" + totTravelCnt );		
		return totTravelCnt;
	}
	//숙소 리스트
	@Override
	public List<Travel> traList(Travel travel) {
		log.debug("smServiceImpl Start traList...");
		List<Travel> travelList = sm.traList(travel);
		log.debug("smServiceImpl End traList...");
		return travelList;
	}

	@Override
	public Travel traDetail(int tid) {
		log.info("smServiceImpl detail");
		Travel travel = null;
		travel = sm.traDetail(tid);		
		return travel;
	}
	//숙소정보작성
	@Override
	public int traInsert(Travel travel) {
		int result = 0;
		log.info("smServiceImpl insert Start...");
		result = sm.traInsert(travel);
		return result;
	}

	@Override
	public int traUpdate(Travel travel) {
		log.info("TravelServiceImpl update");
		int updateCount = 0;
		updateCount = sm.traUpdate(travel);		
		return updateCount;
	}

	@Override
	public int traDelete(int travel_id) {
		int result = 0;
		log.info("TravelServiceImpl delete Start");
		result = sm.traDelete(travel_id);		
		return result;
	}

	@Override
	public int traSearch(Travel travel) {
		log.info("TravelServiceImpl  traSearch Start" );
		int conditionTravelCnt = sm.traSearch(travel);
		log.info("TravelServiceImpl  traSearch conditionTravelCnt" + conditionTravelCnt);						
		return conditionTravelCnt;
	}

	@Override
	public List<Travel> traSearchList(Travel travel) {
		List<Travel> travelSearchList = null;
		log.info("TravelServiceImpl traSearchList Start...");
		travelSearchList = sm.traSearchList(travel);
		log.info("TravelServiceImpl traSearchList inquireTravelList.size()"+ travelSearchList.size());						
		return travelSearchList;
	}

	//공통숙소코드 리스트
	@Override
	public List<CommonCode> getCommonCode() {
		List<CommonCode> result = sm.getCommonCode();		
		return result;
	}
		//숙소종류갯수
	@Override
	public int traFilter(String code) {
		log.info("TravelServiceImpl  traFilter Start" );
		int conditionInquireCnt = sm.traFilter(code);
		log.info("TravelServiceImpl  traFilter conditionInquireCnt" + conditionInquireCnt);	
		return conditionInquireCnt;
	}

	
	@Override
	public List<Travel> optTraList(Travel travel) {
		List<Travel> travelOptionFilterList = null;
		log.info("TravelServiceImpl optTraList Start...");
		travelOptionFilterList = sm.optTraList(travel);
		log.info("TravelServiceImpl optTraList travelOptionFilterList.size()"+ travelOptionFilterList.size());		
		return travelOptionFilterList;
	}

	
	
	
	//지역 코드가져오기
	@Override
	public List<CommonCode> getCommonLocCode() {
		List<CommonCode> result = sm.getCommonLocCode();
		return result;
	}

	@Override
	public int traLocFilter(String code) {
		log.info("TravelServiceImpl  traLocFilter Start" );
		int conditionLocCnt = sm.traLocFilter(code);
		log.info("TravelServiceImpl  traLocFilter conditionLocCnt" + conditionLocCnt);	
		return conditionLocCnt;
	}


	@Override
	public List<Travel> traLocList(Travel travel) {
		List<Travel> locOptionFilterList = null;
		log.info("TravelServiceImpl traLocList Start...");
		locOptionFilterList = sm.traLocList(travel);
		log.info("TravelServiceImpl traLocList locOptionFilterList.size()"+ locOptionFilterList.size());			
		return locOptionFilterList;
	}
	
	
	//==========리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰====================================
	@Override
	public List<Tra_Rev> traRevList(int tid) {
		log.debug("TravelServiceImpl traRevList Start...");
		List<Tra_Rev> traRevList = sm.traRevList(tid);
		
		log.debug("TravelServiceImpl traRevList End...");
		return traRevList;
	}
	//리뷰작성
	@Override
	public int traRevInsert(Tra_Rev tra_Rev) {
		int result = 0;
		log.info("TravelServiceImpl traRevInsert Start...");
		result = sm.traRevInsert(tra_Rev);
		return result;
	}
	
	
	@Override
	public int traRevUpdate(Tra_Rev tra_Rev) {
		log.info("TravelServiceImpl traRevUpdate Start...");
		int updateCount = 0;
		updateCount = sm.traRevUpdate(tra_Rev);	
		return updateCount;
	}
	@Override
	public int traRevDelete(int review_id) {
		int result = 0;
		log.info("TravelServiceImpl traRevDelete Start");
		result = sm.traRevDelete(review_id);		
		return result;
	}

	//이미지 업로드 이미지 업로드 이미지 업로드 이미지 업로드 이미지 업로드 이미지 업로드 이미지 업로드
	@Override
	public int traImgInsert(Tra_Img tra_Img) {
		int result = 0;
		log.info("TravelServiceImpl traImgInsert Start..." );
		result = sm.traImgInsert(tra_Img);
		return result;
		
	}
	@Override
	public int traSeq(Travel travel) {
		int result = 0;
		log.info("TravelServiceImpl traSeq Start..." );
		result = sm.traSeq(travel);
		return result;
	}
	@Override
	public List<Tra_Img> traImgList(Tra_Img tra_Img) {
		log.info("TravelServiceImpl traImgList Start");
		List<Tra_Img> traImgList = sm.traImgList(tra_Img);						
		return traImgList;
	}
	@Override
	public int traImgDelete(int travel_id) {
		int result = 0;
		log.info("TravelServiceImpl traImgDelete Start");
		result = sm.traImgDelete(travel_id);		
		return result;
	}	
}

