package com.travelAlone.s20230404.dao.mh;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.House;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class HouseDaoImpl implements HouseDao {

	private final SqlSession session;
	
	
	@Override
	public int totalHouse() {
		int totHouseCount = 0;
		log.info("mhDaoImpl2 Start total...");
		try {
			totHouseCount = session.selectOne("mhHouseTotal");
			log.info("mhDaoImpl2 totalHouse totHouseCount->" + totHouseCount);
		} catch (Exception e) {
			log.info("mhDaoImpl2 totalHouse Exception " +e.getMessage());
		}						
		return totHouseCount;
	}


	@Override
	public List<House> selectHouseList(House house) {
		List<House> houseList = new ArrayList<House>();
		
	try {
		log.info("mhDaoImpl2 selectHouseList mhHouseList Start...");					
		houseList = session.selectList("mhHouseList",house);
		log.info("mhDaoImpl2 selectHouseList mhHouseList End...");
	} catch (Exception e) {
		log.info("mhDaoImpl2 mhHouseList Exception " +e.getMessage());
	}
		return houseList;
	}

	//정보글 자세히보기
	@Override
	public House detailHouse(int hid) {
		log.info("mhDaoImpl2 detail start..");
		House house = new House();
		try {
			house = session.selectOne("mhHouseSelOne", hid);
			log.info("mhDaoImpl2 detail house.getH_name()->"+ house.getH_name());
		} catch (Exception e) {
			log.info("mhDaoImpl2 mhHouseSelOne Exception " +e.getMessage());
		}				
		return house;
	}


	@Override
	public int insertHou(House house) {
		int result = 0;
		log.info("HouseDaoImpl insert Start...");
		try {
			result = session.insert("insertHouse",house);
		} catch (Exception e) {
			log.info("HouseDaoImpl insert Exception" + e.getMessage());
		}
		
		return result;
	}


	@Override
	public int updateHouse(House house) {
		log.info("HouseDaoImpl updateHouse  start");
		int updateCount= 0;
		try {
			updateCount = session.update("mhHouseUpdate",house);
		} catch (Exception e) {
			log.info("HouseDaoImpl updateHouse Exception->"+e.getMessage());
		}		
		
		return updateCount;
	}


	@Override
	public int deleteHouse(int house_id) {
		log.info("HouseDaoImpl delete start..");
		int result = 0;
		log.info("HouseDaoImpl delete house_id->"+ house_id);
		try {
			result = session.delete("deleteHouse",house_id);
			log.info("HouseDaoImpl delete result->"+ result);
		} catch (Exception e) {
			log.info("HouseDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}

	
	
}
