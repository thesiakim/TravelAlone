package com.travelAlone.s20230404.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.Hou_Rev;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Interest;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Rev;
import com.travelAlone.s20230404.model.Tra_Rev;
import com.travelAlone.s20230404.model.Travel;
import com.travelAlone.s20230404.model.Warning;
import com.travelAlone.s20230404.model.dto.km.UserPageResponseDto;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.jh.JhService;
import com.travelAlone.s20230404.service.km.MypageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class JhController {

   
   private final JhService      js;
   private final MypageService 	mypageService;
   
// 추천 버튼
   @RequestMapping(value = "/boardlike")
   public String like(Board board, Model model, HttpServletRequest request, HttpServletResponse response) {

       log.info("jhController boardlike start");
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
           updateCount = js.updateCount(board);
           log.info("jhController like result ->" + updateCount);
           model.addAttribute("updateCount", updateCount);

           // 쿠키에 해당 게시물이 추천된 것을 기록합니다.
           Cookie cookie = new Cookie(cookieKey, "true");
           cookie.setMaxAge(60 * 60 * 24 * 30); // 쿠키 유효기간 30일로 설정
           response.addCookie(cookie);
           
       } else {
          // 추천 1감소
          updateMinus = js.updateMinus(board);
          log.info("jhController like updateMinus ->" + updateMinus);
           model.addAttribute("updateMinus", updateMinus);
       }
       
       log.info("jhController like board_id ->" + board.getBoard_id());
       model.addAttribute("board_id", board.getBoard_id());
       model.addAttribute("b_common_board", board.getB_common_board());

       return result;
   }
 
   
   // 게시글 신고 버튼
   @RequestMapping(value = "reportMember")
   public String reportMember(Warning warning,Board board,Model model, HttpServletRequest request, HttpServletResponse response) {
      
      log.info("jhController reportMember start");
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
          reportMemberCnt = js.reportMember(warning);
          Cookie cookie = new Cookie(cookieKey, "true");
          cookie.setMaxAge(60 * 60 * 24 * 30); // 쿠키 유효기간 30일로 설정
          response.addCookie(cookie);
      }
      
      log.info("jhController reportMember reportMemberCnt -> " + reportMemberCnt);
      log.info("jhController reportMember member_id -> " + warning.getMember_id());   // 회원 ID
      log.info("jhController reportMember u_nickname -> " + warning.getU_nickname());   // 신고자 ID
      model.addAttribute("reportMemberCnt", reportMemberCnt);
      model.addAttribute("board_id", board.getBoard_id());
      model.addAttribute("b_common_board", board.getB_common_board());
      resultForm = "forward:/detailBoard";
      
       return resultForm;
   }
   
   // 대댓글 작성
   @RequestMapping(value = "WriteBoardReLevel")
   public String WriteBoardReLevel(Board board, Model model) {
      log.info("jhController WriteBoardReLevel start");
      int insertReLevel = js.insertReLevel(board);
      
      log.info("jhController WriteBoardReLevel insertReLevel -> " + insertReLevel);
       model.addAttribute("insertReLevel", insertReLevel);
       model.addAttribute("board_id", board.getBoard_id());
       model.addAttribute("b_common_board", board.getB_common_board());
      return "forward:/detailBoard";
   }
   
   // 마이페이지 커뮤니티
   @RequestMapping(value = "myPageCommunityList") 
   public String myPageCommunityList(@LoginUser MemberJpa memberJpa, Board board, String currentPage, Model model) {
       log.info("jhController myPageCommunityList start");
       log.info("jhController myPageCommunityList currentPage는 "+ currentPage);
       log.info("jhController myPageCommunityList orderList는" + board.getOrderList());
       log.info("memberJpa.getId() -> " + memberJpa.getId());
       // Board table 전체 count
       int myPageCommunityListCnt = js.myPageCommunityListCnt(memberJpa.getId());
       log.info("jhController myPageCommunityList boardAllCnt는 "+ myPageCommunityListCnt);
      
       // Paging 작업
       Paging page = new Paging(myPageCommunityListCnt, currentPage);    
      
       // Board에 추가 setting
       board.setStart(page.getStart());
       board.setEnd(page.getEnd());
       board.setMember_id(memberJpa.getId());
       
       List<Board> myPageCommunityList = js.myPageCommunityList(board);
      
       log.info("jhController myPageCommunityList.size()는 "+ myPageCommunityList.size());
      
       model.addAttribute("myPageCommunityListCnt" ,myPageCommunityListCnt);
       model.addAttribute("myPageCommunityList", myPageCommunityList);
       model.addAttribute("page", page);
       model.addAttribute("user_id", memberJpa.getId());
       return "ro/myPageCommunityList";
   }
   
   // 닉네임 클릭 시 마이페이지 보기
   @GetMapping(value = "/userpage")
   public String userPage(@RequestParam(value = "id") long member_id, Model model) {
	   log.info("jhController userpage start");
	   log.info("jhController userpage member_id -> " + member_id);
	   UserPageResponseDto userPageResponseDto = mypageService.userPage(member_id);
	   
       model.addAttribute("response", userPageResponseDto);
	   return "km/userpage";
   }
   
   // 마이페이지 여행지 리뷰 페이지
   @RequestMapping(value = "/reviewPageTra")
   public String reviewPageTra(@LoginUser MemberJpa memberJpa, Tra_Rev traRev , String currentPage, Model model) {
	   log.info("jhController reviewPageTra start");
	   log.info("jhController reviewPageTra currentPage는 "+ currentPage);
	   log.info("memberJpa.getId() -> " + memberJpa.getId());
	   int totalReviewPageTra = js.totalReviewPageTra(memberJpa.getId());		
	   //페이징
	   Paging page = new Paging(totalReviewPageTra, currentPage);
	   traRev.setStart(page.getStart());
	   traRev.setEnd(page.getEnd());
	   traRev.setMember_id(memberJpa.getId());
	   //숙소리스트
	   List<Travel> listReviewPageTra = js.listReviewPageTra(traRev);
	   log.info("jhController listReviewPageTra.size()=>"+ listReviewPageTra.size());
	   
	   model.addAttribute("totalReviewPageTra", totalReviewPageTra);
	   model.addAttribute("listReviewPageTra", listReviewPageTra);
	   model.addAttribute("page", page);
	   return "ro/reviewPageTra";
   }
   
   // 마이페이지 숙소 리뷰 페이지
   @RequestMapping(value = "/reviewPageHou")
   public String reviewPageHou(@LoginUser MemberJpa memberJpa, Hou_Rev houRev , String currentPage, Model model ) {
	   log.info("jhController reviewPageHou start");
	   log.info("jhController reviewPageHou currentPage는 "+ currentPage);
	   log.info("memberJpa.getId() -> " + memberJpa.getId());
	   int totalReviewPageHou = js.totalReviewPageHou(memberJpa.getId());		
	   //페이징
	   Paging page = new Paging(totalReviewPageHou, currentPage);
	   houRev.setStart(page.getStart());
	   houRev.setEnd(page.getEnd());
	   houRev.setMember_id(memberJpa.getId());
	   //숙소리스트
	   List<House> listReviewPageHou = js.listReviewPageHou(houRev);
	   log.info("jhController llistReviewPageHou.size()=>"+ listReviewPageHou.size());
	   
	   model.addAttribute("totalReviewPageHou", totalReviewPageHou);
	   model.addAttribute("listReviewPageHou", listReviewPageHou);
	   model.addAttribute("page", page);
	   return "ro/reviewPageHou";
   }
   
   // 마이페이지 맛집 리뷰 페이지
   @RequestMapping(value = "/reviewPageRes")
   public String reviewPageRes(@LoginUser MemberJpa memberJpa, Res_Rev resRev , String currentPage, Model model ) {
	   log.info("jhController reviewPageRes start");
	   log.info("jhController reviewPageRes currentPage는 "+ currentPage);
	   log.info("memberJpa.getId() -> " + memberJpa.getId());
	   int totalReviewPageRes = js.totalReviewPageRes(memberJpa.getId());		
	   //페이징
	   Paging page = new Paging(totalReviewPageRes, currentPage);
	   resRev.setStart(page.getStart());
	   resRev.setEnd(page.getEnd());
	   resRev.setMember_id(memberJpa.getId());
	   //숙소리스트
	   List<Res> listReviewPageRes = js.listReviewPageRes(resRev);
	   log.info("jhController listReviewPageRes.size()=>"+ listReviewPageRes.size());
	   
	   model.addAttribute("totalReviewPageRes", totalReviewPageRes);
	   model.addAttribute("listReviewPageRes", listReviewPageRes);
	   model.addAttribute("page", page);
	   return "ro/reviewPageRes";
	}
    

   
}