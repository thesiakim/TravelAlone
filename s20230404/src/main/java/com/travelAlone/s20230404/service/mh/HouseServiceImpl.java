package com.travelAlone.s20230404.service.mh;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.mh.HouseDao;
import com.travelAlone.s20230404.model.House;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HouseServiceImpl implements HouseService {
	private final HouseDao mh;

	@Override
	public int totalHouse() {
		log.info("mhServiceImpl2 start totalHouse ");
		int totHouseCnt = mh.totalHouse();
		log.info("mhServiceImpl2  totalHouse totHouseCnt->" + totHouseCnt );		
		return totHouseCnt;
	}

	@Override
	public List<House> listHouse(House house) {
		log.debug("mhServiceImpl2 Start listHouse...");
		List<House> houseList = mh.selectHouseList(house);
		log.debug("mhServiceImpl2 End listHouse...");
		return houseList;
	}

	@Override
	public House detailHouse(int hid) {
		log.info("mhServiceImpl2 detail");
		House house = null;
		house = mh.detailHouse(hid);		
		return house;
	}

	@Override
	public int insertHou(House house) {
		int result = 0;
		log.info("mhServiceImpl2 insert Start...");
		result = mh.insertHou(house);
		return result;
	}

	@Override
	public int updateHouse(House house) {
		log.info("HouseServiceImpl update");
		int updateCount = 0;
		updateCount = mh.updateHouse(house);		
		return updateCount;
	}

	@Override
	public int deleteHouse(int house_id) {
		int result = 0;
		log.info("HouseServiceImpl delete Start");
		result = mh.deleteHouse(house_id);		
		return result;
	}
		
}
