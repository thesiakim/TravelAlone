package com.travelAlone.s20230404.service.si;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.si.SiDao;
import com.travelAlone.s20230404.model.si.ResultList;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiServiceImpl implements SiService {
	
	private final Logger logger = LoggerFactory.getLogger(SiService.class);
	private final SiDao siDao;
	
	//검색 시 선택한 카테고리에 따라 DB 데이터에  검색 키워드가 존재하는지 조회
	@Override
	public ResultList search(String keyword, String category) {
		
		logger.info("siServiceImpl search start");
		ResultList resultList = new ResultList();
		
		  //카테고리가 전체인 경우
		  if(category.equals("category_total")) {
			  resultList.setTravelList(siDao.travelSearch(keyword));
			  resultList.setHouseList(siDao.houseSearch(keyword));
			  resultList.setRestaurantlList(siDao.resSearch(keyword));
			  resultList.setBoardList(siDao.boardSearch(keyword));
		  
		  //카테고리가 여행지인 경우	  
		  } else if(category.equals("category_travel")) {
			  resultList.setTravelList(siDao.travelSearch(keyword));
		  
		  //카테고리가 숙소인 경우			  
		  } else if(category.equals("category_house")) {
			  resultList.setHouseList(siDao.houseSearch(keyword));
		  
		  //카테고리가 맛집인 경우	  
		  } else if(category.equals("category_res")) {
			  resultList.setRestaurantlList(siDao.resSearch(keyword));
		  
		  //카테고리가 커뮤니티인 경우		  
		  } else if(category.equals("category_comm")) {
			  logger.info("siServiceImpl에서 category_comm인 경우 search");
			  resultList.setBoardList(siDao.boardSearch(keyword));
	      }
		  
		 return resultList;
		 
	}
	
	
	//검색 키워드가 Search 테이블에 있는지 조회한 뒤 있으면 update, 없으면 insert
	@Override
	public void upsertSearch(String keyword) {
		logger.info("siServiceImpl upsertSearch start");
		siDao.upsertSearch(keyword);
		
	}
	

}
