package com.travelAlone.s20230404.dao.jh;

import java.util.List;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.Warning;

public interface JhDao {

	int 				updateCount(Board board);
	int 				reportMember(Warning warning);
	long  				insertBoard(Board board);
	int 				insertBodImg(List<BodImg> bodImgs);
	int 				insertReLevel(Board board);
	int					reportUpdate(Warning warning);

}