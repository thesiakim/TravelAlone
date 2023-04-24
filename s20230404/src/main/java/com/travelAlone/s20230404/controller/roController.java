package com.travelAlone.s20230404.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.service.ro.Paging;
import com.travelAlone.s20230404.service.ro.roService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class roController {
	
	private final roService		rs;
	
	 // 전체 게시판 이동
	   @RequestMapping(value = "listAllBoard")
	   public String listBoardAll(Board board, String currentPage, Model model) {
	      log.info("roController listBoardAll 시작");
	      log.info("roController listBoardAll currentPage는 "+ currentPage);
	      
	      // Board table 전체 count
	      int boardAllCnt = rs.boardAllCnt();
	      log.info("roController listBoardAll boardAllCnt는 "+ boardAllCnt);
	      
	      // Paging 작업
	      Paging page = new Paging(boardAllCnt, currentPage);      
	      
	      // Board에 추가 setting
	      board.setStartRow(page.getStartRow());
	      board.setEndRow(page.getEndRow());
	      
	      List<Board> listAllBoard = rs.listAllBoard(board);
	      
	      log.info("roController listBoardAll listAllBoard.size()는 "+ listAllBoard.size());
	      
	      model.addAttribute("boardAllCnt" ,boardAllCnt);
	      model.addAttribute("listAllBoard", listAllBoard);
	      model.addAttribute("page", page);
	      
	      
	      return "ro/boardAllListForm";
	   }
	
	
	@RequestMapping(value = "listBoard")
	public String boardList(Board board, String currentPage, Model model) {
		log.info("roController boardList 시작");
		log.info("roController boardList cuurentPage는 "+ currentPage);
		
		int boardCnt = rs.boardCnt(board);
		log.info("roController boardList boardCnt는 "+ boardCnt);
		
		// Paging 작업
		Paging page = new Paging(boardCnt, currentPage);
		
		// Board에 추가 setting
		board.setStartRow(page.getStartRow());
		board.setEndRow(page.getEndRow());
		
		List<Board> listBoard = rs.listBoard(board);
		
		log.info("roController boardList listBoard.size()는 "+ listBoard.size());
		
		model.addAttribute("boardCnt" ,boardCnt);
		model.addAttribute("listBoard", listBoard);
		model.addAttribute("page", page);
		
		return "ro/boardListForm";
		
	}
	
	@RequestMapping(value = "detailBoard")
	   public String detailBoard(Board board, Model model, HttpSession session) {
	      log.info("roController detailBoard 시작");
	      log.info("roController detailBoard board_id는 "+ board.getBoard_id());
	      log.info("roController detailBoard b_common_board는 "+ board.getB_common_board());
	      
	      int viewCount = 0;
	      
	      // 세션 id + 글 id를 하나의 key로 사용 -> 조회수 중복 방지
	      String sessionId = session.getId() + board.getBoard_id();
//	      if (session.getAttribute(sessionId) == null) {
//	         viewCount = rs.veiwCount((int) board.getBoard_id());
//	         session.setAttribute(sessionId, true);
//	      }
	      
	      model.addAttribute("board_id", board.getBoard_id());
	      model.addAttribute("b_common_board", board.getB_common_board());

	      List<Board> listBoardS = rs.detailBoard((int) board.getBoard_id());
	      log.info("roController detailBoard listBoardC.size()는 "+ listBoardS.size());
	      
	      model.addAttribute("viewCount", viewCount);
	      model.addAttribute("listBoardS", listBoardS);
	      
	      return "ro/detailBoardForm";
	   }
	
	@RequestMapping(value = "writeBoardForm", method = RequestMethod.POST)
   public String writeFormBoard(Board board, Model model) {
      log.info("roController writeFormBoard 시작");
      log.info("roController writeFormBoard board.getB_common_board() 는 "+ board.getB_common_board());
      model.addAttribute("board", board);
      
      return "ro/writeBoardForm";
   }
	
	@PostMapping(value = "writeBoard")
	public String writeBoard(Board board, Model model) {
		log.info("roController writeBoard 시작");
      
		int insertResult = rs.insertBoard(board);
      
		log.info("roController writeBoard insertResult는 "+ insertResult);
      
		if(insertResult > 0) return "redirect:listAllBoard";
		else {
			model.addAttribute("msg", "입력 실패 확인해 보세요");
			return "forward:writeBoardForm";
		}
	}
	
	 @RequestMapping(value = "writeBoardRe")
	   public String writeReBoard(Board board, Model model) {
	      log.info("roController writeReBoard 시작");
	      log.info("roController writeReBoard board_id는 "+ board.getBoard_id());
	      log.info("roController writeReBoard b_common_board()는 "+ board.getB_common_board());
	      
	      model.addAttribute("board_id", board.getBoard_id());
	      model.addAttribute("b_common_board", board.getB_common_board());

	      int insertResult = rs.insertReBoard(board);
	      
	      String detailResult = "";
	      
	      log.info("roController writeReBoard insertResult는 "+ insertResult);
	      
	      if(insertResult > 0) {
	         detailResult = "forward:detailBoard";
	         
	      } else {
	         model.addAttribute("msg", "입력 실패 확인해 보세요");
	         detailResult = "forward:detailBoard";
	      }
	      return detailResult;
	   }
	
}