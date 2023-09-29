package com.travelAlone.s20230404.dao.si;

import java.util.List;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.model.si.RecentSearch;
import com.travelAlone.s20230404.model.si.Search;

public interface SiDao {
	
	void             	   upsertSearch(String keyword);
	List<House>      	   houseSearch(House house);
	List<Travel>     	   travelSearch(Travel travel);
	List<Res>        	   resSearch(Res res);
	List<Board>      	   boardSearch(Board board);
	List<Search>     	   getDailyPopularSearches(String startOfToday, String endOfToday);
	List<Search>     	   getWeeklyPopularSearches(String startOfWeek, String endOfWeek);
	List<Search>     	   getMonthlyPopularSearches(String startOfMonth, String endOfMonth);
	List<Travel>     	   getPopularTravel();
	List<Res>              getPopularRes();
	List<House>      	   getPopularHouse();
	int              	   getTravelCount(Travel travel);
	int              	   getHouseCount(House house);
	int              	   getResCount(Res res);
	int              	   getBoardCount(Board board);
	int              	   countRecentSearch(RecentSearch recentSearch);
	void             	   deleteRecentSearch(RecentSearch recentSearch);
	void             	   insertRecentSearch(RecentSearch recentSearch);
	List<RecentSearch>     getRecentSearchList(RecentSearch recentSearch);
	int 			       findRecentSearch(RecentSearch recentSearch);

	

}
