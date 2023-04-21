package com.travelAlone.s20230404.service.sm;

import java.util.List;

import com.travelAlone.s20230404.model.Travel;

public interface smService {
	
	int 					totalTravel();
	List<Travel> 			listTravel(Travel travel);
	Travel 					detailTravel(int tid);
	int 					insertTravel(Travel travel);
	int			 			updateTravel(Travel travel);
	int 					deleteTravel(int travel_id);


}
