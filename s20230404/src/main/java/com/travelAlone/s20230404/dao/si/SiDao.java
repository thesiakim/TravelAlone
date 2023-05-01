package com.travelAlone.s20230404.dao.si;

import java.util.List;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.model.si.Search;

public interface SiDao {
	
	void             upsertSearch(String keyword);
	List<House>      houseSearch(String keyword);
	List<Travel>     travelSearch(String keyword);
	List<Res>        resSearch(String keyword);
	List<Board>      boardSearch(String keyword);
	List<Search>     getDailyPopularSearches(String startOfToday, String endOfToday);
	List<Search>     getWeeklyPopularSearches(String startOfWeek, String endOfWeek);
	List<Search>     getMonthlyPopularSearches(String startOfMonth, String endOfMonth);
	List<Travel>     getPopularTravel();
	List<Res>        getPopularRes();
	List<House>      getPopularHouse();
	
	

}
