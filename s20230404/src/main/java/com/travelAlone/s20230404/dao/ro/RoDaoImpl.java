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
   
   @Override
   public List<Board> detailBoard(int board_id) {
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

@Override
public List<String> detailBoardImg(int board_id) {
   List<String> result = new ArrayList<>();
    log.info("roDaoImp detailBoardImg 시작");
    
    try {
       result = session.selectList("detailBoardImg", board_id);
       log.info("roDaoImp detailBoardImg Result size는 "+ result.size());
    } catch (Exception e) {
       log.info("roDaoImp detailBoardImg Exception -> "+ e.getMessage());
    }
   
   return result;
}

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
   
   @Override
   public void deleteReBoard(Board board) {
      log.info("roDaoImp deleteReBoard 시작");
      session.selectList("roBoardDeleteRe", board);
      // call by reference여서 리턴을 void로 해도 상관없음
   }

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

   
}