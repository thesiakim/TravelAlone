package com.travelAlone.s20230404.dao.jh;

import java.util.List;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.Hou_Rev;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Interest;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Rev;
import com.travelAlone.s20230404.model.Tra_Rev;
import com.travelAlone.s20230404.model.Travel;
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
	List<Member> 		userPage(long member_id);
	List<Travel> 		listReviewPageTra(Tra_Rev traRev);
	int 				totalReviewPageTra(long memberId);
	List<House> 		listReviewPageHou(Hou_Rev houRev);
	int 				totalReviewPageHou(long memberId);
	List<Res> 			listReviewPageRes(Res_Rev resRev);
	int 				totalReviewPageRes(long memberId);
	List<Interest> 		mypageTagUpdate(Interest interest);


}