package com.travelAlone.s20230404.dao.sm;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Travel;

public interface TravelDao {

	int 					totalTravel();
	List<Travel> 			selectTravelList(Travel travel);
	Travel 					detailTravel(int tid);
	int 					insertTravel(Travel travel);
	int 					updateTravel(Travel travel);
	int 					deleteTravel(int travel_id);
	//검색
	int 					condTravelCnt(Travel travel);
	List<Travel> 			travelSearchList(Travel travel);
	
	//필터링  숙소구분
	List<CommonCode> 		getCommonCode();
	int 					condOptionInqCnt(String code);
	List<Travel> 			smOptionTravelList(Travel travel);
	//지역구분
	List<CommonCode> 		getCommonLocCode();
	List<Travel> 			smOptionLocList(Travel travel);
	int 					condOptionLocCnt(String code);

	
	
	
	
	
	
	
			
	
	
	
	
}
