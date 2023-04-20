package com.travelAlone.s20230404.dao.sk;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.Res;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SkDaoImpl implements SkDao {

	private final SqlSession session;
	
	@Override
	public int totalRes() {
		int totResCount = 0;
		log.info("SkDaoImpl Start total");
		try {
			totResCount = session.selectOne("SkResTotal");
			log.info("SkDaoImpl totalRes totresCount->" + totResCount);
		} catch (Exception e) {
			log.info("SkDaoImpl totalRes Exception" + e.getMessage());
		}
		return totResCount;
	}

	@Override
	public List<Res> selectResList(Res res){
		List<Res>resList = new ArrayList<Res>();
		
	try {
		log.info("SkDaoImpl selectResList SkresList start");
		resList = session.selectList("skResList", res);
		log.info("SkDaoImpl selectResList SkresList End");
	} catch (Exception e) {
		log.info("skDaoImpl SkResList Exception" + e.getMessage());
	}
		return resList;
	}

	@Override
	public Res detailRes(int rid) {
		log.info("SkDaoImpl detaul start");
		Res res = new Res();
		try {
			res = session.selectOne("SkResSelOne", rid);
			log.info("SkDaoImpl detail res.getR_name"+res.getR_name());
		} catch (Exception e) {
			log.info("SkDaoImpl SkHouseSelOne Exception" + e.getMessage());
		}
		return res;
	}

	@Override
	public int insertRes(Res res) {
		int result = 0;
		
		return 0;
	}

}
