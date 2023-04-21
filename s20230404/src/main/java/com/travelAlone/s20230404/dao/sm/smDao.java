package com.travelAlone.s20230404.dao.sm;

import java.util.List;

import com.travelAlone.s20230404.model.Travel;

public interface smDao {
    int						totalTravel();
    List<Travel> 			selectTravelList(Travel travel);
    Travel					detailTravel(int tid);
    int						insertTravel(Travel travel);
    int 					updateTravel(Travel travel);
	int 					deleteTravel(int travel_id);
	
}

	
