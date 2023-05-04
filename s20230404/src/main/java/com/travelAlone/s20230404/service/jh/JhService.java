package com.travelAlone.s20230404.service.jh;

import java.util.List;


import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.Warning;

public interface JhService {

	int 			updateCount(Board board);
	int 			updateMinus(Board board);
	int 			reportMember(Warning warning);
	int 			insertReLevel(Board board);
	List<Board> 	myPageCommunityList(Board board);
	int 			myPageCommunityListCnt(long memberId);
}