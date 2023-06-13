package com.travelAlone.s20230404.model.si;

import java.util.List;


import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Travel;

import lombok.Data;

@Data
public class ResultList {
	
	private List<House> houseList;
	private List<Travel> travelList;
	private List<Res> restaurantList;
	private List<Board> boardList;
	
	//데이터 개수 반환
	public int getResultCount() {
		int totalSize = 0;
		
		if (travelList != null) totalSize += travelList.size();
	    if (houseList != null) totalSize += houseList.size();
	    if (boardList != null) totalSize += boardList.size();
	    if (restaurantList != null) totalSize += restaurantList.size();
	    
	    return totalSize;
	}
	
}
