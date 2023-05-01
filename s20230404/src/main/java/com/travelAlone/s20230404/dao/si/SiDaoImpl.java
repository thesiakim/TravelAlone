package com.travelAlone.s20230404.dao.si;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Travel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SiDaoImpl implements SiDao {
	
	private final Logger logger = LoggerFactory.getLogger(SiDao.class);
	private final SqlSession session;
	
	//Travel 테이블에 검색 키워드가 있는지 조회
	@Override
	public List<Travel> travelSearch(String keyword) {
		logger.info("siDaoImpl travelSearch start");
		List<Travel> travelList = null;
		
		try {
			//Travel 테이블에 검색 키워드가 있는지 조회한 뒤 결과를 List<Travel>로 반환
			travelList = session.selectList("siTravelSearch", keyword);
			
			//Travel 테이블에 검색 키워드가 존재한다면 해당 키워드에 대해  t_count ++
			if(!travelList.isEmpty()) {
				session.update("siTravelUpdate", keyword);
			}
			
		} catch(Exception e) {
			logger.info("siDaoImpl houseSearch e.getMessage() : " + e.getMessage());
		}
		return travelList;
	}
	
	
	//House 테이블에 검색 키워드가 있는지 조회
	@Override
	public List<House> houseSearch(String keyword) {
		logger.info("siDaoImpl houseSearch start");
		List<House> houseList = null;
		
		try {
			houseList = session.selectList("siHouseSearch", keyword);
			
			if(!houseList.isEmpty()) {
				session.update("siHouseUpdate", keyword);
			}
			
		} catch(Exception e) {
			logger.info("siDaoImpl houseSearch e.getMessage() : " + e.getMessage());
		}
		return houseList;
	}
	


	//Restaurant 테이블에 검색 키워드가 있는지 조회
	@Override
	public List<Res> resSearch(String keyword) {
		logger.info("siDaoImpl resSearch start");
		List<Res> resList = null;
		
		try {
			resList = session.selectList("siResSearch", keyword);	
			
			if(!resList.isEmpty()) {
				session.update("siResUpdate", keyword);
			}
			
		} catch(Exception e) {
			logger.info("siDaoImpl houseSearch e.getMessage() : " + e.getMessage());
		}
		return resList;
	}


	//Board 테이블에 검색 키워드가 있는지 조회
	@Override
	public List<Board> boardSearch(String keyword) {
		logger.info("siDaoImpl boardSearch start");
		List<Board> boardList = null;
		
		try {
			boardList = session.selectList("siBoardSearch", keyword);			
			
		} catch(Exception e) {
			logger.info("siDaoImpl houseSearch e.getMessage() : " + e.getMessage());
		}
		
		return boardList;
	}
	
	
	//Search 테이블에 검색 키워드가 있다면 update, 없으면 insert
	@Override
	public void upsertSearch(String keyword) {
		logger.info("siDaoImpl upsertSearch start");
		
		try {
			session.insert("siUpsert", keyword);
			
		} catch(Exception e) {
			logger.info("siDaoImpl upsertSearch e.getMessage() : " + e.getMessage());
		}
		
	}
}
