package com.travelAlone.s20230404.dao.jh;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;
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
	
	// 게시글 작성
	@Override
	public long insertBoard(Board board) {
		long createBoardId = 0L;

		log.info("jhDaoImpl insertBoard start");
		
		try {
			int roBoardInsertResult = session.insert("roBoardInsert", board);

			createBoardId = board.getBoard_id();

			log.info("jhDaoImpl insertBoard createBoardId는 "+ createBoardId);

		} catch (Exception e) {

			log.info("jhDaoImpl insertBoard e.getMessage는 "+ e.getMessage());

		}
		return createBoardId;
	}
	
	// 이미지 삽입
	@Override
	public int insertBodImg(List<BodImg> bodImgs) {
		int insertResult = 0;
		log.info("jhDaoImpl insertBoardImg start");
		for (BodImg img : bodImgs) {
			try {
				session.insert("roBoardImgInsert", img);
				log.info("jhDaoImpl insertBodImg insertResult는 "+ insertResult);
				
				
				insertResult++;
			} catch (Exception e) {
				log.info("jhDaoImpl insertBodImg e.getMessage는 "+ e.getMessage());
			}
		}
		return insertResult;
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

	@Override
	public int updateBoardImgYn(long boardId) {
		int result = 0;
		log.info("jhDaoImpl updateBoardImgYn start");
		
		try {
			result = session.update("imgStoredFileYn",boardId);
			log.info("jhDaoImpl updateBoardImgYn boardId-> " + boardId);
			
		} catch (Exception e) {
			log.info("jhDaoImpl updateBoardImgYn Exception -> " + e.getMessage());
			
		}
		return result;
	}
	
}