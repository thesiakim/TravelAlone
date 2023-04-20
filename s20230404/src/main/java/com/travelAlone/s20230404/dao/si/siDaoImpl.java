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
public class siDaoImpl implements siDao {
	
	private final Logger logger = LoggerFactory.getLogger(siDao.class);
	private final SqlSession session;
	
	@Override
	public List<Travel> travelSearch(String keyword) {
		logger.info("siDaoImpl travelSearch start");
		List<Travel> travelList = null;
		
		try {
			travelList = session.selectList("siTravelSearch", keyword);		
//			if(travelList.)
			
		} catch(Exception e) {
			logger.info("siDaoImpl houseSearch e.getMessage() : " + e.getMessage());
		}
		return travelList;
	}
	
	
	
	@Override
	public List<House> houseSearch(String keyword) {
		logger.info("siDaoImpl houseSearch start");
		List<House> houseList = null;
		
		try {
			houseList = session.selectList("siHouseSearch", keyword);	
			
			
		} catch(Exception e) {
			logger.info("siDaoImpl houseSearch e.getMessage() : " + e.getMessage());
		}
		return houseList;
	}
	



	@Override
	public List<Res> resSearch(String keyword) {
		logger.info("siDaoImpl resSearch start");
		List<Res> resList = null;
		
		try {
			resList = session.selectList("siResSearch", keyword);			
			
		} catch(Exception e) {
			logger.info("siDaoImpl houseSearch e.getMessage() : " + e.getMessage());
		}
		return resList;
	}


	
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
	
	
	
	@Override
	public void upsertSearch(String keyword) {
		logger.info("siDaoImpl upsertSearch start");
		
		try {
			//검색어가 없으면 insert, 있으면 update count++
			session.insert("siUpsert", keyword);
			
		} catch(Exception e) {
			logger.info("siDaoImpl upsertSearch e.getMessage() : " + e.getMessage());
		}
		
	}
}
