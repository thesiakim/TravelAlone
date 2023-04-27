package com.travelAlone.s20230404.service.mh;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travelAlone.s20230404.dao.mh.HouseDao;
import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Hou_Img;
import com.travelAlone.s20230404.model.Hou_Rev;
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
		log.info("HouseServiceImpl start totalHouse ");
		int totHouseCnt = mh.totalHouse();
		log.info("HouseServiceImpl  totalHouse totHouseCnt->" + totHouseCnt );		
		return totHouseCnt;
	}
	//숙소 리스트
	@Override
	public List<House> listHouse(House house) {
		log.debug("HouseServiceImpl Start listHouse...");
		List<House> houseList = mh.selectHouseList(house);
		log.debug("HouseServiceImpl End listHouse...");
		return houseList;
	}

	@Override
	public House detailHouse(int hid) {
		log.info("HouseServiceImpl detail");
		House house = null;
		house = mh.detailHouse(hid);		
		return house;
	}
	//숙소정보작성
	@Override
	public int insertHou(House house, List<MultipartFile> files) throws Exception {
		int result = 0;
		log.info("HouseServiceImpl insert Start");
		//result = mh.insertHou(house);
		long house_id = mh.insertHou(house);
		List<Hou_Img> houImgs = HouImgUploadHandler.parseFileInfo(files, house_id);
		result =mh.insertHouImg(houImgs);
		
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

	@Override
	public int conditionHouseCount(House house) {
		log.info("HouseServiceImpl  conditionHouseCount Start" );
		int conditionHouseCnt = mh.condHouseCnt(house);
		log.info("HouseServiceImpl  conditionHouseCount conditionHouseCnt" + conditionHouseCnt);						
		return conditionHouseCnt;
	}

	@Override
	public List<House> listSearchHouse(House house) {
		List<House> houseSearchList = null;
		log.info("HouseServiceImpl listSearchHouse Start...");
		houseSearchList = mh.houseSearchList(house);
		log.info("HouseServiceImpl listSearchHouse inquireHouseList.size()"+ houseSearchList.size());						
		return houseSearchList;
	}

	//공통숙소코드 리스트
	@Override
	public List<CommonCode> getCommonCode() {
		List<CommonCode> result = mh.getCommonCode();		
		return result;
	}
		//숙소종류갯수
	@Override
	public int conditionOptionCount(String code) {
		log.info("HouseServiceImpl  conditionOptionCount Start" );
		int conditionInquireCnt = mh.condOptionInqCnt(code);
		log.info("HouseServiceImpl  conditionOptionCount conditionInquireCnt" + conditionInquireCnt);	
		return conditionInquireCnt;
	}

	
	@Override
	public List<House> listFilterOptionHouse(House house) {
		List<House> houseOptionFilterList = null;
		log.info("HouseServiceImpl listFilterOptionHouse Start...");
		houseOptionFilterList = mh.mhOptionHouseList(house);
		log.info("HouseServiceImpl listFilterOptionHouse houseOptionFilterList.size()"+ houseOptionFilterList.size());		
		return houseOptionFilterList;
	}

	
	
	
	//지역 코드가져오기
	@Override
	public List<CommonCode> getCommonLocCode() {
		List<CommonCode> result = mh.getCommonLocCode();
		return result;
	}

	@Override
	public List<House> listFilterOptionLoc(House house) {
		List<House> locOptionFilterList = null;
		log.info("HouseServiceImpl listFilterOptionLoc Start...");
		locOptionFilterList = mh.mhOptionLocList(house);
		log.info("HouseServiceImpl listFilterOptionLoc locOptionFilterList.size()"+ locOptionFilterList.size());			
		return locOptionFilterList;
	}

	@Override
	public int conditionOptionLocCount(String code) {
		log.info("HouseServiceImpl  conditionOptionLocCount Start" );
		int conditionLocCnt = mh.condOptionLocCnt(code);
		log.info("HouseServiceImpl  conditionOptionLocCount conditionLocCnt" + conditionLocCnt);	
		return conditionLocCnt;
	}

	
	//==========리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰====================================
	@Override
	public List<Hou_Rev> listHouRev(int hid) {
		log.debug("HouseServiceImpl Start listHouRev...");
		List<Hou_Rev> houRevList = mh.selectHouRevList(hid);
		
		log.debug("HouseServiceImpl End listHouRev...");
		return houRevList;
	}
	//리뷰작성
	@Override
	public int insertHouRev(Hou_Rev hou_Rev) {
		int result = 0;
		log.info("HouseServiceImpl insert Start...");
		result = mh.insertHouRev(hou_Rev);
		return result;
	}
	
	
	@Override
	public int updateHouseRev(Hou_Rev hou_Rev) {
		log.info("HouseServiceImpl update");
		int updateCount = 0;
		updateCount = mh.updateHouseRev(hou_Rev);	
		return updateCount;
	}
	@Override
	public int deleteHouRev(int review_id) {
		int result = 0;
		log.info("HouseServiceImpl delete Start");
		result = mh.deleteHouseRev(review_id);		
		return result;
	}

	


	
	
	
	
}
