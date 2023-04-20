package com.travelAlone.s20230404.service.ro;

import java.util.List;

import com.travelAlone.s20230404.model.Board;

public interface roService {

	int boardAllCnt();

	List<Board> listAllBoard(Board board);

	int boardCnt(Board board);

	List<Board> listBoard(Board board);
	
	List<Board> detailBoard(int board_id);

	int insertBoard(Board board);

	int insertReBoard(Board board);
	
}
