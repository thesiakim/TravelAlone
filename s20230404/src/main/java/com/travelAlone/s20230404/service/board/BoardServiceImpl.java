package com.travelAlone.s20230404.service.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.travelAlone.s20230404.dao.board.BoardDao;
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
import com.travelAlone.s20230404.service.board.UploadHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDao		bd;
	
	// 전체 게시판 개수
	@Override
	public int boardAllCnt() {
//		log.info("BoardServiceImpl boardAllCnt 시작");
		int boardAllCnt = bd.boardAllCnt();
//		log.info("BoardServiceImpl boardAllCnt는 "+ boardAllCnt);
		
		return boardAllCnt;
	}

	// 전체 게시판 list
	@Override
	public List<Board> listAllBoard(Board board) {
		List<Board> listBoardAll = null;
//		log.info("BoardServiceImpl listAllBoard 시작");
		
		listBoardAll = bd.listAllBoard(board);
//		log.info("BoardServiceImpl listAllBoard listBoardAll.size()는 "+ listBoardAll.size());
		
		return listBoardAll;
	}

	// 게시판 개수
	@Override
	public int boardCnt(Board board) {
//		log.info("BoardServiceImpl boardCnt 시작");
		int boardCnt = bd.boardCnt(board);
//		log.info("BoardServiceImpl boardCnt는 "+ boardCnt);
		      
		return boardCnt;
	}

	// 게시판 list
	@Override
	public List<Board> listBoard(Board board) {
		List<Board> listBoard = null;
//		log.info("BoardService listBoard 시작");
		
		listBoard = bd.listBoard(board);
//		log.info("BoardService listBoard listBoard.size()는 "+ listBoard.size());
		
		return listBoard;
	}
	   
	// 조회수
	@Override
	public int veiwCount(long board_id) {
//		log.info("BoardService veiwCount Start...");
		int veiwCount = 0;
		veiwCount = bd.veiwCount(board_id);
		return veiwCount;
	}
	   
	// 게시물 정보 list
	@Override
	public List<Board> detailBoard(long board_id) {
		List<Board> listBoardS = null;
//		log.info("BoardService detailBoard 시작");
		
		listBoardS = bd.detailBoard(board_id);
		List<String> listBoardImg = bd.detailBoardImg(board_id);
		
		if(listBoardImg.size() > 0) {
			// listBoardS가 list형태인데 select해서 받은 값이 하나 밖에 없어서 첫번째의 0번째 인덱스의 Board.java에 이미지들을 넣기 위해 get(0) 사용
			listBoardS.get(0).setImg_stored_file(listBoardImg);         
//			log.info("BoardServiceImpl detailBoard listBoardC.size()는 "+ listBoardS.size());
		}
		return listBoardS;
	}

	// 게시물 작성
	@Override
	public int insertBoard(BoardWriteRequestDto requestDto, List<MultipartFile> files) throws Exception {
		int insertResult = 0;
//		log.info("BoardServiceImpl insertBoard start");
		
		long boardId = bd.insertBoard(requestDto.toBoard());
		
		List<BodImg> bodImgs = UploadHandler.parseFileInfo(files, boardId);
		      
		insertResult = bd.insertBodImg(bodImgs);
		
		if (insertResult >= 1) {
			insertResult = bd.updateBoardImgYn(boardId);
		}
		
//		log.info("BoardServiceImpl insertBoard insertResult는 "+ insertResult);
		return insertResult;
		}
		
	// 게시물 댓글 작성
	@Override
	public int insertReBoard(Board board) {
		int insertResult = 0;
		log.info("BoardServiceImpl insertReBoard 시작");
			
		insertResult = bd.insertReBoard(board);
		log.info("BoardServiceImpl insertReBoard insertResult는 "+ insertResult);
		
		return insertResult;
	}
	
	// 게시물 대댓글 작성
	@Override
	public int insertReLevel(Board board) {
//		log.info("BoardServiceImpl insertReLevel start");
		int insertReLevel = bd.insertReLevel(board);
		
		return insertReLevel;
	}

	// 게시물 삭제
	@Override
	public int deleteBoard(long board_id) {
		int delImgResult = 0;
		int delResult = 0;
//		log.info("BoardServiceImpl deleteBoard 시작");
		// 삭제할 실제 이미지 list DB에서 갖고 오기
		List<String> listDelBodImgs = bd.detailBoardImg(board_id);
//		log.info("BoardServiceImpl deleteBoard listDelBodImgs.size()는 "+ listDelBodImgs.size());
		// 실제 게시물 이미지 파일 삭제
		for (String listDelFiles : listDelBodImgs) {
			UploadHandler.delete(listDelFiles);
		}
		// DB 게시물에 있는 모든 이미지 파일 삭제
		delImgResult = bd.deleteImgBoard(board_id);
//		log.info("BoardServiceImpl deleteBoard delImgResult는 "+ delImgResult);
		// DB 게시물 게시글 삭제
		delResult = bd.deleteBoard(board_id);
//		log.info("BoardServiceImpl deleteBoard delResult는 "+ delResult);
		
//		log.info("BoardServiceImpl deleteBoard delImgResult + delResult = "+ delImgResult + delResult);
		
		return delImgResult + delResult;
	}
	   
	// 댓글 및 대댓글 삭제
	@Override
	public void deleteReBoard(Board board) {
//		log.info("BoardServiceImpl deleteReBoard 시작");
		bd.deleteReBoard(board);
	}
	   
	// 댓글 수정
	@Override
	public int updateReBoard(Board board) {
		int updateCount = 0;
//		log.info("BoardServiceImpl updateReBoard 시작");
		
		updateCount = bd.updateReBoard(board);
		
		return updateCount;
	}
	   
	// 게시물 수정
	@Override
	public int updateBoard(Board board, List<MultipartFile> imgFiles) {
		int updateResult = 0;
//		log.info("BoardServiceImpl updateReBoard 시작");
		updateResult = bd.updateBoard(board);
		try {
			// 실제 게시글 이미지 파일 저장
			List<BodImg> bodImgs = UploadHandler.parseFileInfo(imgFiles, board.getBoard_id());
//			log.info("BoardServiceImpl updateReBoard bodImgs.size()는 "+ bodImgs.size());
			updateResult = bd.insertBodImg(bodImgs);
			
			if (updateResult >= 1) {
				updateResult = bd.updateBoardImgYn(board.getBoard_id());
			}
//			log.info("BoardServiceImpl insertBoard insertImgResult는 "+ updateResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return updateResult;
	}
	   
	// 선택한 게시물 이미지 삭제
	@Override
	public int deleteImgBoard(BodImg bodImg) {
		int deleteResult = 0;
//		log.info("BoardServiceImpl deleteImgBoard 시작");
		// 삭제할 실제 이미지 DB에서 갖고 오기
		String delBoardImg = bd.selBoardImg(bodImg);
		// 실제 이미지 파일 삭제
		UploadHandler.delete(delBoardImg);
		// DB 게시물 이미지 파일 삭제
		deleteResult = bd.deleteImgOneBoard(bodImg);
//		log.info("BoardServiceImpl deleteImgBoard deleteResult는 "+ deleteResult);
		return deleteResult;
	}
	      
	// 게시물 이미지 리스트
	@Override
	public List<BodImg> listImgBoard(long board_id) {
//		log.info("BoardServiceImpl listImgBoard 시작");
		List<BodImg> listBoardImg = bd.listBoardImg(board_id);
//		log.info("BoardServiceImpl listImgBoard listDelBodImgs.size()는 "+ listBoardImg.size());
		return listBoardImg;
	}
	
	// 추천 버튼
	@Override
	public int updateCount(Board board) {
//		log.info("BoardServiceImpl b_Like_Cnt start...");
		int updateCount = 0;
		updateCount = bd.updateCount(board);
		return updateCount;
	}
		
	// 추천 취소 버튼
	@Override
	public int updateMinus(Board board) {
//		log.info("BoardServiceImpl updateMinus start...");
		int updateMinus = 0;
		updateMinus = bd.updateMinus(board);
		return updateMinus;
	}

	// 신고 버튼
	@Override
	public int reportMember(Warning warning) {
//		log.info("BoardServiceImpl reportMember start...");
		int reportMember = 0;
		int updateResult = 0;
		
		reportMember = bd.reportMember(warning);
		updateResult = bd.reportUpdate(warning);
		
		return reportMember + updateResult;
	}
	
	// 마이페이지 커뮤니티 페이징용 
	@Override
	public int myPageCommunityListCnt(long memberId) {
//		log.info("BoardServiceImpl myPageCommunityListCnt start");
		int myPageCommunityListCnt = bd.myPageCommunityListCnt(memberId);
//		log.info("BoardServiceImpl myPageCommunityListCnt는 "+ myPageCommunityListCnt);
		return myPageCommunityListCnt;
	}
		
	// 마이페이지 커뮤니티  리스트
	@Override
	public List<Board> myPageCommunityList(Board board) {
		List<Board> myPageCommunityList = null;
//		log.info("BoardServiceImpl myPageCommunityList start");
		
		myPageCommunityList = bd.myPageCommunityList(board);
//		log.info("BoardServiceImpl myPageCommunityList.size()는 "+ myPageCommunityList.size());
		
		return myPageCommunityList;
	}

	// 유저 마이페이지 
	@Override
	public List<Member> userPage(long member_id) {
		List<Member> userPage = null;
//		log.info("BoardServiceImpl userPage start");
		
		userPage = bd.userPage(member_id);
		return userPage;
	}
	
	// 마이페이지 여행지 리뷰 리스트
	@Override
	public List<Travel> listReviewPageTra(Tra_Rev traRev) {
		List<Travel> listReviewPageTra = null;
//		log.info("BoardServiceImpl listReviewPageTra start");
		listReviewPageTra = bd.listReviewPageTra(traRev);
//		log.info("BoardServiceImpl listReviewPageTra.size() -> " + listReviewPageTra.size());
		return listReviewPageTra;
	}
	
	// 마이페이지 여행지 리뷰 페이징
	@Override
	public int totalReviewPageTra(long memberId) {
//		log.info("BoardServiceImpl totalReviewPageTra start");
		int totalReviewPageTra = bd.totalReviewPageTra(memberId);
//		log.info("BoardServiceImpl  totalReviewPageTra -> " + totalReviewPageTra );		
		return totalReviewPageTra;
	}
	
	// 마이페이지 숙소 리뷰 리스트
	@Override
	public List<House> listReviewPageHou(Hou_Rev houRev) {
		List<House> listReviewPageHou = null;
//		log.info("BoardServiceImpl listReviewPageHou start");
		listReviewPageHou = bd.listReviewPageHou(houRev);
//		log.info("BoardServiceImpl listReviewPageHou.size() -> " + listReviewPageHou.size());
		return listReviewPageHou;
	}
	
	// 마이페이지 숙소 리뷰 페이징
	@Override
	public int totalReviewPageHou(long memberId) {
//		log.info("BoardServiceImpl totalReviewPageHou start");
		int totalReviewPageHou = bd.totalReviewPageHou(memberId);
//		log.info("BoardServiceImpl  totalReviewPageHou -> " + totalReviewPageHou );		
		return totalReviewPageHou;
	}
	
	
	// 마이페이지 맛집 리뷰 리스트
	@Override
	public List<Res> listReviewPageRes(Res_Rev resRev) {
		List<Res> listReviewPageRes = null;
//		log.info("BoardServiceImpl listReviewPageRes start");
		listReviewPageRes = bd.listReviewPageRes(resRev);
//		log.info("BoardServiceImpl listReviewPageRes.size() -> " + listReviewPageRes.size());
		return listReviewPageRes;
	}
	
	// 마이페이지 맛집 리뷰 페이징
	@Override
	public int totalReviewPageRes(long memberId) {
//		log.info("BoardServiceImpl totalReviewPageRes start");
		int totalReviewPageRes = bd.totalReviewPageRes(memberId);
//		log.info("BoardServiceImpl totalReviewPageRes -> " + totalReviewPageRes );		
		return totalReviewPageRes;
	}
	
	// 태그 관심사 등록, 삭제
	@Override
	public List<Interest> mypageTagUpdate(Interest interest) {
//		log.info("BoardServiceImpl mypageTagUpdate start");
		List<Interest> mypageTagUpdate = null;
		mypageTagUpdate = bd.mypageTagUpdate(interest);
//		log.info("BoardServiceImpl mypageTagUpdate.size() -> " + mypageTagUpdate.size());
		return mypageTagUpdate;
	}
	
	// 유저 페이지 점수 업데이트
	@Override
	public int userScoreUpdate(Score score) {
//		log.info("BoardServiceImpl userScoreUpdate start");
		int userScoreUpdate = bd.userScoreUpdate(score);
//		log.info("BoardServiceImpl userScoreUpdate -> " + userScoreUpdate);
		return userScoreUpdate;
	}
	

}
