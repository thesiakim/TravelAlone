package com.travelAlone.s20230404.service.mh;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.mh.Inquire;
import com.travelAlone.s20230404.model.mh.Notice;

public interface mhService {
	//공지사항
	int 					totalNotice();
	List<Notice> 			listNotice(Notice notice);
	Notice 					detailNotice(int g_notice_id);
	int 					insertNotice(Notice notice);
	int 					updateNotice(Notice notice);
	int 					deleteNotice(int g_notice_id);
	List<Notice> 			listSearchNotice(Notice notice);
	int 					conditionNoticeCount(Notice notice);
	
	//자주묻는질문
	List<Notice> 			listFaq(Notice notice);
	
	
	//문의하기
	int 					totalInquire();
	List<Inquire> 			listInquire(Inquire inquire);
	Inquire 				detailInquire(int gid);
	int 					insertInquire(Inquire inquire);
	int 					updateInquire(Inquire inquire);
	int 					deleteInquire(int g_writing_id);	
	List<Inquire> 			listSearchInquire(Inquire inquire);
	int 					conditionInquireCount(Inquire inquire);
	
	
	
	//답변하기
	int replyInquire(Inquire inquire);
	
	
	
	
	
	//공통코드 호출 
	List<CommonCode> 		getCommonCode();
	//옵션별 count 갯수
	int conditionOptionCount(String code);		
	//옵션별 게시글 갯수
	List<Inquire> 			listFilterOptionInquire(Inquire inquire);
	

	
	
	
}
