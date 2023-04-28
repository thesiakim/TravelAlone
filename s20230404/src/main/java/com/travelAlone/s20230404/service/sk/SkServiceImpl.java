package com.travelAlone.s20230404.service.sk;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.sk.SkDao;
import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Img;
import com.travelAlone.s20230404.model.Res_Rev;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkServiceImpl implements SkService {
	private final SkDao sk;

	@Override
	public int totalRes() {
		log.info("SkServiceImpl start totalRes");
		int totResCnt = sk.totalRes();
		log.info("SkServiceImpl totalRes totResCnt->" + totResCnt);
		return totResCnt;
	}

	@Override
	public List<Res> listRes(Res res) {
		log.debug("SkServiceImpl start listRes");
		List<Res> resList = sk.selectResList(res);
		log.debug("SkServiceImpl End listRes");
		return resList;
	}

	@Override
	public Res detailRes(int rid) {
		log.info("SkServiceImpl detail");
		Res res = null;
		res = sk.detailRes(rid);
		return res;
	}

	@Override
	public int insertRes(Res res) {
		int result = 0;
		log.info("SkServiceImpl insert start");
		result = sk.insertRes(res);
		return result;
	}

	@Override
	public int updateRes(Res res) {
		log.info("SkServiceImpl update");
		int updateCount = 0;
		updateCount = sk.updateRes(res);
		return updateCount;
	}

	@Override
	public int deleteRes(int res_id) {
		int result = 0;
		log.info("SkServiceImpl delete Start");
		result = sk.deleteRes(res_id);		
		return result;
	}

	@Override
	public int conditionResCount(Res res) {
		log.info("SkServiceImpl conditionResCount start");
		int conditionResCount = sk.condResCnt(res);
		log.info("SkServiceImpl conditionResCount conditionResCnt" + conditionResCount);	
		return conditionResCount;
	}

	@Override
	public List<Res> listSearchRes(Res res) {
		List<Res> resSearchList = null;
		log.info("SkServiceImpl listSearchRes start");
		resSearchList = sk.resSearchList(res);
		log.info("SkServiceImpl listSearchRes inquireResList.size()"+resSearchList.size());
		return resSearchList;
	}

	// 공통 맛집 코드 리스트
	@Override
	public List<CommonCode> getCommonCode() {
		List<CommonCode> result = sk.getCommonCode();
		return result;
	}

	// 맛집 종류 갯수
	@Override
	public int resFilter(String code) {
		log.info("SkServiceImpl conditionOptionCount Start");
		int conditionInquireCnt = sk.resFilter(code);
		log.info("SkServiceImpl conditionOptionCount conditionInquireCnt" + conditionInquireCnt);
		return conditionInquireCnt;
	}

	@Override
	public List<Res> optResList(Res res) {
		List<Res> resOptionFilterList = null;
		log.info("SkServiceImpl listFilterOptionRes Start");
		resOptionFilterList = sk.optResList(res);
		log.info("SkServiceImpl listFilterOptionRes resOptionFilterList.size()" + resOptionFilterList.size());
		return resOptionFilterList;
	}

	// 지역 코드 가져오기
	@Override
	public List<CommonCode> getCommonLocCode() {
		List<CommonCode> result = sk.getCommonLocCode();
		return result;
	}

	@Override
	public int conditionOptionLocCount(String code) {
		log.info("SkServiceImpl conditionOptionLocCount start");
		int conditionLocCnt = sk.condOptionLocCnt(code);
		log.info("SkServiceImpl conditionOptionLocCount conditionLocCnt" + conditionLocCnt);
		return conditionLocCnt;
	}

	@Override
	public List<Res> listFilterOptionLoc(Res res) {
		List<Res> locOptionFilterList = null;
		log.info("SkServiceImpl listFilterOptionLoc start");
		locOptionFilterList = sk.optionLocList(res);
		log.info("SkServiceImpl listFilterOptionLoc locOptionFilterList.size()"+locOptionFilterList.size());
		return locOptionFilterList;
	}

	@Override
	public List<Res_Rev> listResRev(int rid) {
		log.debug("SkServiceImpl Start listResRev");
		List<Res_Rev> resRevList = sk.selectResRevList(rid);
		
		log.debug("SkServiceImpl End listResRev");
		return resRevList;
	}

	@Override
	public int insertResRev(Res_Rev res_Rev) {
		int result = 0;
		log.info("SkServiceImpl insert start");
		result = sk.insertResRev(res_Rev);
		return result;
	}

	@Override
	public int updateResRev(Res_Rev res_Rev) {
		log.info("SkServiceImpl update");
		int updateCount = 0;
		updateCount = sk.updateResRev(res_Rev);
		return updateCount;
	}

	@Override
	public int deleteResRev(int review_id) {
		int result = 0;
		log.info("SkServiceImpl delete Start");
		result = sk.deleteResRev(review_id);
		return result;
	}

	@Override
	public int insertImg(Res_Img res_Img) {
		int result = 0;
		log.info("SkServiceImpl insert Start..." );
		result = sk.insertImg(res_Img);
		return result;
	}

	@Override
	public int seqRes(Res res) {
		int result = 0;
		log.info("SkServiceImpl seqRes Start..." );
		result = sk.seqRes(res);
		return result;
	}

	@Override
	public List<Res_Img> listRes_Img(Res_Img res_Img) {
		log.info("SkServiceImpl Start listRes_Img");
		List<Res_Img> resImgList = sk.selectResImgList(res_Img);						
		return resImgList;
	}

	@Override
	public int deleteResImg(int res_id) {
		int result = 0;
		log.info("SkServiceImpl deleteResImg Start");
		result = sk.deleteResImg(res_id);		
		return result;
	}
}
