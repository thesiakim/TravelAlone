package com.travelAlone.s20230404.dao.ro;

import java.util.List;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;

public interface roDao {

	int boardAllCnt();

	List<Board> listAllBoard(Board board);

	int boardCnt(Board board);

	List<Board> listBoard(Board board);
	
	List<Board> detailBoard(int board_id);

	long insertBoard(Board board);

	int insertReBoard(Board board);

    int insertBodImg(List<BodImg> bodImgs);
}
