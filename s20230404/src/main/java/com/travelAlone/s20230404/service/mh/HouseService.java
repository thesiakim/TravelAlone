package com.travelAlone.s20230404.service.mh;

import java.util.List;

import com.travelAlone.s20230404.model.House;

public interface HouseService {

	int 					totalHouse();
	List<House> 			listHouse(House house);
	House 					detailHouse(int hid);
	int 					insertHou(House house);
	int			 			updateHouse(House house);
	int 					deleteHouse(int house_id);

	
	
}
