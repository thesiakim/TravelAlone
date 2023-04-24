package com.travelAlone.s20230404.controller;

import java.util.List;

import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.dto.ro.BoardWriteRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.ro.roService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequiredArgsConstructor
public class roController {
	
	private final roService		rs;
	
	@GetMapping("listAllBoard")
	public String listBoardAll(Board board, String currentPage, Model model) {
		log.info("roController listBoardAll 시작");
		log.info("roController listBoardAll currentPage는 "+ currentPage);
		
		// Board table 전체 count
		int boardAllCnt = rs.boardAllCnt();
		log.info("roController totalBoardAll는 "+ boardAllCnt);
		
		// Paging 작업
		Paging page = new Paging(boardAllCnt, currentPage);
		
		// Board에 추가 setting
		board.setStartRow(page.getStart());
		board.setEndRow(page.getEnd());
		
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
		board.setStartRow(page.getStart());
		board.setEndRow(page.getEnd());
		
		List<Board> listBoard = rs.listBoard(board);
		
		log.info("roController boardList listBoard.size()는 "+ listBoard.size());
		
		model.addAttribute("boardCnt" ,boardCnt);
		model.addAttribute("listBoard", listBoard);
		model.addAttribute("page", page);
		
		return "ro/boardListForm";
		
	}
	
	@GetMapping(value = "detailBoard")
	public String detailBoard(int board_id, Model model) {
		log.info("roController detailBoard 시작");
		log.info("roController detailBoard board_id는 "+ board_id);
		
		List<Board> listBoardS = rs.detailBoard(board_id);
		log.info("roController detailBoard listBoardC.size()는 "+ listBoardS.size());
		
		model.addAttribute("listBoardS", listBoardS);
		
		return "ro/detailBoardForm";
	}
	
	@GetMapping(value = "writeBoardForm")
	public String writeFormBoard(Board board, Model model) {
		log.info("roController writeFormBoard 시작");
		log.info("roController writeFormBoard board.getB_common_board() 는 "+ board.getB_common_board());
		model.addAttribute("board", board);
		
		return "ro/writeBoardFormTest";
	}
	
	@PostMapping(value = "writeBoard")
	@ResponseBody
	public String writeBoard(@RequestPart(value = "key") BoardWriteRequestDto requestDto,
							 @RequestPart(value = "file", required = false) List<MultipartFile> files,
							 @LoginUser MemberJpa memberJpa,
							 Model model) throws Exception {
		log.info("roController writeBoard 시작");

		if (memberJpa == null){
			throw new Exception("로그인 해주세요!");
		}

		requestDto.addMemberId(memberJpa.getId());


		int insertResult = rs.insertBoard(requestDto,files);

		
		log.info("roController writeBoard insertResult는 "+ insertResult);
		
		return ""+insertResult;
	}
	
	@RequestMapping(value = "writeBoardRe", method = RequestMethod.POST)
	public String writeReBoard(Board board, Model model) {
		log.info("roController writeReBoard 시작");
		System.out.println("roController writeReBoard board -> "+ board);
		int insertResult = rs.insertReBoard(board);
		
		log.info("roController writeReBoard insertResult는 "+ insertResult);
		
		if(insertResult > 0) {
			
			return "redirect:ro/detailBoardForm";
			
		}
		else {
			model.addAttribute("msg", "입력 실패 확인해 보세요");
			return "forward:ro/detailBoardForm";
		}
	}
}