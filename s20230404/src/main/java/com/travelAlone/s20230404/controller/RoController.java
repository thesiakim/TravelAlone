package com.travelAlone.s20230404.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.dto.ro.BoardWriteRequestDto;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.ro.RoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RoController {
   
   private final RoService      rs;
   
   
   // 전체 게시판 이동
   @RequestMapping(value = "listAllBoard")
   public String listBoardAll(@LoginUser MemberJpa memberJpa, Board board, String currentPage, Model model) {
      log.info("roController listBoardAll 시작");
      log.info("roController listBoardAll currentPage는 "+ currentPage);
      log.info("roController listBoardAll orderList는" + board.getOrderList());
      
      // Board table 전체 count
      int boardAllCnt = rs.boardAllCnt();
      log.info("roController listBoardAll boardAllCnt는 "+ boardAllCnt);
      
      // Paging 작업
      Paging page = new Paging(boardAllCnt, currentPage);      
      
      // Board에 추가 setting
      board.setStart(page.getStart());
      board.setEnd(page.getEnd());
      
      List<Board> listAllBoard = rs.listAllBoard(board);
      
      log.info("roController listBoardAll listAllBoard.size()는 "+ listAllBoard.size());
      
      model.addAttribute("boardAllCnt" ,boardAllCnt);
      model.addAttribute("listAllBoard", listAllBoard);
      model.addAttribute("page", page);
      if (memberJpa != null){
			model.addAttribute("user_id", memberJpa.getId());
		}
      
      return "ro/boardAllListForm";
   }
   
   // 게시판 이동
   @RequestMapping(value = "listBoard")
   public String boardList(@LoginUser MemberJpa memberJpa, Board board, String currentPage, Model model) {

      log.info("roController boardList 시작");
      log.info("roController boardList cuurentPage는 "+ currentPage);
      
      int boardCnt = rs.boardCnt(board);
      log.info("roController boardList boardCnt는 "+ boardCnt);
      
      // Paging 작업
      Paging page = new Paging(boardCnt, currentPage);
      
      // Board에 추가 setting
      board.setStart(page.getStart());
      board.setEnd(page.getEnd());
      
      List<Board> listBoard = rs.listBoard(board);
      
      log.info("roController boardList listBoard.size()는 "+ listBoard.size());
      
      model.addAttribute("boardCnt" ,boardCnt);
      model.addAttribute("listBoard", listBoard);
      model.addAttribute("page", page);
      if (memberJpa != null){
			model.addAttribute("user_id", memberJpa.getId());
		}
      
      return "ro/boardListForm";
      }

   
   // 게시판 게시물 페이지 이동
   @RequestMapping(value = "detailBoard")
   public String detailBoard(@LoginUser MemberJpa memberJpa, Board board, Model model, HttpServletRequest request, HttpServletResponse response) {
       log.info("roController detailBoard 시작");
       log.info("roController detailBoard board_id는 "+ board.getBoard_id());
       log.info("roController detailBoard b_common_board는 "+ board.getB_common_board());
       String result = "";
       int viewCount = 0;

       // 쿠키 id + 글 id를 하나의 key로 사용 -> 조회수 중복 방지
       String cookieKey = "viewed" + board.getBoard_id();
       Cookie[] cookies = request.getCookies();
       boolean boardViewChk = false;
       if (cookies != null) {
           for (Cookie cookie : cookies) {
               if (cookie.getName().equals(cookieKey)) {
                   // 쿠키에 해당 게시물을 이미 조회한 경우, 조회수 증가하지 않음

                  boardViewChk = true;

                   break;
               }
           }
       }
       if (!boardViewChk) {
           // 쿠키에 해당 게시물을 처음 조회한 경우, 조회수 증가
           viewCount = rs.veiwCount((int) board.getBoard_id());
           Cookie cookie = new Cookie(cookieKey, "true");
           cookie.setMaxAge(60 * 60 * 24 * 30); // 쿠키 유효기간 30일로 설정
           response.addCookie(cookie);
       }

       model.addAttribute("board_id", board.getBoard_id());
       model.addAttribute("b_common_board", board.getB_common_board());

       List<Board> listBoardS = rs.detailBoard(board.getBoard_id());

       log.info("roController detailBoard listBoardC.size()는 "+ listBoardS.size());

       model.addAttribute("viewCount", viewCount);
       model.addAttribute("listBoardS", listBoardS);

       if (memberJpa != null) {
           log.info("roController detailBoard memberJpa.getId()는 "+ memberJpa.getId());
           model.addAttribute("user_id", memberJpa.getId());
           result ="ro/detailBoardForm";
       }

       result = "ro/detailBoardForm";
       return result;
   }
   
   // 게시물 작성 페이지 이동
   @RequestMapping(value = "writeBoardForm")
   public String writeFormBoard(@LoginUser MemberJpa memberJpa, Board board, Model model) {
	   log.info("roController writeFormBoard 시작");
      
	   String resultForm = "";
      
	   if(memberJpa != null) {
		   log.info("roController writeFormBoard memberJpa.getId()는 "+ memberJpa.getId());
		   model.addAttribute("user_id", memberJpa.getId());
		   model.addAttribute("b_common_board", board.getB_common_board());
		   resultForm = "ro/writeBoardForm";

	   }else {
    	  resultForm = "km/login";
	   }
	   return resultForm;
   }
   
   // 게시물 작성
   @PostMapping(value = "writeBoard")
   @ResponseBody
   public String writeBoard(@RequestPart(value = "key") BoardWriteRequestDto requestDto,
                      @RequestPart(value = "file", required = false) List<MultipartFile> files,
                      @LoginUser MemberJpa memberJpa,
                      Model model) throws Exception {
	   log.info("roController writeBoard start");

	   if (memberJpa == null){
		   throw new Exception("로그인 해주세요!");
	   }
	   requestDto.addMemberId(memberJpa.getId());
      
	   int insertResult = rs.insertBoard(requestDto,files);
	   log.info("roController writeBoard insertResult는 "+ insertResult);
	   return ""+insertResult;
	}
   
   // 게시물 댓글 작성
   @PostMapping(value = "writeBoardRe")
   public String writeReBoard(@LoginUser MemberJpa memberJpa, Board board, Model model) {
      log.info("roController writeReBoard 시작");
      log.info("roController writeReBoard board_id는 "+ board.getBoard_id());
      log.info("roController writeReBoard b_common_board()는 "+ board.getB_common_board());
      
      model.addAttribute("board_id", board.getBoard_id());
      model.addAttribute("b_common_board", board.getB_common_board());
      
      int insertResult = rs.insertReBoard(board);
      
      String resultForm = "";
      
      log.info("roController writeReBoard insertResult는 "+ insertResult);
      
      if(insertResult > 0) {
         resultForm = "forward:detailBoard";
         
      } else {
         model.addAttribute("msg", "입력 실패 확인해 보세요");
         resultForm = "km/login";
      }
      return resultForm;
   }

	// 게시물 수정 페이지 이동
	@RequestMapping(value = "updateBoardForm", method = RequestMethod.POST)
	public String updateFormBoard(Board board, Model model) {
		log.info("roController updateBoardForm 시작");
      
		// 게시물에 있는 이미지 list 갖고 오기
		List<BodImg> listBoardImgs = rs.listImgBoard(board.getBoard_id());
		System.out.println("RoController updateBoardForm listBoardImgs.size()->"+listBoardImgs.size());
      
		model.addAttribute("board", board);
		model.addAttribute("listBoardImgs", listBoardImgs);
      
		return "ro/updateBoardForm";
	}
   
	// 게시물 수정
	@PostMapping(value = "updateBoard")
	@ResponseBody
	public String updateBoard(
                        // formData에 들어 있는 boardData, imgFiled을 각각 parameter값으로 받기 위해 RequestPart사용
                        @RequestPart(value = "boardData", required = false) Board board,
                        @RequestPart(value = "imgFile", required = false) List<MultipartFile> imgFiles,
                        Model model
                        ) {
		log.info("roController updateBoard 시작");
      
		int updateResult = rs.updateBoard(board, imgFiles);
		log.info("roController updateBoard updateResult"+ updateResult);
      
		return ""+ updateResult;
	}
   
   
	// 게시물 삭제
	@ResponseBody
	@PostMapping(value = "deleteBoard")
	public String deleteBoard(long board_id, Model model) {

		log.info("roController deleteBoard 시작");
		log.info("roController deleteBoard board_id는 "+ board_id);
	      
		int delResult = rs.deleteBoard(board_id);
		String delResultStr = Integer.toString(delResult);
		log.info("roController deleteBoard delResultStr는 "+ delResultStr);
	      
		return delResultStr;
	}
   
	// 게시물 댓글 삭제
	@ResponseBody
	@PostMapping(value = "deleteBoardRe")
	public String deleteReBoard(Board board, Model model) {   

		String successStatus = "1";
		log.info("roController deleteReBoard 시작");
		log.info("roController deleteReBoard board.b_re_step는 "+ board.getB_re_step());
	      
		rs.deleteReBoard(board);
		log.info("deleteReBoard successStatus ->"+successStatus);
		return successStatus;
	}

	// 게시물 댓글 수정
	@ResponseBody
	@PostMapping(value = "UpdateBoardRe")
	public String updateReBoard(Board board, Model model) {
		log.info("roController updateReBoard 시작");
	      
		int updateCount = rs.updateReBoard(board);
		log.info("roController updateReBoard updateCount는 "+ updateCount);
		String updateResult = Integer.toString(updateCount);
	      
		return updateResult;
	}
	
	// 게시물 이미지 삭제
	@ResponseBody
	@RequestMapping("/deleteBoardImg")
	public String deleteImgBoard(BodImg bodImg) {
		log.info("roController deleteImgBoard 시작");
	      
		int deleteCount = rs.deleteImgBoard(bodImg);
		log.info("roController deleteImgBoard deleteResult는 "+ deleteCount);
	      
		String deleteResult = Integer.toString(deleteCount);
	      
		return deleteResult;
	}

}