package com.travelAlone.s20230404.dao.mh;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.House;

public interface HouseDao {

	int 					totalHouse();
	List<House> 			selectHouseList(House house);
	House 					detailHouse(int hid);
	int 					insertHou(House house);
	int 					updateHouse(House house);
	int 					deleteHouse(int house_id);
	//검색
	int 					condHouseCnt(House house);
	List<House> 			houseSearchList(House house);
	
	//필터링  숙소구분
	List<CommonCode> 		getCommonCode();
	int 					condOptionInqCnt(String code);
	List<House> 			mhOptionHouseList(House house);
	//지역구분
	List<CommonCode> 		getCommonLocCode();
	List<House> 			mhOptionLocList(House house);
	int 					condOptionLocCnt(String code);

	
	
	
	
	
	
	
			
	
	
	
	
}
