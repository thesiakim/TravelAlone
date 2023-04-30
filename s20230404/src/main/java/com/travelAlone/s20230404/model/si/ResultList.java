package com.travelAlone.s20230404.model.si;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	
	//데이터 merge
	public List<String> mergeData(List<Map<String, Object>> travelList, List<Map<String, Object>> houseList, List<Map<String, Object>> restaurantList) {
		List<String> data = new ArrayList<>();
		
		if(travelList != null && !travelList.isEmpty()) {
			System.out.println("ResultList mergeData Travel start");
			System.out.println("ResultList mergeData travelList : " + travelList);
			
			for(Map<String, Object> map : travelList) {
				String travel = (String)map.get("t_name");
				data.add(travel);
			}
		}
		if(houseList != null && !houseList.isEmpty()) {
			System.out.println("ResultList mergeData House start");
			
			for(Map<String, Object> map : houseList) {
				String house = (String)map.get("h_name");
				data.add(house);
			}
		}
		if(restaurantList != null && !restaurantList.isEmpty()) {
			System.out.println("ResultList mergeData Res start");
			
			for(Map<String, Object> map : restaurantList) {
				String res = (String)map.get("r_name");
				data.add(res);
			}
		}
		System.out.println("ResultList mergeData return");
		return data;
		
	}
		
	
}
