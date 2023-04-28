package com.travelAlone.s20230404.service.ro;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travelAlone.s20230404.dao.ro.roDao;
import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.dto.ro.BoardWriteRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class roServiceImpl implements roService {

   private final roDao      rd;

   @Override
   public int boardAllCnt() {
      log.info("roService boardAllCnt 시작");
      int boardAllCnt = rd.boardAllCnt();
      log.info("roService boardAllCnt는 "+ boardAllCnt);
      
      return boardAllCnt;
   }

   @Override
   public List<Board> listAllBoard(Board board) {
      List<Board> listBoardAll = null;
      log.info("roService listAllBoard 시작");
      
      listBoardAll = rd.listAllBoard(board);
      
      return listBoardAll;
   }

   @Override
   public int boardCnt(Board board) {
      log.info("roService boardCnt 시작");
      int boardCnt = rd.boardCnt(board);
      log.info("roService boardCnt는 "+ boardCnt);
      
      return boardCnt;
   }

   @Override
   public List<Board> listBoard(Board board) {
      List<Board> listBoard = null;
      log.info("roService listBoard 시작");
      
      listBoard = rd.listBoard(board);
      log.info("roService listBoard listBoard.size()는 "+ listBoard.size());
      
      return listBoard;
   }
   
   @Override
   public int veiwCount(int board_id) {
      log.info("roServiceImpl veiwCount Start...");
      int veiwCount = 0;
      veiwCount = rd.veiwCount(board_id);
      return veiwCount;
   }
   
   @Override
   public List<Board> detailBoard(int board_id) {
      List<Board> listBoardS = null;
      log.info("roService detailBoard 시작");
      
      listBoardS = rd.detailBoard(board_id);
      List<String> listBoardImg = rd.detailBoardImg(board_id);
      listBoardS.get(0).setImg_stored_file(listBoardImg);
      
      if (!listBoardImg.isEmpty()) {
      } else {
      }
      
      log.info("roService detailBoard listBoardC.size()는 "+ listBoardS.size());
      
      return listBoardS;
   }

   @Override
   public int insertReBoard(Board board) {
      int insertResult = 0;
      log.info("roService insertReBoard 시작");
      insertResult = rd.insertReBoard(board);
      log.info("roService insertReBoard insertResult는 "+ insertResult);
      
      return insertResult;
   }

   @Override
   public int deleteBoard(long board_id) {
     int delImgResult = 0;
      int delResult = 0;
      log.info("roService deleteBoard 시작");
      
      delImgResult = rd.deleteImgBoard(board_id);
      log.info("roService deleteBoard delImgResult는 "+ delImgResult);
      
      delResult = rd.deleteBoard(board_id);
      log.info("roService deleteBoard delResult는 "+ delResult);
      
      log.info("roService deleteBoard delImgResult + delResult = "+ delImgResult + delResult);
      
      return delImgResult + delResult;
   }

   @Override
   public void deleteReBoard(Board board) {
      log.info("roService deleteReBoard 시작");
      rd.deleteReBoard(board);
      
   }


   
}