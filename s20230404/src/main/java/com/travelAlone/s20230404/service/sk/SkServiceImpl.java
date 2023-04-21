package com.travelAlone.s20230404.service.sk;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.sk.SkDao;
import com.travelAlone.s20230404.model.Res;

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
}
