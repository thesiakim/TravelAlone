package com.travelAlone.s20230404.service.jh;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.jh.jhDao;
import com.travelAlone.s20230404.model.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class jhServiceImpl implements jhService {
	private final jhDao	 jd;
	
	// 커뮤니티 글 totalCnt 
	@Override
	public int totalBoard() {
		System.out.println("jhServiceImpl totalBoard Start...");
		int totBoardCnt = jd.totalBoard();
		System.out.println("jhServiceImpl totalBoard totBoardCnt -> " + totBoardCnt);
		return totBoardCnt;
	}
	
	// 커뮤니티 리스트
	@Override
	public List<Board> listBoard(Board board) {
		List<Board> boardList = null;
		System.out.println("jhServiceImpl listBoard Start...");
		boardList = jd.listBoard(board);
		System.out.println("jhServiceImpl listBoard boardList.size() -> " + boardList.size());
		return boardList;
	}
	
	// 게시글내용 + 댓글
	@Override
	public List<Board> contentBoard(Board board) {
		List<Board> contentBoard = null;
		System.out.println("jhServiceImpl contentBoard Start...");
		contentBoard = jd.contentBoard(board);
		System.out.println("jhServiceImpl contentBoard contentBoard.size() -> " + contentBoard.size());
		return contentBoard;
	}
	
	@Override
	public int insertBoard(Board board) {
		int result = 0;
		System.out.println("jhServiceImpl insertBoard Start...");
		result = jd.insertBoard(board);
		return result;
	}

	@Override
	public int boardLike(int board_id) {
		System.out.println("jhServiceImpl boardLike update...");
		int boardLike = 0;
		boardLike = jd.boardLike(board_id);
		return boardLike;
	}

	@Override
	public int veiwCount(Board board) {
		System.out.println("jhServiceImpl veiwCount Start...");
		int veiwCount = 0;
		veiwCount = jd.veiwCount(board);
		return veiwCount;
	}

	@Override
	public int insertReply(Board board) {
		int result = 0;
		System.out.println("jhServiceImpl insertReply Start...");
		result = jd.insertReply(board);
		return result;
	}

}
