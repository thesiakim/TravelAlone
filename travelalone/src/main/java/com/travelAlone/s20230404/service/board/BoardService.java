package com.travelAlone.s20230404.service.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
import com.travelAlone.s20230404.model.dto.ro.BoardWriteRequestDto;

public interface BoardService {
	int 				boardAllCnt();
	List<Board> 		listAllBoard(Board board);
	int 				boardCnt(Board board);
	List<Board> 		listBoard(Board board);
	int 				veiwCount(long board_id);
	List<Board> 		detailBoard(long board_id);
	int 				insertBoard(BoardWriteRequestDto requestDto, List<MultipartFile> files) throws Exception;
	int 				insertReBoard(Board board);
	int					insertReLevel(Board board);
	int 				deleteBoard(long board_id);
	void				deleteReBoard(Board board);
	int					updateReBoard(Board board);
	int 				updateBoard(Board board, List<MultipartFile> imgFiles);
	int 				deleteImgBoard(BodImg bodImg);
	List<BodImg> 		listImgBoard(long board_id);
	int 				updateCount(Board board);
	int 				updateMinus(Board board);
	int 				reportMember(Warning warning);
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
