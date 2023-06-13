package com.travelAlone.s20230404.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.Hou_Rev;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Interest;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Rev;
import com.travelAlone.s20230404.model.Score;
import com.travelAlone.s20230404.model.Tra_Rev;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.model.Warning;
import com.travelAlone.s20230404.model.dto.km.ScoreCount;
import com.travelAlone.s20230404.model.dto.km.UserPageResponseDto;
import com.travelAlone.s20230404.model.dto.ro.BoardWriteRequestDto;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.board.BoardService;
import com.travelAlone.s20230404.service.km.MypageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService		bs;
	private final MypageService		mypageService;
	
	// 전체 게시판 이동
	@RequestMapping(value = "listAllBoard")
	public String listBoardAll(@LoginUser MemberJpa memberJpa, @ModelAttribute Board board, String currentPage, Model model) {
//		log.info("BoardController listBoardAll 시작");
//		log.info("BoardController listBoardAll currentPage는 "+ currentPage);
//		log.info("BoardController listBoardAll orderList는" + board.getOrderList());
	  
		// Board table 전체 count
		int boardAllCnt = bs.boardAllCnt();
//		log.info("BoardController listBoardAll boardAllCnt는 "+ boardAllCnt);
		  
		// Paging 작업
		Paging page = new Paging(boardAllCnt, currentPage);      
		
		// Board에 추가 setting
		board.setStart(page.getStart());
		board.setEnd(page.getEnd());
	  
		List<Board> listAllBoard = bs.listAllBoard(board);
		
//		log.info("BoardController listBoardAll listAllBoard.size()는 "+ listAllBoard.size());
		
		model.addAttribute("board" ,board);
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
	public String boardList(@LoginUser MemberJpa memberJpa, @ModelAttribute Board board, String currentPage, Model model) {
		
//		log.info("BoardController boardList 시작");
//		log.info("BoardController boardList cuurentPage는 "+ currentPage);
		
		int boardCnt = bs.boardCnt(board);
//		log.info("BoardController boardList boardCnt는 "+ boardCnt);
		
		// Paging 작업
		Paging page = new Paging(boardCnt, currentPage);
	  
		// Board에 추가 setting
		board.setStart(page.getStart());
		board.setEnd(page.getEnd());
		
		List<Board> listBoard = bs.listBoard(board);
		
//		log.info("BoardController boardList listBoard.size()는 "+ listBoard.size());
		
		model.addAttribute("board" ,board);
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
	public String detailBoard(@LoginUser MemberJpa memberJpa, @ModelAttribute Board board, Model model, HttpServletRequest request, HttpServletResponse response) {
//		log.info("BoardController detailBoard 시작");
//		log.info("BoardController detailBoard board_id는 "+ board.getBoard_id());
//		log.info("BoardController detailBoard b_common_board는 "+ board.getB_common_board());
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
			viewCount = bs.veiwCount(board.getBoard_id());
			Cookie cookie = new Cookie(cookieKey, "true");
			cookie.setMaxAge(60 * 60 * 24 * 30); // 쿠키 유효기간 30일로 설정
			response.addCookie(cookie);
		}
		
		model.addAttribute("board_id", board.getBoard_id());
		model.addAttribute("b_common_board", board.getB_common_board());
		
		List<Board> listBoardS = bs.detailBoard(board.getBoard_id());
		
//		log.info("BoardController detailBoard listBoardC.size()는 "+ listBoardS.size());
		
		model.addAttribute("viewCount", viewCount);
		model.addAttribute("listBoardS", listBoardS);
		
		if (memberJpa != null) {
//			log.info("BoardController detailBoard memberJpa.getId()는 "+ memberJpa.getId());
			model.addAttribute("user_id", memberJpa.getId());
			result ="ro/detailBoardForm";
		}
		
		result = "ro/detailBoardForm";
		return result;
	}
	
	// 게시물 작성 페이지 이동
	@RequestMapping(value = "writeBoardForm")
	public String writeFormBoard(@LoginUser MemberJpa memberJpa, Board board, Model model) {
//		log.info("BoardController writeFormBoard 시작");
		
		String resultForm = "";
		
		if(memberJpa != null) {
//			log.info("BoardController writeFormBoard memberJpa.getId()는 "+ memberJpa.getId());
			model.addAttribute("user_id", memberJpa.getId());
			model.addAttribute("b_common_board", board.getB_common_board());
			resultForm = "ro/writeBoardForm";
			
		}else {
			resultForm = "km/login";
		}
		return resultForm;
	}
	   
	// 게시물 작성
	@ResponseBody
	@PostMapping(value = "writeBoard")
	public String writeBoard(@RequestPart(value = "key") BoardWriteRequestDto requestDto,
							 @RequestPart(value = "file", required = false) List<MultipartFile> files,
							 @LoginUser MemberJpa memberJpa,
							 Model model) throws Exception {
//		log.info("BoardController writeBoard start");
		
		if (memberJpa == null){
			throw new Exception("로그인 해주세요!");
		}
		requestDto.addMemberId(memberJpa.getId());
		
		int insertResult = bs.insertBoard(requestDto,files);
//		log.info("BoardController writeBoard insertResult는 "+ insertResult);
		return ""+insertResult;
	}
	   
	// 게시물 댓글 작성
	@PostMapping(value = "writeBoardRe")
	public String writeReBoard(@LoginUser MemberJpa memberJpa, @ModelAttribute Board board, Model model) {
		log.info("BoardController writeReBoard 시작");
		log.info("BoardController writeReBoard board_id는 "+ board.getBoard_id());
		log.info("BoardController writeReBoard b_common_board()는 "+ board.getB_common_board());
		
		model.addAttribute("board_id", board.getBoard_id());
		model.addAttribute("b_common_board", board.getB_common_board());
		
		int insertResult = bs.insertReBoard(board);
		
		String resultForm = "";
		
		log.info("BoardController writeReBoard insertResult는 "+ insertResult);
		
		if(insertResult > 0) {
			resultForm = "redirect:detailBoard?board_id="+ board.getBoard_id() +"&b_common_board="+ board.getB_common_board();
			
		} else {
			model.addAttribute("msg", "입력 실패 확인해 보세요");
			resultForm = "km/login";
		}
		return resultForm;
	}
	
	// 게시물 대댓글 작성
	@RequestMapping(value = "WriteBoardReLevel")
	public String WriteBoardReLevel(@ModelAttribute Board board, Model model) {
//		log.info("BoardController WriteBoardReLevel start");
		int insertReLevel = bs.insertReLevel(board);
		
//		log.info("BoardController WriteBoardReLevel insertReLevel -> " + insertReLevel);
		model.addAttribute("insertReLevel", insertReLevel);
		model.addAttribute("board_id", board.getBoard_id());
		model.addAttribute("b_common_board", board.getB_common_board());
		return "redirect:detailBoard?board_id="+ board.getBoard_id() +"&b_common_board="+ board.getB_common_board();
	}
	
	// 게시물 수정 페이지 이동
	@RequestMapping(value = "updateBoardForm", method = RequestMethod.POST)
	public String updateFormBoard(Board board, Model model) {
//		log.info("BoardController updateBoardForm 시작");
	  
		// 게시물에 있는 이미지 list 갖고 오기
		List<BodImg> listBoardImgs = bs.listImgBoard(board.getBoard_id());
//		log.info("BoardController updateBoardForm listBoardImgs.size()->"+listBoardImgs.size());
	  
		model.addAttribute("board", board);
		model.addAttribute("listBoardImgs", listBoardImgs);
	  
		return "ro/updateBoardForm";
	}
	   
	// 게시물 수정
	@ResponseBody
	@PostMapping(value = "updateBoard")
	public String updateBoard(
							  // formData에 들어 있는 boardData, imgFiled을 각각 parameter값으로 받기 위해 RequestPart사용
							  @RequestPart(value = "boardData", required = false) Board board,
							  @RequestPart(value = "imgFile", required = false) List<MultipartFile> imgFiles,
							  Model model
	                    	 ) {
//		log.info("BoardController updateBoard 시작");
	  
		int updateResult = bs.updateBoard(board, imgFiles);
//		log.info("BoardController updateBoard updateResult"+ updateResult);
	  
		return ""+ updateResult;
	}
	   
	   
	// 게시물 삭제
	@ResponseBody
	@PostMapping(value = "deleteBoard")
	public String deleteBoard(long board_id, Model model) {
	
//		log.info("BoardController deleteBoard 시작");
//		log.info("BoardController deleteBoard board_id는 "+ board_id);
	      
		int delResult = bs.deleteBoard(board_id);
		String delResultStr = Integer.toString(delResult);
//		log.info("BoardController deleteBoard delResultStr는 "+ delResultStr);
		      
		return delResultStr;
	}
	   
	// 게시물 댓글 삭제
	@ResponseBody
	@PostMapping(value = "deleteBoardRe")
	public String deleteReBoard(Board board, Model model) {   
	
		String successStatus = "1";
//		log.info("BoardController deleteReBoard 시작");
//		log.info("BoardController deleteReBoard board.b_re_step는 "+ board.getB_re_step());
	      
		bs.deleteReBoard(board);
//		log.info("deleteReBoard successStatus ->"+successStatus);
		return successStatus;
	}
	
	// 게시물 댓글 수정
	@ResponseBody
	@PostMapping(value = "UpdateBoardRe")
	public String updateReBoard(Board board, Model model) {
//		log.info("BoardController updateReBoard 시작");
	      
		int updateCount = bs.updateReBoard(board);
//		log.info("BoardController updateReBoard updateCount는 "+ updateCount);
		String updateResult = Integer.toString(updateCount);
	      
		return updateResult;
	}
	
	// 게시물 이미지 삭제
	@ResponseBody
	@RequestMapping("/deleteBoardImg")
	public String deleteImgBoard(BodImg bodImg) {
//		log.info("BoardController deleteImgBoard 시작");
	      
		int deleteCount = bs.deleteImgBoard(bodImg);
//		log.info("BoardController deleteImgBoard deleteResult는 "+ deleteCount);
	      
		String deleteResult = Integer.toString(deleteCount);
	      
		return deleteResult;
	}
	
	// 추천 버튼
	@RequestMapping(value = "/boardlike")
	public String like(Board board, Model model, HttpServletRequest request, HttpServletResponse response) {
		
//		log.info("BoardController boardlike start");
		int updateCount = 0;
		int updateMinus = 0;
		String cookieKey = "boardlike" + board.getBoard_id();
		String result = "forward:/detailBoard";
		
		// 쿠키에서 해당 게시물이 추천된 적이 있는지 검사합니다.
		Cookie[] cookies = request.getCookies();
		boolean boardLikeChk = false;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieKey)) {
					// 이미 추천한 경우, 처리할 내용을 작성합니다.
					// 추천되어있는 쿠키 삭제
					// 해당 쿠키 값을 비우기
					// cookie.setValue("");
					// 쿠키의 유효시간을 0으로 설정 -> 해당 쿠키를 브라우저에게 삭제하도록 지시하는 것
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					
					String message = "추천 취소합니다.";
					model.addAttribute("message", message);
					boardLikeChk = true;
					break;
				}
			}
		}
		
		if (!boardLikeChk) {
			// 추천 1증가
			updateCount = bs.updateCount(board);
//			log.info("BoardController like result ->" + updateCount);
			model.addAttribute("updateCount", updateCount);
			
			// 쿠키에 해당 게시물이 추천된 것을 기록합니다.
			Cookie cookie = new Cookie(cookieKey, "true");
			cookie.setMaxAge(60 * 60 * 24 * 30); // 쿠키 유효기간 30일로 설정
			response.addCookie(cookie);
			
		} else {
			// 추천 1감소
			updateMinus = bs.updateMinus(board);
//			log.info("BoardController like updateMinus ->" + updateMinus);
			model.addAttribute("updateMinus", updateMinus);
		}
	       
//		log.info("BoardController like board_id ->" + board.getBoard_id());
		model.addAttribute("board_id", board.getBoard_id());
		model.addAttribute("b_common_board", board.getB_common_board());
		
		return result;
	}
	 
	   
	// 게시글 신고 버튼
	@RequestMapping(value = "reportMember")
	public String reportMember(Warning warning,Board board,Model model, HttpServletRequest request, HttpServletResponse response) {
		
//		log.info("BoardController reportMember start");
		String resultForm = "";
		int reportMemberCnt = 0;
	      
		// 쿠키 id + 글 id를 하나의 key로 사용 -> 조회수 중복 방지
		String cookieKey = "report" + board.getMember_id() + board.getBoard_id();
		
		Cookie[] cookies = request.getCookies();
		boolean boardReportChk = false;
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieKey)) {
					// 쿠키에 해당 게시물을 이미 조회한 경우, 조회수 증가하지 않음
					String message = "이미 신고한 회원입니다.";
					model.addAttribute("message", message);
					resultForm = "forward:/detailBoard";
					boardReportChk = true;
					
					break;
				}
			}
		}
		
		if (!boardReportChk) {
			// 쿠키에 해당 게시물을 처음 조회한 경우, 조회수 증가
			reportMemberCnt = bs.reportMember(warning);
			Cookie cookie = new Cookie(cookieKey, "true");
			cookie.setMaxAge(60 * 60 * 24 * 30); // 쿠키 유효기간 30일로 설정
			response.addCookie(cookie);
		}
		
//		log.info("BoardController reportMember reportMemberCnt -> " + reportMemberCnt);
//		log.info("BoardController reportMember member_id -> " + warning.getMember_id());   // 회원 ID
//		log.info("BoardController reportMember u_nickname -> " + warning.getU_nickname());   // 신고자 ID
		model.addAttribute("reportMemberCnt", reportMemberCnt);
		model.addAttribute("board_id", board.getBoard_id());
		model.addAttribute("b_common_board", board.getB_common_board());
		resultForm = "forward:/detailBoard";
	      
		return resultForm;
	}
	   
	   
	// 마이페이지 커뮤니티
	@RequestMapping(value = "myPageCommunityList") 
	public String myPageCommunityList(@LoginUser MemberJpa memberJpa, Board board, String currentPage, Model model) {
//		log.info("BoardController myPageCommunityList start");
//		log.info("BoardController myPageCommunityList currentPage는 "+ currentPage);
//		log.info("BoardController myPageCommunityList orderList는" + board.getOrderList());
//		log.info("memberJpa.getId() -> " + memberJpa.getId());
		// Board table 전체 count
		int myPageCommunityListCnt = bs.myPageCommunityListCnt(memberJpa.getId());
//		log.info("BoardController myPageCommunityList boardAllCnt는 "+ myPageCommunityListCnt);
		
		// Paging 작업
		Paging page = new Paging(myPageCommunityListCnt, currentPage);    
		
		// Board에 추가 setting
		board.setStart(page.getStart());
		board.setEnd(page.getEnd());
		board.setMember_id(memberJpa.getId());
		
		List<Board> myPageCommunityList = bs.myPageCommunityList(board);
		
//		log.info("BoardController myPageCommunityList.size()는 "+ myPageCommunityList.size());
		
		model.addAttribute("myPageCommunityListCnt" ,myPageCommunityListCnt);
		model.addAttribute("myPageCommunityList", myPageCommunityList);
		model.addAttribute("page", page);
		model.addAttribute("user_id", memberJpa.getId());
		
		return "ro/myPageCommunityList";
	}
	
	
	// 닉네임 클릭 시 마이페이지 보기
   @GetMapping(value = "/userpage")
   public String userPage(@RequestParam(value = "id") long member_id, Model model) {
//	   log.info("BoardController userpage start");
//	   log.info("BoardController userpage member_id -> " + member_id);
	   UserPageResponseDto userPageResponseDto = mypageService.userPage(member_id);
	   
       model.addAttribute("response", userPageResponseDto);
       model.addAttribute("member_id", member_id);
	   return "km/userpage";
   }
   
   // 마이페이지 여행지 리뷰 페이지
   @RequestMapping(value = "/reviewPageTra")
   public String reviewPageTra(@LoginUser MemberJpa memberJpa, Tra_Rev traRev , String currentPage, Model model) {
//	   log.info("BoardController reviewPageTra start");
//	   log.info("BoardController reviewPageTra currentPage는 "+ currentPage);
//	   log.info("memberJpa.getId() -> " + memberJpa.getId());
	   int totalReviewPageTra = bs.totalReviewPageTra(memberJpa.getId());		
	   //페이징
	   Paging page = new Paging(totalReviewPageTra, currentPage);
	   traRev.setStart(page.getStart());
	   traRev.setEnd(page.getEnd());
	   traRev.setMember_id(memberJpa.getId());
	   //숙소리스트
	   List<Travel> listReviewPageTra = bs.listReviewPageTra(traRev);
//	   log.info("BoardController listReviewPageTra.size()=>"+ listReviewPageTra.size());
	   
	   model.addAttribute("totalReviewPageTra", totalReviewPageTra);
	   model.addAttribute("listReviewPageTra", listReviewPageTra);
	   model.addAttribute("page", page);
	   return "ro/reviewPageTra";
   }
   
   // 마이페이지 숙소 리뷰 페이지
   @RequestMapping(value = "/reviewPageHou")
   public String reviewPageHou(@LoginUser MemberJpa memberJpa, Hou_Rev houRev , String currentPage, Model model ) {
//	   log.info("BoardController reviewPageHou start");
//	   log.info("BoardController reviewPageHou currentPage는 "+ currentPage);
//	   log.info("memberJpa.getId() -> " + memberJpa.getId());
	   int totalReviewPageHou = bs.totalReviewPageHou(memberJpa.getId());		
	   //페이징
	   Paging page = new Paging(totalReviewPageHou, currentPage);
	   houRev.setStart(page.getStart());
	   houRev.setEnd(page.getEnd());
	   houRev.setMember_id(memberJpa.getId());
	   //숙소리스트
	   List<House> listReviewPageHou = bs.listReviewPageHou(houRev);
//	   log.info("BoardController llistReviewPageHou.size()=>"+ listReviewPageHou.size());
	   
	   model.addAttribute("totalReviewPageHou", totalReviewPageHou);
	   model.addAttribute("listReviewPageHou", listReviewPageHou);
	   model.addAttribute("page", page);
	   return "ro/reviewPageHou";
   }
   
   // 마이페이지 맛집 리뷰 페이지
   @RequestMapping(value = "/reviewPageRes")
   public String reviewPageRes(@LoginUser MemberJpa memberJpa, Res_Rev resRev , String currentPage, Model model ) {
//	   log.info("BoardController reviewPageRes start");
//	   log.info("BoardController reviewPageRes currentPage는 "+ currentPage);
//	   log.info("memberJpa.getId() -> " + memberJpa.getId());
	   int totalReviewPageRes = bs.totalReviewPageRes(memberJpa.getId());		
	   //페이징
	   Paging page = new Paging(totalReviewPageRes, currentPage);
	   resRev.setStart(page.getStart());
	   resRev.setEnd(page.getEnd());
	   resRev.setMember_id(memberJpa.getId());
	   //숙소리스트
	   List<Res> listReviewPageRes = bs.listReviewPageRes(resRev);
//	   log.info("BoardController listReviewPageRes.size()=>"+ listReviewPageRes.size());
	   
	   model.addAttribute("totalReviewPageRes", totalReviewPageRes);
	   model.addAttribute("listReviewPageRes", listReviewPageRes);
	   model.addAttribute("page", page);
	   return "ro/reviewPageRes";
	}
    
   	// 태그 업데이트
	@RequestMapping(value = "/mypage/tagUpdate")
	public String mypageTagUpdate(@LoginUser MemberJpa memberJpa, Interest interest, Model model) {
//		log.info("BoardController mypageTagUpdate start");
        model.addAttribute("name", memberJpa.getName());
        interest.setMember_id(memberJpa.getId());
        model.addAttribute("nickName", memberJpa.getNickname());
        List<Interest> mypageTagUpdate = bs.mypageTagUpdate(interest);
        model.addAttribute("mypageTagUpdate", mypageTagUpdate);
		return "km/mypage-tagUpdate";
	}
	
	// 유저 페이지 점수 업데이트
	@RequestMapping(value = "/userScoreUpdate")
	public String userScoreUpdate(long member_id, @LoginUser MemberJpa memberJpa, Score score, Model model, HttpServletRequest request, HttpServletResponse response) {
//		log.info("BoardController userScoreUpdate start");
//		log.info("BoardController userScoreUpdate member_id -> " + member_id);
//		log.info("BoardController userScoreUpdate score.getS_common_spec() -> " + score.getS_common_spec());
		int userScoreUpdate = 0;
		String result = "redirect:/userpage?id="+member_id;
		
		// 비로그인이거나 점수 받는 회원이 동일한 경우
		if(memberJpa == null || memberJpa.getId().equals(member_id)) {
			return result;
		}
		
		String cookieKey = "userScoreUpdate" + member_id + score.getS_common_spec() + memberJpa.getId();
		
		Cookie[] cookies = request.getCookies();
		boolean userScoreUpdateChk = false;
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieKey)) {
					
					String message = "이미 추천한 유저점수입니다.";
					model.addAttribute("message", message);
					userScoreUpdateChk = true;
					break;
				}
			}
		}
		if (!userScoreUpdateChk) {
			
			score.setMember_id(member_id);
			userScoreUpdate = bs.userScoreUpdate(score);
			
//			log.info("BoardController userScoreUpdate result ->" + userScoreUpdate);
			model.addAttribute("userScoreUpdate", userScoreUpdate);
			Cookie cookie = new Cookie(cookieKey, "true");
			cookie.setMaxAge(60 * 60 * 24 * 30); // 쿠키 유효기간 30일로 설정
			response.addCookie(cookie);
		} 
		return result;
	}
	
	
}
