package com.travelAlone.s20230404.dao.ro;

import java.util.List;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;

public interface RoDao {

	int 				boardAllCnt();
	List<Board> 		listAllBoard(Board board);
	int 				boardCnt(Board board);
	List<Board> 		listBoard(Board board);
	int 				veiwCount(int board_id);
	List<Board> 		detailBoard(long board_id);
	long 				insertBoard(Board board);
	int 				insertBodImg(List<BodImg> bodImgs);
	int 				insertReBoard(Board board);
	int					deleteImgBoard(long board_id);
	List<String>		detailBoardImg(long board_id);
	int 				deleteBoard(long board_id);
	void				deleteReBoard(Board board);
	int					updateReBoard(Board board);
	int 				updateBoard(Board board);
	int 				deleteImgOneBoard(BodImg bodImg);
	String 				selBoardImg(BodImg bodImg);
	List<BodImg> 		listBoardImg(long board_id);
}
