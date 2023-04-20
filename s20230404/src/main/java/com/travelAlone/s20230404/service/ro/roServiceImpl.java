package com.travelAlone.s20230404.service.ro;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.ro.roDao;
import com.travelAlone.s20230404.model.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class roServiceImpl implements roService {

	private final roDao		rd;

	@Override
	public int boardAllCnt() {
		log.info("roService boardAllCnt 시작");
		int boardAllCnt = rd.boardAllCnt();
		log.info("roService boardAllCnt는 "+ boardAllCnt);
		
		return boardAllCnt;
	}

	@Override
	public List<Board> listAllBoard(Board board) {
		List<Board> listBoardAll = null;
		log.info("roService listAllBoard 시작");
		
		listBoardAll = rd.listAllBoard(board);
		log.info("roService listAllBoard listBoardAll.size()는 "+ listBoardAll.size());
		
		return listBoardAll;
	}

	@Override
	public int boardCnt(Board board) {
		log.info("roService boardCnt 시작");
		int boardCnt = rd.boardCnt(board);
		log.info("roService boardCnt는 "+ boardCnt);
		
		return boardCnt;
	}

	@Override
	public List<Board> listBoard(Board board) {
		List<Board> listBoard = null;
		log.info("roService listBoard 시작");
		
		listBoard = rd.listBoard(board);
		log.info("roService listBoard listBoard.size()는 "+ listBoard.size());
		
		return listBoard;
	}

	@Override
	public List<Board> detailBoard(int board_id) {
		List<Board> listBoardS = null;
		log.info("roService detailBoard 시작");
		
		listBoardS = rd.detailBoard(board_id);
		log.info("roService detailBoard listBoardC.size()는 "+ listBoardS.size());
		
		return listBoardS;
	}

	@Override
	public int insertBoard(Board board) {
		int insertResult = 0;
		log.info("roService insertBoard 시작");
		insertResult = rd.insertBoard(board);
		log.info("roService insertBoard insertResult는 "+ insertResult);
		
		return insertResult;
	}

	@Override
	public int insertReBoard(Board board) {
		int insertResult = 0;
		log.info("roService insertReBoard 시작");
		insertResult = rd.insertReBoard(board);
		log.info("roService insertReBoard insertResult는 "+ insertResult);
		
		return insertResult;
	}
	
	
}