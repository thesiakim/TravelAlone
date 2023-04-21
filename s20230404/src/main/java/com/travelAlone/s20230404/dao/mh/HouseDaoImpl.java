package com.travelAlone.s20230404.dao.mh;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.CommonCode;
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


	//숙소검색결과갯수
	@Override
	public int condHouseCnt(House house) {
		int conditionHouseCount = 0;
		log.info("HouseDaoImpl Start total...");
		try {
			conditionHouseCount = session.selectOne("condHouseCnt",house);
			log.info("HouseDaoImpl conditionInquireCount->"+conditionHouseCount);		
		} catch (Exception e) {
			log.info("HouseDaoImpl Exception"+ e.getMessage());
		}			
		return conditionHouseCount;
	}

	
	//숙소 검색결과
	@Override
	public List<House> houseSearchList(House house) {
		List<House> houseSearchList = null;
		log.info("HouseDaoImpl houseSearchList start");
		try {
			houseSearchList = session.selectList("mhhouseSearchList", house);
		} catch (Exception e) {
			log.info("HouseDaoImpl houseSearchList Exception " + e.getMessage());
		}
		
		return houseSearchList;
	}

	
	
	
	
	//숙소필터구분
	@Override
	public List<CommonCode> getCommonCode() {
		log.info("getCommonCode 호출부 .......");
		List<CommonCode> result = session.selectList("houseCommonCode");
		log.info("getCommonCode data {},{} .......",result.get(0).getCode(),result.get(0).getValue());
		return result;
	}

	//숙소 필터갯수
	@Override
	public int condOptionInqCnt(String code) {
		int count = 0;		
		log.info("HouseDaoImpl Start total...");
		try {
			count = session.selectOne("condOptionHouseCnt",code);
			log.info("HouseDaoImpl condOptionInquireCnt->"+count);		
		} catch (Exception e) {
			log.info("HouseDaoImpl Exception"+ e.getMessage());
		}			
		return count;
	}

	//숙소필터
	@Override
	public List<House> mhOptionHouseList(House house) {
		List<House> houseFilterList = null;
		log.info("HouseDaoImpl houseFilterList start");
		try {
			houseFilterList = session.selectList("mhOptionHouseList",house);

		} catch (Exception e) {
			log.info("HouseDaoImpl houseFilterList Exception " + e.getMessage());
		}

		return houseFilterList;
	}

	//지역코드가져오기
	@Override
	public List<CommonCode> getCommonLocCode() {
		log.info("getCommonLocCode 호출부 .......");
		List<CommonCode> result = session.selectList("locCommonCode");
		log.info("getCommonLocCode data {},{} .......",result.get(0).getCode(),result.get(0).getValue());
		return result;
	}


	@Override
	public List<House> mhOptionLocList(House house) {
		List<House> locFilterList = null;
		log.info("HouseDaoImpl locFilterList start");
		try {
			locFilterList = session.selectList("mhOptionLocList",house);
			
		} catch (Exception e) {
			log.info("HouseDaoImpl locFilterList Exception " + e.getMessage());
		}
		
		return locFilterList;
	}


	
	@Override
	public int condOptionLocCnt(String code) {
		int count = 0;
		log.info("HouseDaoImpl Start total");
		try {
			count = session.selectOne("condOptionLocCnt",code);
			log.info("HouseDaoImpl condOptionLocCnt->"+count);		
		} catch (Exception e) {
			log.info("HouseDaoImpl Exception"+ e.getMessage());
		}
		
		return count;
	}


	
	
	
}
