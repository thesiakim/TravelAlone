package com.travelAlone.s20230404.dao.mh;

import java.util.List;

import com.travelAlone.s20230404.model.House;

public interface HouseDao {

	int 					totalHouse();
	List<House> 			selectHouseList(House house);
	House 					detailHouse(int hid);
	int 					insertHou(House house);
	int 					updateHouse(House house);
	int 					deleteHouse(int house_id);

			
	
	
	
}
