package com.travelAlone.s20230404.dao.jh;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.Warning;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class jhDaoImpl implements jhDao {
	
	private final SqlSession	session;

	// 추천 버튼
	@Override
	public int updateCount(Board board) {
		log.info("jhDaoImpl updateCount Start...");
		int updateCount = 0;
		try {
			updateCount = session.update("jhLike",board);
			log.info("jhDaoImpl updateCount -> " + updateCount);
		} catch (Exception e) {
			log.info("jhDaoImpl updateCount Exception->" + e.getMessage());
		}
		
		return updateCount;
	}

	// 신고 버튼
	@Override
	public int reportMember(Warning warning) {
		log.info("jhDaoImpl reportMember Start...");
		int reportMember = 0;
		try {
			reportMember = session.insert("reportMember", warning);
			log.info("jhDaoImpl reportMember -> " + reportMember);
		} catch (Exception e) {
			log.info("jhDaoImpl reportMember Exception -> " + e.getMessage());
		}
		return reportMember;
	}
	
}
