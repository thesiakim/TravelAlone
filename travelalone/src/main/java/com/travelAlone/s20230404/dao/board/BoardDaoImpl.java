package com.travelAlone.s20230404.dao.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BoardDaoImpl implements BoardDao {

	private final SqlSession	session;
	
	// 전체 게시판 개수
	@Override
	public int boardAllCnt() {
		int boardAllCnt = 0;
//		log.info("BoardDaoImpl boardAllCnt 시작");
		
		try {
			boardAllCnt = session.selectOne("roBoardAllCount");
//			log.info("BoardDaoImpl boardAllCnt는 "+ boardAllCnt);
			
		} catch (Exception e) {
//			log.info("BoardDaoImpl boardAllCnt Exception -> "+ e.getMessage());
		}
		return boardAllCnt;
	}

	// 전체 게시판 list
	@Override
	public List<Board> listAllBoard(Board board) {
		List<Board> listBoardAll = null;
//		log.info("BoardDaoImpl listAllBoard 시작");
		
		try {
			listBoardAll = session.selectList("roBoardAllList", board);
//			log.info("BoardDaoImpl listAllBoard listBoardAll.size()는 "+ listBoardAll.size());
			
		} catch (Exception e) {
//			log.info("BoardDaoImpl listAllBoard e.getMessage() -> "+ e.getMessage());
		}
		return listBoardAll;
	}

	// 게시판 개수
	@Override
	public int boardCnt(Board board) {
		int boardCnt = 0;
//		log.info("BoardDaoImpl boardCnt 시작");
		
		try {
			boardCnt = session.selectOne("roBoardCount", board);
//			log.info("BoardDaoImpl boardCnt는 "+ boardCnt);
		} catch (Exception e) {
//			log.info("BoardDaoImpl boardCnt Exception -> "+ e.getMessage());
		}
		return boardCnt;
	}

	// 게시판 list
	@Override
	public List<Board> listBoard(Board board) {
		List<Board> listBoard = null;
//		log.info("BoardDaoImpl listBoard 시작");
		
		try {
			listBoard = session.selectList("roBoardList", board);
//			log.info("BoardDaoImpl listBoard listBoard.size()는 "+ listBoard.size());
		} catch (Exception e) {
//			log.info("BoardDaoImpl listBoard e.getMessage() -> "+ e.getMessage());
		}
		return listBoard;
	}
	   
	// 조회수
	@Override
	public int veiwCount(long board_id) {
//		log.info("BoardDaoImpl veiwCount 시작");
		int veiwCount = 0;
		try {
			veiwCount = session.update("roBoardView",board_id);
//			log.info("BoardDaoImpl veiwCount는 "+ veiwCount);
		} catch (Exception e) {
//			log.info("BoardDaoImpl veiwCount e.getMessage() -> "+ e.getMessage());
		}
		return veiwCount;
	}
	
	// 게시물 정보 list
	@Override
	public List<Board> detailBoard(long board_id) {
		List<Board> listBoardS = null;
//		log.info("BoardDaoImpl detailBoard 시작");
		
		try {
			listBoardS = session.selectList("roBoardSelList", board_id);
//			log.info("BoardDaoImpl detailBoard listBoardRe.size()는 "+ listBoardS.size());
		} catch (Exception e) {
//			log.info("BoardDaoImpl detailBoard e.getMessage -> "+ e.getMessage());
		}
		return listBoardS;
	}

	// 게시물 게시글 삽입
	@Override
	public long insertBoard(Board board) {
		long createBoardId = 0L;
//		log.info("BoardDaoImpl insertBoard start");
		
		try {
			session.insert("roBoardInsert", board);
			createBoardId = board.getBoard_id();
//			log.info("BoardDaoImpl insertBoard createBoardId는 "+ createBoardId);
			
		} catch (Exception e) {
//			log.info("BoardDaoImpl insertBoard e.getMessage는 "+ e.getMessage());
		}
		return createBoardId;
	}
	   
	// 게시물 이미지 삽입
	@Override
	public int insertBodImg(List<BodImg> bodImgs) {
		int insertResult = 0;
//		log.info("BoardDaoImpl insertBoardImg start");
		
		for (BodImg img : bodImgs) {
			try {
				session.insert("roBoardImgInsert", img);
//				log.info("BoardDaoImpl insertBodImg insertResult는 "+ insertResult);   
				insertResult++;
				
			} catch (Exception e) {
//				log.info("BoardDaoImpl insertBodImg e.getMessage는 "+ e.getMessage());
			}
		}
		return insertResult;
	}
	   
	// 게시물 댓글 작성
	@Override
	public int insertReBoard(Board board) {
		int insertResult = 0;
		log.info("BoardDaoImpl insertReBoard 시작");
		
		try {
			insertResult = session.insert("roBoardReInsert", board);
			log.info("BoardDaoImpl insertReBoard insertResult는 "+ insertResult);
		} catch (Exception e) {
			log.info("BoardDaoImpl insertReBoard e.getMessage는 "+ e.getMessage());
		}
		return insertResult;
	}
	
	// 게시물 대댓글 작성
	@Override
	public int insertReLevel(Board board) {
		int insertReLevel = 0;
		int updateReLevel = 0;
//		log.info("BoardDaoImpl insertReLevel start");
		
		try {
			
			updateReLevel = session.update("updateReLevel", board);
//			log.info("BoardDaoImpl updateReLevel -> " + updateReLevel);
			
			insertReLevel = session.insert("insertReLevel", board);
//			log.info("BoardDaoImpl insertReLevel -> " + insertReLevel);
		} catch (Exception e) {
//			log.info("BoardDaoImpl insertReLevel Exception -> " + e.getMessage());
		}
		return insertReLevel;
	}

	// DB 게시물에 있는 모든 이미지 파일 삭제
	@Override
	public int deleteImgBoard(long board_id) {
		int delImgResult = 0;
//		log.info("BoardDaoImpl deleteImgBoard 시작");
		
		try {
			delImgResult = session.delete("roBoardImgDelete", board_id);
//			log.info("BoardDaoImpl deleteImgBoard delImgResult는 "+ delImgResult);
		} catch (Exception e) {
//			log.info("BoardDaoImpl deleteBoard Exception -> "+ e.getMessage());
		}
		return delImgResult;
	}

	// DB에 Board 이미지 list들 불러오기
	@Override
	public List<String> detailBoardImg(long board_id) {
		List<String> BodImgList = new ArrayList<>();
//		log.info("BoardDaoImpl detailBoardImg 시작");
		
		try {
			BodImgList = session.selectList("detailBoardImg", board_id);
//			log.info("BoardDaoImpl detailBoardImg Result size는 "+ BodImgList.size());
			
		} catch (Exception e) {
//			log.info("BoardDaoImpl detailBoardImg Exception -> "+ e.getMessage());
		}
		return BodImgList;
	}

	// DB 게시물 게시글 삭제
	@Override
	public int deleteBoard(long board_id) {
		int delResult = 0;
//		log.info("BoardDaoImpl delResult 시작");
		
		try {
			delResult = session.delete("roBoardDelete", board_id);
//			log.info("BoardDaoImpl deleteBoard delResult는 "+ delResult);
		} catch (Exception e) {
//			log.info("BoardDaoImpl deleteBoard Exception -> "+ e.getMessage());
		}
		return delResult;
	}
	   
	// 댓글 및 대댓글 삭제
	@Override
	public void deleteReBoard(Board board) {
//		log.info("BoardDaoImpl deleteReBoard 시작");
		session.selectList("roBoardDeleteRe", board);
		// call by reference여서 리턴을 void로 해도 상관없음
	}
	   
	// 댓글 수정
	@Override
	public int updateReBoard(Board board) {
		int updateCount = 0;
//		log.info("BoardDaoImpl updateReBoard 시작");
		
		try {
			updateCount = session.update("roBoardUpdateRe", board);
//			log.info("BoardDaoImpl updateReBoard updateCount는 "+ updateCount);
		} catch (Exception e) {
//			log.info("BoardDaoImpl updateReBoard Exception -> "+ e.getMessage());
		}
		return updateCount;
	}

	// 게시물 수정
	@Override
	public int updateBoard(Board board) {
		int updateCount = 0;
//		log.info("BoardDaoImpl updateBoard 시작");
		
		try {
			updateCount = session.update("roBoardUpdate", board);
//			log.info("BoardDaoImpl updateBoard updateCount는 "+ updateCount);
		} catch (Exception e) {
//			log.info("BoardDaoImpl updateBoard Exception -> "+ e.getMessage());
		}
		return updateCount;
	}
	   
	// DB 게시물 이미지 파일 삭제
	@Override
	public int deleteImgOneBoard(BodImg bodImg) {
		int deleteResult = 0;
//		log.info("BoardDaoImpl deleteImgOneBoard 시작");
		
		try {
			deleteResult = session.delete("roBoardImgDeleteOne", bodImg);
//			log.info("BoardDaoImpl deleteImgOneBoard deleteResult는 "+ deleteResult);
		} catch (Exception e) {
//			log.info("BoardDaoImpl deleteImgOneBoard Exception -> "+ e.getMessage());
		}
		return deleteResult;
	}
	   
	// 선택한 이미지를 DB에 갖고 오기
	@Override
	public String selBoardImg(BodImg bodImg) {
		String imgBoard = "";
//		log.info("BoardDaoImpl selBoardImg 시작");
		
		try {
			imgBoard = session.selectOne("oneBoardImg", bodImg);
//			log.info("BoardDaoImpl selBoardImg imgBoard는 "+ imgBoard);
		} catch (Exception e) {
//			log.info("BoardDaoImpl selBoardImg Exception -> "+ e.getMessage());
		}
		return imgBoard;
	}
	   
	// 게시물 이미지 리스트
	@Override
	public List<BodImg> listBoardImg(long board_id) {
		List<BodImg> listBoardImg = new ArrayList<>();
//		log.info("BoardDaoImpl listBoardImg 시작");
		
		try {
			listBoardImg = session.selectList("listBoardImg", board_id);
//			log.info("BoardDaoImpl listBoardImg listBoardImg.size()는 "+ listBoardImg.size());
		} catch (Exception e) {
//			log.info("BoardDaoImpl listBoardImg Exception -> "+ e.getMessage());
		}
		return listBoardImg;
	}
	
	// 추천 버튼
	@Override
	public int updateCount(Board board) {
//		log.info("BoardDaoImpl updateCount Start");
		int updateCount = 0;
		try {
			updateCount = session.update("jhLike",board);
//			log.info("BoardDaoImpl updateCount -> " + updateCount);
		} catch (Exception e) {
//			log.info("BoardDaoImpl updateCount Exception->" + e.getMessage());
		}
		
		return updateCount;
	}
	
	// 추천 취소 버튼
	@Override
	public int updateMinus(Board board) {
//		log.info("BoardDaoImpl updateMinus Start");
		int updateMinus = 0;
		try {
			updateMinus = session.update("jhLikeCancel", board);
//			log.info("BoardDaoImpl updateMinus -> "+ updateMinus);
		} catch (Exception e) {
//			log.info("jhDaoImpl updateMinus Exception->" + e.getMessage());
		}
		return updateMinus;
	}
	
	// 신고 버튼
	@Override
	public int reportMember(Warning warning) {
//		log.info("BoardDaoImpl reportMember Start");
		int reportMember = 0;
		try {
			reportMember = session.insert("reportMember", warning);
//			log.info("BoardDaoImpl reportMember -> " + reportMember);
		} catch (Exception e) {
//			log.info("BoardDaoImpl reportMember Exception -> " + e.getMessage());
		}
		return reportMember;
	}
	
	// 게시글 이미지 첨부 여부
	@Override
	public int updateBoardImgYn(long boardId) {
		int result = 0;
//		log.info("BoardDaoImpl updateBoardImgYn start");
		
		try {
			result = session.update("imgStoredFileYn", boardId);
//			log.info("BoardDaoImpl updateBoardImgYn board_id-> " + boardId);
			
		} catch (Exception e) {
//			log.info("BoardDaoImpl updateBoardImgYn Exception -> " + e.getMessage());
			
		}
		return result;
	}
	
	// 신고 수에 따른 게시글, 댓글 자동 블락
	@Override
	public int reportUpdate(Warning warning) {
		int updateResult = 0;
//		log.info("BoardDaoImpl reportUpdate start");
		
		try {
			updateResult = session.update("updateReportContent", warning);
//			log.info("BoardDaoImpl reportUpdate updateResult -> " + updateResult);
		} catch (Exception e) {
//			log.info("BoardDaoImpl reportUpdate Exception -> " + e.getMessage());
		}
		return updateResult;
	}
	
	// 마이페이지 커뮤니티 페이징용
	@Override
	public int myPageCommunityListCnt(long memberId) {
		int myPageCommunityListCnt = 0;
//		log.info("BoardDaoImpl myPageCommunityListCnt start");
	      
		try {
			myPageCommunityListCnt = session.selectOne("myPageCommunityListCnt", memberId);
//			log.info("BoardDaoImpl myPageCommunityListCnt -> "+ myPageCommunityListCnt);
	         
		} catch (Exception e) {
//			log.info("BoardDaoImpl myPageCommunityListCnt Exception -> "+ e.getMessage());
		}
		
		return myPageCommunityListCnt;
	}
	
	// 마이페이지 커뮤니티  리스트
	@Override
	public List<Board> myPageCommunityList(Board board) {
		List<Board> myPageCommunityList = null;
//		log.info("BoardDaoImpl myPageCommunityList start");
	      
		try {
			myPageCommunityList = session.selectList("myPageCommunityList", board);
//			log.info("BoardDaoImpl myPageCommunityList.size()는 "+ myPageCommunityList.size());
		} catch (Exception e) {
//			log.info("BoardDaoImpl myPageCommunityList e.getMessage() -> "+ e.getMessage());
		}
		return myPageCommunityList;
	}

	// 유저 마이페이지
	@Override
	public List<Member> userPage(long member_id) {
		List<Member> userPage = null;
//		log.info("BoardDaoImpl userPage start");
		try {
			userPage = session.selectList("userPage", member_id);
//			log.info("BoardDaoImpl userPage.size() -> " + userPage.size());
		} catch (Exception e) {
//			log.info("BoardDaoImpl userPage e.getMessage() -> " + e.getMessage());
		}
		return userPage;
	}
		
	// 마이페이지 여행지 리뷰 리스트
	@Override
	public List<Travel> listReviewPageTra(Tra_Rev traRev) {
		List<Travel> listReviewPageTra = null;
//		log.info("BoardDaoImpl listReviewPageTra start");
		try {
			listReviewPageTra = session.selectList("listReviewPageTra", traRev);
//			log.info("BoardDaoImpl listReviewPageTra.size() -> " + listReviewPageTra.size());
		} catch (Exception e) {
//			log.info("BoardDaoImpl listReviewPageTra e.getMessage() -> " + e.getMessage());
		}
		return listReviewPageTra;
	}
	
	// 마이페이지 여행지 리뷰 페이징
	@Override
	public int totalReviewPageTra(long member_id) {
		int totalReviewPageTra = 0;
//		log.info("BoardDaoImpl totalReviewPageTra start");
		try {
			totalReviewPageTra = session.selectOne("totalReviewPageTra", member_id);
//			log.info("BoardDaoImpl totalReviewPageTra->" + totalReviewPageTra);
		} catch (Exception e) {
//			log.info("BoardDaoImpl totalReviewPageTra Exception " +e.getMessage());
		}						
		return totalReviewPageTra;
	}
	
	// 마이페이지 숙소 리뷰 리스트
	@Override
	public List<House> listReviewPageHou(Hou_Rev houRev) {
		List<House> listReviewPageHou = null;
//		log.info("BoardDaoImpl listReviewPageHou start");
		try {
			listReviewPageHou = session.selectList("listReviewPageHou", houRev);
//			log.info("BoardDaoImpl listReviewPageHou.size() -> " + listReviewPageHou.size());
		} catch (Exception e) {
//			log.info("BoardDaoImpl listReviewPageHou e.getMessage() -> " + e.getMessage());
		}
		return listReviewPageHou;
	}
	
	// 마이페이지 숙소 리뷰 페이징
	@Override
	public int totalReviewPageHou(long member_id) {
		int totalReviewPageHou = 0;
//		log.info("BoardDaoImpl totalReviewPageHou start");
		try {
			totalReviewPageHou = session.selectOne("totalReviewPageHou", member_id);
//			log.info("BoardDaoImpl totalReviewPageHou->" + totalReviewPageHou);
		} catch (Exception e) {
//			log.info("BoardDaoImpl totalReviewPageHou Exception " +e.getMessage());
		}						
		return totalReviewPageHou;
	}
	
	// 마이페이지 맛집 리뷰 리스트
	@Override
	public List<Res> listReviewPageRes(Res_Rev resRev) {
		List<Res> listReviewPageRes = null;
//		log.info("BoardDaoImpl listReviewPageRes start");
		try {
			listReviewPageRes = session.selectList("listReviewPageRes", resRev);
//			log.info("BoardDaoImpl listReviewPageRes.size() -> " + listReviewPageRes.size());
		} catch (Exception e) {
//			log.info("BoardDaoImpl listReviewPageRes e.getMessage() -> " + e.getMessage());
		}
		return listReviewPageRes;
	}
	
	// 마이페이지 맛집 리뷰 페이징
	@Override
	public int totalReviewPageRes(long member_id) {
		int totalReviewPageRes = 0;
//		log.info("BoardDaoImpl totalReviewPageRes start");
		try {
			totalReviewPageRes = session.selectOne("totalReviewPageRes", member_id);
//			log.info("BoardDaoImpl totalReviewPageRes->" + totalReviewPageRes);
		} catch (Exception e) {
//			log.info("BoardDaoImpl totalReviewPageRes Exception " +e.getMessage());
		}						
		return totalReviewPageRes;
	}

	// 태그 업데이트
	@Override
	public List<Interest> mypageTagUpdate(Interest interest) {
		List<Interest> mypageTagUpdate = null;
//		log.info("BoardDaoImpl mypageTagUpdate start");
		try {
			mypageTagUpdate = session.selectList("mypageTagUpdate", interest);
//			log.info("BoardDaoImpl mypageTagUpdate.size()->" + mypageTagUpdate.size());
		} catch (Exception e) {
//			log.info("BoardDaoImpl mypageTagUpdate Exception " +e.getMessage());
		}						
		return mypageTagUpdate;
	}
	
	// 유저 페이지 점수 업데이트
	@Override
	public int userScoreUpdate(Score score) {
//		log.info("BoardDaoImpl userScoreUpdate Start");
		int userScoreUpdate = 0;
		try {
			userScoreUpdate = session.update("userScoreUpdate",score);
//			log.info("BoardDaoImpl userScoreUpdate -> " + userScoreUpdate);
		} catch (Exception e) {
//			log.info("BoardDaoImpl userScoreUpdate Exception->" + e.getMessage());
		}
		return userScoreUpdate;
	}
	
}
