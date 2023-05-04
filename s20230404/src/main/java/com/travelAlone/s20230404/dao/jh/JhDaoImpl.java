package com.travelAlone.s20230404.dao.jh;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.Warning;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JhDaoImpl implements JhDao {
	
	private final SqlSession	session;

	// 추천 버튼
	@Override
	public int updateCount(Board board) {
		log.info("jhDaoImpl updateCount Start");
		int updateCount = 0;
		try {
			updateCount = session.update("jhLike",board);
			log.info("jhDaoImpl updateCount -> " + updateCount);
		} catch (Exception e) {
			log.info("jhDaoImpl updateCount Exception->" + e.getMessage());
		}
		
		return updateCount;
	}
	
	// 추천 취소 버튼
	@Override
	public int updateMinus(Board board) {
		log.info("jhDaoImpl updateMinus Start");
		int updateMinus = 0;
		try {
			updateMinus = session.update("jhLikeCancel", board);
			log.info("jhDaoImpl updateMinus -> "+ updateMinus);
		} catch (Exception e) {
			log.info("jhDaoImpl updateMinus Exception->" + e.getMessage());
		}
		return updateMinus;
	}

	// 신고 버튼
	@Override
	public int reportMember(Warning warning) {
		log.info("jhDaoImpl reportMember Start");
		int reportMember = 0;
		try {
			reportMember = session.insert("reportMember", warning);
			log.info("jhDaoImpl reportMember -> " + reportMember);
		} catch (Exception e) {
			log.info("jhDaoImpl reportMember Exception -> " + e.getMessage());
		}
		return reportMember;
	}
	
	// 대댓글 작성
	@Override
	public int insertReLevel(Board board) {
		int insertReLevel = 0;
		int updateReLevel = 0;
		log.info("jhDaoImpl insertReLevel start");
		
		try {
			
			updateReLevel = session.update("updateReLevel", board);
			log.info("jhDaoImpl updateReLevel -> " + updateReLevel);
			
			insertReLevel = session.insert("insertReLevel", board);
			log.info("jhDaoImpl insertReLevel -> " + insertReLevel);
		} catch (Exception e) {
			log.info("jhDaoImpl insertReLevel Exception -> " + e.getMessage());
		}
		return insertReLevel;
	}

	// 게시글 이미지 첨부 여부
	@Override
	public int updateBoardImgYn(long boardId) {
		int result = 0;
		log.info("jhDaoImpl updateBoardImgYn start");
		
		try {
			result = session.update("imgStoredFileYn", boardId);
			log.info("jhDaoImpl updateBoardImgYn board_id-> " + boardId);
			
		} catch (Exception e) {
			log.info("jhDaoImpl updateBoardImgYn Exception -> " + e.getMessage());
			
		}
		return result;
	}

	// 신고 수에 따른 게시글, 댓글 자동 블락
	@Override
	public int reportUpdate(Warning warning) {
		int updateResult = 0;
		log.info("jhDaoImpl reportUpdate start");
		
		try {
			updateResult = session.update("updateReportContent", warning);
			log.info("jhDaoImpl reportUpdate updateResult -> " + updateResult);
		} catch (Exception e) {
			log.info("jhDaoImpl reportUpdate Exception -> " + e.getMessage());
		}
		return updateResult;
	}

   // 마이페이지 커뮤니티 페이징용
	@Override
	public int myPageCommunityListCnt(long memberId) {
		int myPageCommunityListCnt = 0;
		log.info("jhDaoImpl myPageCommunityListCnt start");
	      
		try {
			myPageCommunityListCnt = session.selectOne("myPageCommunityListCnt", memberId);
			log.info("jhDaoImpl myPageCommunityListCnt -> "+ myPageCommunityListCnt);
	         
		} catch (Exception e) {
			log.info("jhDaoImpl myPageCommunityListCnt Exception -> "+ e.getMessage());
		}
		
		return myPageCommunityListCnt;
	}

	// 마이페이지 커뮤니티  리스트
	@Override
	public List<Board> myPageCommunityList(Board board) {
		List<Board> myPageCommunityList = null;
		log.info("jhDaoImpl myPageCommunityList start");
	      
		try {
			myPageCommunityList = session.selectList("myPageCommunityList", board);
			log.info("jhDaoImpl myPageCommunityList.size()는 "+ myPageCommunityList.size());
		} catch (Exception e) {
			log.info("jhDaoImpl myPageCommunityList e.getMessage() -> "+ e.getMessage());
		}
		return myPageCommunityList;
	}

	
}