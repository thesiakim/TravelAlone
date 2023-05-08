package com.travelAlone.s20230404.service.jh;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.jh.JhDao;
import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.Warning;

@Service
@Slf4j
@RequiredArgsConstructor
public class JhServiceImpl implements JhService {
	private final JhDao jd;

	// 추천 버튼
	@Override
	public int updateCount(Board board) {
		log.info("jhServiceImpl b_Like_Cnt start...");
		int updateCount = 0;
		updateCount = jd.updateCount(board);
		return updateCount;
	}
	
	// 추천 취소 버튼
	@Override
	public int updateMinus(Board board) {
		log.info("jhServiceImpl updateMinus start...");
		int updateMinus = 0;
		updateMinus = jd.updateMinus(board);
		return updateMinus;
	}

	// 신고 버튼
	@Override
	public int reportMember(Warning warning) {
		log.info("jhServiceImpl reportMember start...");
		int reportMember = 0;
		int updateResult = 0;
		
		reportMember = jd.reportMember(warning);
		updateResult = jd.reportUpdate(warning);
		
		return reportMember + updateResult;
	}
	
	// 대댓글 작성
	@Override
	public int insertReLevel(Board board) {
		log.info("jhServiceImpl insertReLevel start");
		int insertReLevel = jd.insertReLevel(board);
		
		return insertReLevel;
	}
	
	// 마이페이지 커뮤니티 페이징용 
	@Override
	public int myPageCommunityListCnt(long memberId) {
		log.info("jhServiceImpl myPageCommunityListCnt start");
		int myPageCommunityListCnt = jd.myPageCommunityListCnt(memberId);
		log.info("jhServiceImpl myPageCommunityListCnt는 "+ myPageCommunityListCnt);
		return myPageCommunityListCnt;
	}
	
	// 마이페이지 커뮤니티  리스트
	@Override
	public List<Board> myPageCommunityList(Board board) {
		List<Board> myPageCommunityList = null;
		log.info("jhServiceImpl myPageCommunityList start");
	      
		myPageCommunityList = jd.myPageCommunityList(board);
		log.info("jhServiceImpl myPageCommunityList.size()는 "+ myPageCommunityList.size());
		      
		return myPageCommunityList;
	}
	
	// 유저 마이페이지 
	@Override
	public List<Member> userPage(long member_id) {
		List<Member> userPage = null;
		log.info("jhServiceImpl userPage start");
		
		userPage = jd.userPage(member_id);
		return userPage;
	}

	
	

}