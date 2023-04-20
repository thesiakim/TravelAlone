package com.travelAlone.s20230404.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travelAlone.s20230404.model.mh.Inquire;
import com.travelAlone.s20230404.model.mh.Notice;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.mh.mhService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
public class mhController {
	
	private final mhService mh;
	
	
	
	// ===================공지사항===================
	//공지사항 목록보기
	@RequestMapping(value = "notice")
	public String notice(Notice notice, String currentPage, Model model) {
		System.out.println("mhController Start notice..." );
		int totalNotice = mh.totalNotice();		
		
		//페이징
		Paging page = new Paging(totalNotice, currentPage);
		notice.setStart(page.getStart());
		notice.setEnd(page.getEnd());
		
		List<Notice> listNotice = mh.listNotice(notice);
		System.out.println("mhController list listNotice.size()=>" + listNotice.size());

		
		System.out.println("currentPage는 ?"+ currentPage);
		model.addAttribute("totalNotice", totalNotice);
		model.addAttribute("noticeList", listNotice);
		model.addAttribute("page", page);
		return "mh/notice";
	}
	
	//공지사항   게시글조회
	@GetMapping(value = "noticeDetail")
	public String noticeDetail(int gid , Model model ) {
		System.out.println("mhController Start noticeDetail..." );
		System.out.println("mhController noticeDetail g_notice_id->"+ gid );
		
		Notice notice = mh.detailNotice(gid);
		model.addAttribute("notice", notice);
		
		return "mh/noticeDetail";
	}
	/*
	//공지사항   게시글조회 (rest api 방식으로 작성)
	@GetMapping(value = "noticeDetail/{gid}")
	public String noticeDetail(@PathVariable int gid , Model model ) {
		System.out.println("mhController Start noticeDetail..." );
		System.out.println("mhController noticeDetail g_notice_id->"+ gid );
		
		Notice notice = mh.detailNotice(gid);
		model.addAttribute("notice", notice);
		
		return "noticeDetail";
	}
	*/

	//공지사항 글작성 페이지 이동
	@GetMapping(value = "noticeWriteForm")
	public String noticeWriteForm(Notice notice, Model model) {
		System.out.println("mhController  noticeWriteForm Start..." );
		return "mh/noticeWriteForm";
	}
		
	//공지사항 글작성
	@PostMapping(value = "noticeWriteForm")
	public String noticeWrite(Notice notice, Model model) {
		System.out.println("mhController  noticeWrite Start..." );
		
		int insertResult = mh.insertNotice(notice);
		System.out.println("mhController noticeWrite insertResult->"+insertResult );
		
		if (insertResult > 0) {
			return "redirect:notice";}
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:mh/noticeWriteForm";
		}
		
	}
	 
	
	//공지사항 글수정 페이지이동
	@GetMapping(value = "noticeUpdateForm")
	public String noticeUpdateForm(int g_notice_id, Model model) {
		System.out.println("mhController Start updateForm..." );
		Notice notice = mh.detailNotice(g_notice_id);
		
		System.out.println("mhController updateFormEmp notice->"+notice);
		
		
		model.addAttribute("notice", notice);	
		return "mh/noticeUpdateForm";
	}
	
	//공지사항 글수정 처리
	@PostMapping(value = "updateNotice")
	public String updateNotice(Notice notice , Model model) {
		System.out.println("mhController Start update..." );
		
		int updateCount = mh.updateNotice(notice);
		System.out.println("mhController es.updateNotice updateCount-->"+ updateCount);
		model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
		model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
		
		return "forward:notice";
	}
	
	//공지사항 글 삭제
	@RequestMapping(value = "deleteNotice")
	public String deleteNotice(int g_notice_id, Model model) {
		System.out.println("mhController Start delete... n_id :" +g_notice_id);
		int result = mh.deleteNotice(g_notice_id);
		
		return "redirect:notice";
	}
	
	
	
	
	//공지사항 검색
	@RequestMapping(value = "noticeSearch")
	public String noticeSearch(Notice notice, String currentPage, Model model) {
		log.info("mhController noticeSearch Start ..." );
		// Notice 전체 Count  
		int totalNotice = mh.conditionNoticeCount(notice);
		log.info("mhController noticeSearch totalNotice =>" + totalNotice);
		// Paging 작업
		Paging page = new Paging(totalNotice, currentPage);
		
		// Parameter emp --> Page만 추가 Setting
		notice.setStart(page.getStart());
		notice.setEnd(page.getEnd());
		List<Notice> listSearchNotice = mh.listSearchNotice(notice);
		log.info("mhController noticeSearch listSearchNotice.size()=>" + 
				listSearchNotice.size());
		model.addAttribute("totalNotice", totalNotice);
		model.addAttribute("noticeList", listSearchNotice);
		model.addAttribute("page", page);
		model.addAttribute("search", notice.getKeyword());
		return "mh/notice";
		
	}
	
	
// ===================자주믇는 질문=====================================================================================================
	//자주묻는질문 목록보기
	@RequestMapping(value = "faq")
	public String Faq(Notice notice, String currentPage, Model model) {
		System.out.println("mhController Start faq..." );
		int totalNotice = mh.totalNotice();		
		
		//페이징
		Paging page = new Paging(totalNotice, currentPage);
		notice.setStart(page.getStart());
		notice.setEnd(page.getEnd());
		
		List<Notice> listFaq = mh.listFaq(notice);
		System.out.println("mhController list listFaq.size()=>" + listFaq.size());

		
		System.out.println("currentPage는 ?"+ currentPage);
		//model.addAttribute("totalNotice", totalNotice);
		model.addAttribute("noticeList", listFaq);
		model.addAttribute("page", page);
		return "mh/faq";
	}
		
	
	
	//==========================문의게시판=========================================================================================
		//문의게시판 글목록
		@RequestMapping(value = "inquire")
		public String inquire( Inquire inquire, String currentPage, Model model) {
			log.info("mhController Start inquire...");
			int totalInquire = mh.totalInquire();
			
			//페이징
			Paging page = new Paging(totalInquire, currentPage);
			inquire.setStart(page.getStart());
			inquire.setEnd(page.getEnd());
			
			
			//요구사항 리스트
			List<Inquire> listInquire = mh.listInquire(inquire);
			log.info("mhController  listInquire.size()=>" + listInquire.size());
			
			model.addAttribute("totalInquire", totalInquire);
			model.addAttribute("inquireList", listInquire);
			model.addAttribute("page", page);
			
			log.info("data checking : 1 - {}, 2 - {}, 3 - {}",listInquire.get(0).getG_reply_yn(),listInquire.get(1).getG_reply_yn(),listInquire.get(2).getG_reply_yn());
			
			return "mh/inquire";
		}
	
		//문의게시판  게시글조회
		@GetMapping(value = "inquireDetail")
		public String inquireDetail(int gid , Model model ) {
			log.info("mhController Start inquireDetail..." );
			log.info("mhController Start inquireDetail g_notice_id"+ gid  );
			Inquire inquire = mh.detailInquire(gid);
			model.addAttribute("inquire", inquire);			
	
			return "mh/inquireDetail";
		}
		
		
		
		//문의게시판 글작성 페이지이동
		@GetMapping(value = "inquireWriteForm")
		public String inquireWriteForm(Inquire inquire,Model model) {
			log.info("mhController inquireWriteForm Start... ");
			return "mh/inquireWriteForm";
		}
		
	
		
		//문의게시판 글작성
		@PostMapping(value = "inquireWriteForm")
		public String  inquireWrite(Inquire inquire, Model model) {
			log.info("mhController  inquireWrite Start...");
			int insertResult = mh.insertInquire(inquire);
			log.info("mhController  inquireWrite insertResult0> " + insertResult);
			
			if (insertResult>0) {
				return "redirect:inquire";
			}
			else {
				model.addAttribute("msg","입력 실패 확인해 보세요");
				return "forward:mh/inquireWriteForm";
			}
		}
		
		//문의게시판 글수정 페이지이동
		@GetMapping(value = "inquireUpdateForm")
		public String inquireUpdateForm(int g_writing_id, Model model) {
			log.info("mhController Start updateForm...");
			Inquire inquire = mh.detailInquire(g_writing_id);
			log.info("mhController updateFormInquire inquire->"+inquire);			
			
			model.addAttribute("inquire", inquire);	
			return "mh/inquireUpdateForm";
		}	

		//문의게시판 글수정 
		@PostMapping(value = "updateInquire")
		public String updateInquire(Inquire inquire, Model model) {
			log.info("mhController Start update...");
			int updateCount = mh.updateInquire(inquire);
			log.info("mhController es.updateNotice updateCount-->"+ updateCount);
			model.addAttribute("uptCnt", updateCount);
			model.addAttribute("kk3"," Message Test");
			return "forward:inquire";
		}
		
		//문의게시판 글 삭제
		@RequestMapping(value = "deleteInquire")
		public String deleteInquire(int g_writing_id, Model model) {
			log.info("mhController Start delete..." + g_writing_id);
			int result = mh.deleteInquire(g_writing_id);
			
			return "redirect:inquire";
		}
		//문의게시판 검색
		@RequestMapping(value = "inquireSearch")
		public String inquireSearch(Inquire inquire, String currentPage, Model model) {
			log.info("mhController inquireSearch Start ..." );
			
			int totalInquire = mh.conditionInquireCount(inquire);
			log.info("mhController inquireSearch totalInquire =>" + totalInquire);
			// Paging 작업
			Paging page = new Paging(totalInquire, currentPage);
			
			inquire.setStart(page.getStart());
			inquire.setEnd(page.getEnd());
			List<Inquire> listSearchInquire = mh.listSearchInquire(inquire);
			log.info("mhController InquireSearch listSearchInquire.size()=>" + 
					listSearchInquire.size());
			
			model.addAttribute("totalInquire", totalInquire);
			model.addAttribute("inquireList", listSearchInquire);
			model.addAttribute("page", page);
			model.addAttribute("search", inquire.getKeyword());
			
			return "mh/inquire";
		}
		
	//문의게시판 분류 여행지추가 문의
		@RequestMapping(value = "inquireTraFilter")
		public String inquireTraFilter(Inquire inquire, String currentPage, Model model) {
			log.info("mhController inquireTraFilter Start ..." );
			
			int totalInquire = mh.conditionTravelCount(inquire);
			log.info("mhController inquireTraFilter totalInquire =>" + totalInquire);
			
			// Paging 작업
			Paging page = new Paging(totalInquire, currentPage);
			inquire.setStart(page.getStart());
			inquire.setEnd(page.getEnd());
			
			List<Inquire> listFilterTraInquire = mh.listFilterTraInquire(inquire);
			log.info("mhController TraInquireSearch listFilterInquire.size()=>" + listFilterTraInquire.size());
			model.addAttribute("totalInquire", totalInquire);
			model.addAttribute("inquireList", listFilterTraInquire);
			model.addAttribute("page", page);
			model.addAttribute("search", inquire.getKeyword());
								
			return "mh/inquire";
		}
		
		//문의게시판 분류 숙소추가 문의
		@RequestMapping(value = "inquireHouFilter")
		public String inquireHouFilter(Inquire inquire, String currentPage, Model model) {
			log.info("mhController inquireHouFilter Start ..." );
			
			int totalInquire = mh.conditionHouseCount(inquire);
			log.info("mhController inquireHouFilter totalInquire =>" + totalInquire);
			
			// Paging 작업
			Paging page = new Paging(totalInquire, currentPage);
			inquire.setStart(page.getStart());
			inquire.setEnd(page.getEnd());
			
			List<Inquire> listFilterHouInquire = mh.listFilterHouInquire(inquire);
			log.info("mhController TraInquireSearch listFilterTraInquire.size()=>" + listFilterHouInquire.size());
			model.addAttribute("totalInquire", totalInquire);
			model.addAttribute("inquireList", listFilterHouInquire);
			model.addAttribute("page", page);
			model.addAttribute("search", inquire.getKeyword());
			
			return "mh/inquire";
		}
		
		//문의게시판 분류 맛집추가 문의
		@RequestMapping(value = "inquireResFilter")
		public String inquireResFilter(Inquire inquire, String currentPage, Model model) {
			log.info("mhController inquireResFilter Start ..." );
			
			int totalInquire = mh.conditionResCount(inquire);
			log.info("mhController inquireResFilter totalInquire =>" + totalInquire);
			
			// Paging 작업
			Paging page = new Paging(totalInquire, currentPage);
			inquire.setStart(page.getStart());
			inquire.setEnd(page.getEnd());
			
			List<Inquire> listFilterResInquire = mh.listFilterResInquire(inquire);
			log.info("mhController TraInquireSearch listFilterResInquire.size()=>" + listFilterResInquire.size());
			model.addAttribute("totalInquire", totalInquire);
			model.addAttribute("inquireList", listFilterResInquire);
			model.addAttribute("page", page);
			model.addAttribute("search", inquire.getKeyword());
			
			return "mh/inquire";
		}
		
		//문의게시판 분류 기타 문의
		@RequestMapping(value = "inquireEtcFilter")
		public String inquireEtcFilter(Inquire inquire, String currentPage, Model model) {
			log.info("mhController inquireEtcFilter Start ..." );
			
			int totalInquire = mh.conditionEtcCount(inquire);
			log.info("mhController inquireEtcFilter totalInquire =>" + totalInquire);
			
			// Paging 작업
			Paging page = new Paging(totalInquire, currentPage);
			inquire.setStart(page.getStart());
			inquire.setEnd(page.getEnd());
			
			List<Inquire> listFilterEtcInquire = mh.listFilterEtcInquire(inquire);
			log.info("mhController TraInquireSearch listFilterEtcInquire.size()=>" + listFilterEtcInquire.size());
			model.addAttribute("totalInquire", totalInquire);
			model.addAttribute("inquireList", listFilterEtcInquire);
			model.addAttribute("page", page);
			model.addAttribute("search", inquire.getKeyword());
			
			return "mh/inquire";
		}
		

		
		//문의게시판 답변 페이지이동
		@GetMapping(value = "inquireReplyForm")
		public String inquireReplyForm(int g_writing_id, Model model) {
			log.info("mhController inquireReplyForm Start... ");
			Inquire inquire = mh.detailInquire(g_writing_id);
			log.info("mhController inquireReplyForm inquire->"+inquire);	
			model.addAttribute("inquire", inquire);	
			return "mh/inquireReplyForm";
			
		}
		
		//문의게시판 글답변(업데이트) 
		@PostMapping(value = "replyInquire")
		public String replyInquire(Inquire inquire, Model model) {
			log.info("mhController Start reply...");
			System.out.println("mhController reply" + inquire.getG_writing_id());
			System.out.println("mhController reply" + inquire.getG_content());
			System.out.println("mhController reply" + inquire.getG_reply_content());
			
			int replyCount = mh.replyInquire(inquire);
			log.info("mhController es.updateNotice updateCount-->"+ replyCount);
			model.addAttribute("repCnt", replyCount);
			model.addAttribute("kk3"," Message Test");
			return "forward:inquire";
		}
		


		
		
		
	
}
