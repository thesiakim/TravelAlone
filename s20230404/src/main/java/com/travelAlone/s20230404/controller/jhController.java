package com.travelAlone.s20230404.controller;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.jh.jhService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class jhController {

	private final jhService 		js;
	
	
	//테스트용
	@GetMapping("mainPage")
	public String test() {
		System.out.println("jhController start...");
		return "th/main";
	}
	
	
	//커뮤니티 리스트 보는 컨트롤러
	@GetMapping("listBoard")
	public String boardList(Board board, String currentPage, Model model) {
		System.out.println("jhController Start listBoard...");
		int totalBoard = js.totalBoard();
		System.out.println("jhController totalBoard -> " + totalBoard);
		
		Paging	page = new Paging(totalBoard, currentPage);
		board.setStartRow(page.getStart());
		board.setEndRow(page.getEnd());
		
		List<Board> listBoard = js.listBoard(board);
		System.out.println("jhController list listBoard.size() -> " + listBoard.size());
		
		model.addAttribute("totalBoard", totalBoard);
		model.addAttribute("listBoard" , listBoard);
		model.addAttribute("page"	 , page);

		return "th/jhList";
	}
	
	// listcontent = 게시판 글 보는 컨트롤러
	// veiwCount = 조회수 증가
	@GetMapping("content")
	public String contentBoard(Board board, Model model, HttpSession session) {
		System.out.println("jhController Start contentBoard...");
		List<Board> listcontent = js.contentBoard(board);
		int veiwCount = 0;
		String sessionId = session.getId() + board.getBoard_id(); // 세션 id + 글 id를 하나의 key로 사용
		if (session.getAttribute(sessionId) == null) {
			veiwCount = js.veiwCount(board);
			session.setAttribute(sessionId, true);
		}
		
		System.out.println("jhController list listcontent.size() -> " + listcontent.size());
		
		model.addAttribute("veiwCount",veiwCount);
		model.addAttribute("listcontent" , listcontent);
		return "th/jhContent";
	}
	
	//좋아요
	@GetMapping("boardLike")
	public int boardLike(int board_id, Model model) {
		int likeCount = js.boardLike(board_id);
		model.addAttribute("like", likeCount);
		return likeCount;
	}
	
	// 댓글 작성
	@GetMapping("writeReply")
	public String writeReply (Board board, Model model) {
		System.out.println("jhController Start writeReply...");
		String result = "";
		int insertReult = js.insertReply(board);
		System.out.println("jhController writeReply insertResult -> " + insertReult);
		
		if(insertReult > 0) {
			result = "redirect:/content?board_id=" + board.getBoard_id();
		}
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			result = "redirect:/content?board_id=" + board.getBoard_id();
		}
		return result;
	}
	
	// 게시글 작성시
	@GetMapping("writeBoardForm")
	public String writeBoardForm(Board board, Model model) {
		System.out.println("jhController writeBoardForm Start...");
		return "th/jhWrite";
	}

	
}
