package com.travelAlone.s20230404.service.sm;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Travel;

public interface TravelService {

	int 					totalTravel();
	List<Travel> 			listTravel(Travel travel);
	Travel 					detailTravel(int tid);
	int 					insertTravel(Travel travel);
	int			 			updateTravel(Travel travel);
	int 					deleteTravel(int travel_id);
	int 					conditionTravelCount(Travel travel);
	List<Travel>			listSearchTravel(Travel travel);
	
	//공통코드 활용(여행지종류)
	List<CommonCode> 		getCommonCode();
	int 					conditionOptionCount(String code);
	List<Travel> 			listFilterOptionTravel(Travel travel);
	
	
	//공통코드 활용(지역종류)
	List<CommonCode> 		getCommonLocCode();	
	int 					conditionOptionLocCount(String code);
	List<Travel> 			listFilterOptionLoc(Travel travel);

	
	
	
	
	
	
	
	
	
}
