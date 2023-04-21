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
	
	// 맛집 정보글 갯수 조회
	@Override
	public int totalRes() {
		int totResCount = 0;
		log.info("SkDaoImpl Start total");
		try {
			totResCount = session.selectOne("resTotal");
			log.info("SkDaoImpl totalRes totresCount->" + totResCount);
		} catch (Exception e) {
			log.info("SkDaoImpl totalRes Exception" + e.getMessage());
		}
		return totResCount;
	}

	// 맛집 정보글 리스트 조회
	@Override
	public List<Res> selectResList(Res res){
		List<Res>resList = new ArrayList<Res>();
		
	try {
		log.info("SkDaoImpl selectResList SkresList start");
		resList = session.selectList("resList", res);
		log.info("SkDaoImpl selectResList SkResList End");
	} catch (Exception e) {
		log.info("SkDaoImpl SkResList Exception" + e.getMessage());
	}
		return resList;
	}

	// 맛집 정보글 상세페이지
	@Override
	public Res detailRes(int rid) {
		log.info("SkDaoImpl detail start");
		Res res = new Res();
		try {
			res = session.selectOne("resSelOne", rid);
			
			log.info("SkDaoImpl detail res.getR_name"+res.getR_name());
		} catch (Exception e) {
			log.info("SkDaoImpl SkResSelOne Exception" + e.getMessage());
		}
		return res;
	}
	
	// 맛집 정보글 작성
	@Override
	public int insertRes(Res res) {
		int result = 0;
		log.info("SkDaoImpl insert Start");
		try {
			result = session.insert("insertRes",res);
		} catch (Exception e) {
			log.info("SkDaoImpl insert Exception" + e.getMessage());
		}
		
		return result;
	}
	
	// 맛집 정보글 수정
	@Override
	public int updateRes(Res res) {
		log.info("SkDaoImpl updateRes  start");
		int updateCount= 0;
		try {
			updateCount = session.update("resUpdate",res);
		} catch (Exception e) {
			log.info("SkDaoImpl updateRes Exception->"+e.getMessage());
		}		
		
		return updateCount;
	}

	// 맛집 정보글 삭제
	@Override
	public int deleteRes(int res_id) {
		log.info("SkDaoImpl delete start");
		int result = 0;
		log.info("SkDaoImpl delete res_id->"+ res_id);
		try {
			result = session.delete("deleteRes",res_id);
			log.info("SkDaoImpl delete result->"+ result);
		} catch (Exception e) {
			log.info("SkDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}
}
