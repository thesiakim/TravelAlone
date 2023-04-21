package com.travelAlone.s20230404.service.mh;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.House;

public interface HouseService {

	int 					totalHouse();
	List<House> 			listHouse(House house);
	House 					detailHouse(int hid);
	int 					insertHou(House house);
	int			 			updateHouse(House house);
	int 					deleteHouse(int house_id);
	int 					conditionHouseCount(House house);
	List<House>				 listSearchHouse(House house);
	
	//공통코드 활용(숙소종류)
	List<CommonCode> 		getCommonCode();
	int 					conditionOptionCount(String code);
	List<House> 			listFilterOptionHouse(House house);
	
	
	//공통코드 활용(지역종류)
	List<CommonCode> 		getCommonLocCode();	
	int 					conditionOptionLocCount(String code);
	List<House> 			listFilterOptionLoc(House house);

	
	
	
	
	
	
	
	
	
}
