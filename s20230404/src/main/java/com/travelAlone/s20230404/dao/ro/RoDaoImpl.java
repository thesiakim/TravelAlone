package com.travelAlone.s20230404.dao.ro;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class RoDaoImpl implements RoDao {

	private final SqlSession   session;

	// 전체 게시판 개수
	@Override
	public int boardAllCnt() {
		int boardAllCnt = 0;
		log.info("roDaoImpl boardAllCnt 시작");
      
		try {
			boardAllCnt = session.selectOne("roBoardAllCount");
			log.info("roDaoImpl boardAllCnt는 "+ boardAllCnt);
         
		} catch (Exception e) {
			log.info("roDaoImpl boardAllCnt Exception -> "+ e.getMessage());
		}
		return boardAllCnt;
	}

	// 전체 게시판 list
	@Override
	public List<Board> listAllBoard(Board board) {
		List<Board> listBoardAll = null;
		log.info("roDaoImpl listAllBoard 시작");
      
		try {
			listBoardAll = session.selectList("roBoardAllList", board);
			log.info("roDaoImpl listAllBoard listBoardAll.size()는 "+ listBoardAll.size());
		} catch (Exception e) {
			log.info("roDaoImpl listAllBoard e.getMessage() -> "+ e.getMessage());
		}
		return listBoardAll;
	}

	// 게시판 개수
	@Override
	public int boardCnt(Board board) {
		int boardCnt = 0;
		log.info("roDaoImpl boardCnt 시작");
      
		try {
			boardCnt = session.selectOne("roBoardCount", board);
			log.info("roDaoImpl boardCnt는 "+ boardCnt);
         
		} catch (Exception e) {
			log.info("roDaoImpl boardCnt Exception -> "+ e.getMessage());
		}
		return boardCnt;
	}

	// 게시판 list
	@Override
	public List<Board> listBoard(Board board) {
		List<Board> listBoard = null;
		log.info("roDaoImpl listBoard 시작");
      
		try {
			listBoard = session.selectList("roBoardList", board);
			log.info("roDaoImpl listBoard listBoard.size()는 "+ listBoard.size());
		} catch (Exception e) {
			log.info("roDaoImpl listBoard e.getMessage() -> "+ e.getMessage());
		}
		return listBoard;
	}
   
	// 조회수
	@Override
	public int veiwCount(int board_id) {
		log.info("roDaoImpl veiwCount 시작");
		int veiwCount = 0;
		try {
			veiwCount = session.update("roBoardView",board_id);
			log.info("roDaoImpl veiwCount는 "+ veiwCount);
		} catch (Exception e) {
			log.info("roDaoImpl veiwCount e.getMessage() -> "+ e.getMessage());
		}
		return veiwCount;
	}
   
	// 게시물 정보 list
	@Override
	public List<Board> detailBoard(long board_id) {
		List<Board> listBoardS = null;
		log.info("roDaoImpl detailBoard 시작");
      
		try {
			listBoardS = session.selectList("roBoardSelList", board_id);
			log.info("roDaoImp detailBoard listBoardRe.size()는 "+ listBoardS.size());
		} catch (Exception e) {
			log.info("roDaoImpl detailBoard e.getMessage -> "+ e.getMessage());
		}
		return listBoardS;
	}

	// 게시물 게시글 삽입
	@Override
	public long insertBoard(Board board) {
		long createBoardId = 0L;
		log.info("jhDaoImpl insertBoard start");
      
		try {
			session.insert("roBoardInsert", board);
			createBoardId = board.getBoard_id();
			log.info("jhDaoImpl insertBoard createBoardId는 "+ createBoardId);
		} catch (Exception e) {
			log.info("jhDaoImpl insertBoard e.getMessage는 "+ e.getMessage());
		}
		return createBoardId;
	}
   
	// 게시물 이미지 삽입
	@Override
	public int insertBodImg(List<BodImg> bodImgs) {
		int insertResult = 0;
		log.info("jhDaoImpl insertBoardImg start");
      
		for (BodImg img : bodImgs) {
			try {
				session.insert("roBoardImgInsert", img);
				log.info("jhDaoImpl insertBodImg insertResult는 "+ insertResult);   
				insertResult++;
			} catch (Exception e) {
				log.info("jhDaoImpl insertBodImg e.getMessage는 "+ e.getMessage());
			}
		}
		return insertResult;
	}
   
	// 게시물 댓글 작성
	@Override
	public int insertReBoard(Board board) {
		int insertResult = 0;
		log.info("roDaoImp insertReBoard 시작");
      
		try {
			insertResult = session.insert("roBoardReInsert", board);
			log.info("roDaoImp insertReBoard insertResult는 "+ insertResult);
		} catch (Exception e) {
			log.info("roDaoImp insertReBoard e.getMessage는 "+ e.getMessage());
		}
		return insertResult;
	}

	// DB 게시물에 있는 모든 이미지 파일 삭제
	@Override
	public int deleteImgBoard(long board_id) {
		int delImgResult = 0;
		log.info("roDaoImp deleteImgBoard 시작");
      
		try {
			delImgResult = session.delete("roBoardImgDelete", board_id);
			log.info("roDaoImp deleteImgBoard delImgResult는 "+ delImgResult);
		} catch (Exception e) {
			log.info("roDaoImp deleteBoard Exception -> "+ e.getMessage());
		}
		return delImgResult;
	}

	// DB에 Board 이미지 list들 불러오기
	@Override
	public List<String> detailBoardImg(long board_id) {
		List<String> BodImgList = new ArrayList<>();
		log.info("roDaoImp detailBoardImg 시작");
      
		try {
			BodImgList = session.selectList("detailBoardImg", board_id);
			log.info("roDaoImp detailBoardImg Result size는 "+ BodImgList.size());
		} catch (Exception e) {
			log.info("roDaoImp detailBoardImg Exception -> "+ e.getMessage());
		}
		return BodImgList;
	}

	// DB 게시물 게시글 삭제
	@Override
	public int deleteBoard(long board_id) {
		int delResult = 0;
		log.info("roDaoImp delResult 시작");
      
		try {
			delResult = session.delete("roBoardDelete", board_id);
			log.info("roDaoImp deleteBoard delResult는 "+ delResult);
		} catch (Exception e) {
			log.info("roDaoImp deleteBoard Exception -> "+ e.getMessage());
		}
		return delResult;
	}
   
	// 댓글 및 대댓글 삭제
	@Override
	public void deleteReBoard(Board board) {
		log.info("roDaoImp deleteReBoard 시작");
		session.selectList("roBoardDeleteRe", board);
		// call by reference여서 리턴을 void로 해도 상관없음
	}
   
	// 댓글 수정
	@Override
	public int updateReBoard(Board board) {
		int updateCount = 0;
		log.info("roDaoImp updateReBoard 시작");
      
		try {
			updateCount = session.update("roBoardUpdateRe", board);
			log.info("roDaoImp updateReBoard updateCount는 "+ updateCount);
		} catch (Exception e) {
			log.info("roDaoImp updateReBoard Exception -> "+ e.getMessage());
		}
		return updateCount;
	}

	// 게시물 수정
	@Override
	public int updateBoard(Board board) {
		int updateCount = 0;
		log.info("roDaoImp updateBoard 시작");
      
		try {
			updateCount = session.update("roBoardUpdate", board);
			log.info("roDaoImp updateBoard updateCount는 "+ updateCount);
		} catch (Exception e) {
			log.info("roDaoImp updateBoard Exception -> "+ e.getMessage());
		}
		return updateCount;
	}
   
	// DB 게시물 이미지 파일 삭제
	@Override
	public int deleteImgOneBoard(BodImg bodImg) {
		int deleteResult = 0;
		log.info("roDaoImp deleteImgOneBoard 시작");
      
		try {
			deleteResult = session.delete("roBoardImgDeleteOne", bodImg);
			log.info("roDaoImp deleteImgOneBoard deleteResult는 "+ deleteResult);
		} catch (Exception e) {
			log.info("roDaoImp deleteImgOneBoard Exception -> "+ e.getMessage());
		}
		return deleteResult;
	}
   
	// 선택한 이미지를 DB에 갖고 오기
	@Override
	public String selBoardImg(BodImg bodImg) {
		String imgBoard = "";
		log.info("roDaoImp selBoardImg 시작");
      
		try {
			imgBoard = session.selectOne("oneBoardImg", bodImg);
			log.info("roDaoImp selBoardImg imgBoard는 "+ imgBoard);
		} catch (Exception e) {
			log.info("roDaoImp selBoardImg Exception -> "+ e.getMessage());
		}
		return imgBoard;
	}
   
	// 게시물 이미지 리스트
	@Override
	public List<BodImg> listBoardImg(long board_id) {
		List<BodImg> listBoardImg = new ArrayList<>();
		log.info("roDaoImp listBoardImg 시작");
      
		try {
			listBoardImg = session.selectList("listBoardImg", board_id);
			log.info("roDaoImp listBoardImg listBoardImg.size()는 "+ listBoardImg.size());
		} catch (Exception e) {
			log.info("roDaoImp listBoardImg Exception -> "+ e.getMessage());
		}
		return listBoardImg;
	}

	

}