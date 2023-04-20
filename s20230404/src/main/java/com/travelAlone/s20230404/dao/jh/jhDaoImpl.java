package com.travelAlone.s20230404.dao.jh;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.Board;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class jhDaoImpl implements jhDao {
	
	private final SqlSession session;
	
	// 커뮤니티글 totalCnt
	@Override
	public int totalBoard() {
		int 	totalBoardCnt = 0;
		System.out.println("jhDaoImpl totalBoard Start...");
		
		try {
			totalBoardCnt = session.selectOne("boardTotal");
			System.out.println("jhDaoImpl totalBoard totalBoardCnt -> " + totalBoardCnt);
		} catch (Exception e) {
			System.out.println("jhDaoImpl totalBoard Exception -> " + e.getMessage());
		}
		return totalBoardCnt;
	}
	
	// 전체 커뮤니티 리스트
	@Override
	public List<Board> listBoard(Board board) {
		List<Board> boardList = null;
		System.out.println("jhDaoImpl listBoard Start...");
		
		try {
			boardList = session.selectList("jhBoardListAll", board);
			System.out.println("jhDaoImpl listBoard boardList.size() -> " + boardList.size());
		} catch (Exception e) {
			System.out.println("jhDaoImpl listBoard Exception -> " + e.getMessage());
		}
		return boardList;
	}
	
	// 게시글 내용
	@Override
	public List<Board> contentBoard(Board board) {
		List<Board> contentBoard = null;
		System.out.println("jhDaoImpl listBoard Start...");
		
		try {
			contentBoard = session.selectList("jhcontentBoard", board);
			System.out.println("jhDaoImpl listBoard boardList2.size() -> " + contentBoard.size());
		} catch (Exception e) {
			System.out.println("jhDaoImpl listBoard Exception -> " + e.getMessage());
		}
		return contentBoard;
	}
	
	// 게시글 작성
	@Override
	public int insertBoard(Board board) {
		int result = 0;
		System.out.println("jhDaoImpl insertBoard Start...");
		try {
			result = session.insert("insertBoard",board);
			System.out.println("jhDaoImpl insertBoard result -> " + result);
		} catch (Exception e) {
			System.out.println("jhDaoImpl insertBoard Exception -> " + e.getMessage());
		}
		return result;
	}
	
	// 추천 수 증가
	@Override
	public int boardLike(int board_id) {
		System.out.println("jhDaoImpl boardLike start...");
		int boardLike = 0;
		try {
			boardLike = session.update("jhboardLike",board_id);
			System.out.println(" ");
		} catch (Exception e) {
			System.out.println("jhDaoImpl boardLike Exception -> " + e.getMessage());
		}
		return boardLike;
	}
	
	// 조회 수 증가
	@Override
	public int veiwCount(Board board) {
		System.out.println("jhDaoImpl veiwCount start...");
		int veiwCount = 0;
		try {
			veiwCount = session.update("jhboardView",board);
			System.out.println("jhDaoImpl veiwCount -> " + veiwCount);
		} catch (Exception e) {
			System.out.println("jhDaoImpl veiwCount Exception -> " + e.getMessage());
		}
		return veiwCount;
	}
	
	// 댓글 작성
	@Override
	public int insertReply(Board board) {
		int result = 0;
		System.out.println("jhDaoImpl insertReply Start...");
		try {
			result = session.insert("insertReply",board);
			System.out.println("insert start...");
			System.out.println("update start..");
			System.out.println("jhDaoImpl insertReply result -> " + result);
		} catch (Exception e) {
			System.out.println("jhDaoImpl insertReply Exception -> " + e.getMessage());
		}
		return result;
	}
	

}
