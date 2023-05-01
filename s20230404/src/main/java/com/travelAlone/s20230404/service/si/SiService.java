package com.travelAlone.s20230404.service.si;

import java.util.List;

import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.model.si.ResultList;

public interface SiService {

	ResultList     search(String keyword, String category);
	void           upsertSearch(String keyword);
	List<String>   getDailyPopularSearches();
	List<String>   getWeeklyPopularSearches();
	List<String>   getMonthlyPopularSearches();
	List<Travel>   getPopularTravel();
	List<Res>      getPopularRes();
	List<House>    getPopularHouse();
	
	

}
