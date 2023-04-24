package com.travelAlone.s20230404.dao.ro;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class roDaoImpl implements roDao {

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
      log.info("roDaoImpl detailBoardRe 시작");
      
      try {
         listBoardS = session.selectList("roBoardSelList", board_id);
         log.info("roDaoImp detailBoardRe listBoardRe.size()는 "+ listBoardS.size());
      } catch (Exception e) {
         log.info("roDaoImpl detailBoard e.getMessage -> "+ e.getMessage());
      }
      
      return listBoardS;
   }

   @Override
   public int insertBoard(Board board) {
      int insertResult = 0;
      log.info("roDaoImp insertBoard 시작");
      
      try {
         insertResult = session.insert("roBoardInsert", board);
         log.info("roDaoImp insertBoard insertResult는 "+ insertResult);
      } catch (Exception e) {
         log.info("roDaoImp insertBoard e.getMessage는 "+ e.getMessage());
      }
      return insertResult;
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
      
   
   
}