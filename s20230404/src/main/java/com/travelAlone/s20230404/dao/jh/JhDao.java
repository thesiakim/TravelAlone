package com.travelAlone.s20230404.dao.jh;

import java.util.List;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.Warning;

public interface JhDao {

	int 				updateCount(Board board);
	int 				updateMinus(Board board);
	int 				reportMember(Warning warning);
	int 				insertReLevel(Board board);
	int 				updateBoardImgYn(long boardId);
	int					reportUpdate(Warning warning);
	List<Board> 		myPageCommunityList(Board board);
	int 				myPageCommunityListCnt(long memberId);


}