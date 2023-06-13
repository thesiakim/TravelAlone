package com.travelAlone.s20230404.dao.board;

import java.util.List;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.Hou_Rev;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Interest;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Rev;
import com.travelAlone.s20230404.model.Score;
import com.travelAlone.s20230404.model.Tra_Rev;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.model.Warning;

public interface BoardDao {
	
	int 				boardAllCnt();
	List<Board> 		listAllBoard(Board board);
	int 				boardCnt(Board board);
	List<Board> 		listBoard(Board board);
	int 				veiwCount(long board_id);
	List<Board> 		detailBoard(long board_id);
	long 				insertBoard(Board board);
	int 				insertBodImg(List<BodImg> bodImgs);
	int 				insertReLevel(Board board);
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
	int 				updateCount(Board board);
	int 				updateMinus(Board board);
	int 				reportMember(Warning warning);
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
	int 				userScoreUpdate(Score score);
	
}
