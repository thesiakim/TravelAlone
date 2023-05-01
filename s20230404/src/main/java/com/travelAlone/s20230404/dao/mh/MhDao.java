package com.travelAlone.s20230404.dao.mh;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.mh.Inquire;
import com.travelAlone.s20230404.model.mh.Notice;


public interface MhDao {

	int 				totalNotice();
	List<Notice> 		selectNoticeList(Notice notice);
	Notice 				detailNotice(int g_notice_id);
	int 				insertNotice(Notice notice);
	int 				updateNotice(Notice notice);
	int 				deleteNotice(int g_notice_id);	
	List<Notice> 		noticeSearchList(Notice notice);
	int 				condNotice(Notice notice);
	
	//자주묻는 질문
	List<Notice> 		selectFaqList(Notice notice);
	
	
	//문의하기
	int 				totalInquire();
	List<Inquire> 		selectInquireList(Inquire inquire);
	Inquire 			detailInquire(int g_writing_id);
	int 				insertInquire(Inquire inquire);
	int 				updateInquire(Inquire inquire);
	int 				deleteInquire(int g_writing_id);
	List<Inquire> 		inquireSearchList(Inquire inquire);
	int 				condInquireCnt(Inquire inquire);
	
	//필터링
	List<CommonCode> getCommonCode();
	List<Inquire> mhOptionInquireList(Inquire inquire);
	int condOptionInqCnt(String code); 
			
	
	//답변
	int replyCount(Inquire inquire);
			
	
	
	
	
	
}
