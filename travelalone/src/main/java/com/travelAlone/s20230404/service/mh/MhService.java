package com.travelAlone.s20230404.service.mh;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.mh.Inq_Img;
import com.travelAlone.s20230404.model.mh.Inquire;
import com.travelAlone.s20230404.model.mh.Not_Img;
import com.travelAlone.s20230404.model.mh.Notice;

public interface MhService {
//===============================공지사항=======================================
	int 					totalNotice();
	List<Notice> 			listNotice(Notice notice);
	int 					seqNot(Notice notice);
	int 					insertNotice(Notice notice);
	int 					insertNotImg(Not_Img not_Img);
	int 					updateNotice(Notice notice);
	Notice 					detailNotice(int g_notice_id);
	List<Not_Img> 			listNot_Img(Not_Img not_Img);
	int 					deleteNotice(int g_notice_id);
	int 					deleteNotImg(int g_notice_id);	
	int 					deleteNotOneImg(int g_notice_id, int img_id);
	List<Notice> 			listSearchNotice(Notice notice);
	int 					conditionNoticeCount(Notice notice);
					
	
	
//===============================자주묻는질문=======================================
	List<Notice> 			listFaq(Notice notice);
	
	
//===============================문의하기=======================================
	int 					totalInquire();
	List<Inquire> 			listInquire(Inquire inquire);
	int 					seqInq(Inquire inquire);
	int 					insertInquire(Inquire inquire);
	int 					insertInqImg(Inq_Img inq_Img);
	Inquire 				detailInquire(int gid);
	List<Inq_Img> 			listInq_Img(Inq_Img inq_Img);
	int 					updateInquire(Inquire inquire);
	int 					deleteInquire(int g_writing_id);	
	int 					deleteInqImg(int g_writing_id);
	int 					deleteInqOneImg(int g_writing_id, int img_id);
	List<Inquire> 			listSearchInquire(Inquire inquire);
	int 					conditionInquireCount(Inquire inquire);
	
	//답변하기
	int 					replyInquire(Inquire inquire);
	
	//공통코드 호출 
	List<CommonCode> 		getCommonCode();
	int 					conditionOptionCount(String code);		
	List<Inquire> 			listFilterOptionInquire(Inquire inquire);
	
	

}
