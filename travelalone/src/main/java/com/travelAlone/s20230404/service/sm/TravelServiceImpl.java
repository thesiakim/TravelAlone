package com.travelAlone.s20230404.service.sm;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.sm.TravelDao;
import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Tra_Fav;
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
//		log.info("smServiceImpl start totalTravel ");
		int totTravelCnt = sm.traTotal();
//		log.info("smServiceImpl  totalTravel totTravelCnt->" + totTravelCnt );		
		return totTravelCnt;
	}
	//여행지 리스트
	@Override
	public List<Travel> traList(Travel travel) {
//		log.debug("smServiceImpl Start traList...");
		List<Travel> travelList = sm.traList(travel);
//		log.debug("smServiceImpl End traList...");
		return travelList;
	}

	//여행지 정보 조회
	@Override
	public Travel traDetail(int tid) {
//		log.info("smServiceImpl detail");
		Travel travel = null;
		travel = sm.traDetail(tid);		
		return travel;
	}
	//여행지 정보 작성
	@Override
	public int traInsert(Travel travel) {
		int result = 0;
//		log.info("smServiceImpl insert Start...");
		result = sm.traInsert(travel);
		return result;
	}

	//여행지 정보 수정
	@Override
	public int traUpdate(Travel travel) {
//		log.info("TravelServiceImpl update");
		int updateCount = 0;
		updateCount = sm.traUpdate(travel);		
		return updateCount;
	}

	//여행지 정보 삭제
	@Override
	public int traDelete(int travel_id) {
		int result = 0;
//		log.info("TravelServiceImpl delete Start");
		result = sm.traDelete(travel_id);		
		return result;
	}

	@Override
	public int traSearch(Travel travel) {
//		log.info("TravelServiceImpl  traSearch Start" );
		int conditionTravelCnt = sm.traSearch(travel);
//		log.info("TravelServiceImpl  traSearch conditionTravelCnt" + conditionTravelCnt);						
		return conditionTravelCnt;
	}

	@Override
	public List<Travel> traSearchList(Travel travel) {
		List<Travel> travelSearchList = null;
//		log.info("TravelServiceImpl traSearchList Start...");
		travelSearchList = sm.traSearchList(travel);
//		log.info("TravelServiceImpl traSearchList inquireTravelList.size()"+ travelSearchList.size());						
		return travelSearchList;
	}

	//공통 여행지코드 리스트
	@Override
	public List<CommonCode> traCommonCode() {
		List<CommonCode> result = sm.traCommonCode();		
		return result;
	}
	
	//여행지 종류 갯수
	@Override
	public int traFilter(String code) {
//		log.info("TravelServiceImpl  traFilter Start" );
		int traFilter = sm.traFilter(code);
//		log.info("TravelServiceImpl  traFilter->" + traFilter);	
		return traFilter;
	}

	
	@Override
	public List<Travel> traOptList(Travel travel) {
		List<Travel> traOptList = null;
//		log.info("TravelServiceImpl traOptList Start...");
		traOptList = sm.traOptList(travel);
//		log.info("TravelServiceImpl traOptList traOptList.size()->"+ traOptList.size());		
		return traOptList;
	}

	//지역 코드 가져오기
	@Override
	public List<CommonCode> traCommonLocCode() {
		List<CommonCode> result = sm.traCommonLocCode();
		return result;
	}

	@Override
	public List<Travel> traLocList(Travel travel) {
		List<Travel> traLocList = null;
//		log.info("TravelServiceImpl traLocList Start...");
		traLocList = sm.traLocList(travel);
//		log.info("TravelServiceImpl traLocList traLocList.size()"+ traLocList.size());			
		return traLocList;
	}
	
	@Override
	public int traLocFilter(String code) {
//		log.info("TravelServiceImpl  traLocFilter Start" );
		int traLocFilter = sm.traLocFilter(code);
//		log.info("TravelServiceImpl  traLocFilter" + traLocFilter);	
		return traLocFilter;
	}

	
//====================================리뷰====================================
	@Override
	public int totalTraRev(int tid) {
		log.info("TravelServiceImpl start totalTraRev ");
		log.info("travel_id->"+ tid);
		int totTraRevCnt  = sm.totalTraRev(tid);
		log.info("TravelServiceImpl  traRevTotal->" + totTraRevCnt );		
		return totTraRevCnt;
	}
	
	@Override
	public List<Tra_Rev> traRevList(Tra_Rev tra_Rev) {
//		log.debug("TravelServiceImpl traRevList Start...");
		List<Tra_Rev> traRevList = sm.traRevList(tra_Rev);
		
//		log.debug("TravelServiceImpl traRevList End...");
		return traRevList;
	}
	
	//리뷰 작성
	@Override
	public int traRevInsert(Tra_Rev tra_Rev) {
		int result = 0;
//		log.info("TravelServiceImpl traRevInsert Start...");
		result = sm.traRevInsert(tra_Rev);
		return result;
	}
	
	//리뷰 수정
	@Override
	public int traRevUpdate(Tra_Rev tra_Rev) {
//		log.info("TravelServiceImpl traRevUpdate Start...");
		int updateCount = 0;
		updateCount = sm.traRevUpdate(tra_Rev);	
		return updateCount;
	}
	
	//리뷰 삭제
	@Override
	public int traRevDelete(int review_id) {
		int result = 0;
//		log.info("TravelServiceImpl traRevDelete Start");
		result = sm.traRevDelete(review_id);		
		return result;
	}
	
	//리뷰 전체 삭제
	@Override
	public int traRevDelAll(int travel_id) {
		int result = 0;
//		log.info("TravelServiceImpl traRevDelAll Start");
		result =sm.traRevDelAll(travel_id);
		return result;
	}

	
//====================================이미지 업로드====================================
	//이미지 삽입
	@Override
	public int traImgInsert(Tra_Img tra_Img) {
		int result = 0;
//		log.info("TravelServiceImpl traImgInsert Start..." );
		result = sm.traImgInsert(tra_Img);
		return result;
		
	}
	
	@Override
	public int traSeq(Travel travel) {
		int result = 0;
//		log.info("TravelServiceImpl traSeq Start..." );
		result = sm.traSeq(travel);
		return result;
	}
	
	@Override
	public List<Tra_Img> traImgList(Tra_Img tra_Img) {
//		log.info("TravelServiceImpl traImgList Start");
		List<Tra_Img> traImgList = sm.traImgList(tra_Img);						
		return traImgList;
	}
	
	//이미지 삭제
	@Override
	public int traImgDelete(int travel_id) {
		int result = 0;
//		log.info("TravelServiceImpl traImgDelete Start");
		result = sm.traImgDelete(travel_id);		
		return result;
	}	
	
	//이미지 개별 삭제
	@Override
	public int traOneImgDelete(int travel_id,int img_id) {
		int result = 0;
//		log.info("TravelServiceImpl traOneImgDelet Start");
		result = sm.traOneImgDelete(travel_id,img_id);		
		return result;
	}
	
	
//====================================즐겨찾기====================================
	//즐겨찾기 추가
	@Override
	public int insertTraFav(Tra_Fav tra_Fav) {
		int result = 0;
//		log.info("TravelServiceImpl insert Start");
		result = sm.insertTraFav(tra_Fav);
		return result;
	}
	
	//즐겨찾기 해제
	@Override
	public int deleteTraFav(Tra_Fav tra_Fav) {
		int result = 0;
//		log.info("TravelServiceImpl delete Start");
		result = sm.deleteTraFav(tra_Fav);						
		return result;
	}
	
	@Override
	public int isTra_Fav(Tra_Fav tra_Fav) {
//		log.info("TravelServiceImpl isTra_Fav Start");
		int traFavList = sm.selectTraFav(tra_Fav);
		return traFavList;
	}

}

