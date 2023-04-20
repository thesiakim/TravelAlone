package com.travelAlone.s20230404.service.jh;

import java.util.List;

import com.travelAlone.s20230404.model.Board;


public interface jhService {

	int 			totalBoard();
	List<Board> 	listBoard(Board board);
	List<Board> 	contentBoard(Board board);
	int 			insertReply(Board board);
	int 			insertBoard(Board board);
	int 			boardLike(int board_id);
	int 			veiwCount(Board board);
}
