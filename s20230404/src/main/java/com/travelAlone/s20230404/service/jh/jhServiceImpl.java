package com.travelAlone.s20230404.service.jh;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import com.travelAlone.s20230404.dao.jh.jhDao;
import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.Warning;

@Service
@Slf4j
@RequiredArgsConstructor
public class jhServiceImpl implements jhService {
	private final jhDao jd;

	// 추천 버튼
	@Override
	public int updateCount(Board board) {
		log.info("jhServiceImpl b_Like_Cnt start...");
		int updateCount = 0;
		updateCount = jd.updateCount(board);
		return updateCount;
	}

	// 신고 버튼
	@Override
	public int reportMember(Warning warning) {
		log.info("jhServiceImpl reportMember start...");
		int reportMember = 0;
		reportMember = jd.reportMember(warning);
		return reportMember;
	}
}
