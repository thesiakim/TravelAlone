package com.travelAlone.s20230404.service.mh;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.mh.MhDao;
import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.mh.Inq_Img;
import com.travelAlone.s20230404.model.mh.Inquire;
import com.travelAlone.s20230404.model.mh.Not_Img;
import com.travelAlone.s20230404.model.mh.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MhServiceImpl implements MhService {
	private final MhDao		mh;

//=============================공지사항========================================================

@Override
public int totalNotice() {
	//log.info("NoticeServiceImpl Start totalNotice" );
	int totNoticeCnt = mh.totalNotice();
	//log.info("NoticeServiceImpl totalNotice totalNoticeCnt->" + totNoticeCnt);
	return totNoticeCnt;
}

@Override
public List<Notice> listNotice(Notice notice) {
	//log.debug("NoticeServiceImpl Start listNotice...");
	List<Notice> noticeList = mh.selectNoticeList(notice);
	//log.debug("NoticeServiceImpl End listNotice...");
	
	
	return noticeList;
}

@Override
public int seqNot(Notice notice) {
	int result = 0;
	//log.info("NoticeServiceImpl seqNot Start" );
	result = mh.seqNotice(notice);
	return result;
}
@Override
public int insertNotice(Notice notice) {
	int result = 0;
	//log.info("NoticeServiceImpl insert Start..." );
	result = mh.insertNotice(notice);	
	return result;
}

@Override
public int insertNotImg(Not_Img not_Img) {
	int result = 0;
	//log.info("NoticeServiceImpl insert Start" );
	result = mh.insertNotImg(not_Img);
	return result;
}

@Override
public int updateNotice(Notice notice) {
	//log.info("NoticeServiceImpl update ...");
	int updateCount = 0;
	updateCount = mh.updateNotice(notice);		
	return updateCount;
}


@Override
public Notice detailNotice(int g_notice_id) {
	//log.info("NoticeServiceImpl detail ...");
	Notice notice = null; 
	notice = mh.detailNotice(g_notice_id);
	return notice;						
}

//공지사항 사진리스트
@Override
public List<Not_Img> listNot_Img(Not_Img not_Img) {
	//log.info("NoticeServiceImpl Start listInq_Img");
	List<Not_Img> notImgList = mh.selectNotImgList(not_Img);
	return notImgList;
}



@Override
public int deleteNotice(int g_notice_id) {
	int result = 0;
	//log.info("NoticeServiceImpl delete Start..." );
	result = mh.deleteNotice(g_notice_id);		
	return result;
}

//공지사항 사진 전체삭제
@Override
public int deleteNotImg(int g_notice_id) {
	int result = 0;
	//log.info("NoticeServiceImpl deleteNotImg Start..." );		
	result = mh.deleteNotImg(g_notice_id);		
	return result;
}

//공지사항 사진 하나만 삭제
@Override
public int deleteNotOneImg(int g_notice_id, int img_id) {
	int result = 0;
	//log.info("NoticeServiceImpl deleteNotOneImg Start");
	result = mh.deleteNotOneImg(g_notice_id,img_id);
	return result;
}



@Override
public List<Notice> listSearchNotice(Notice notice) {
	List<Notice> noticeSearchList = null;
	//log.info("NoticeServiceImpl listSearchNotice Start...");
	noticeSearchList = mh.noticeSearchList(notice);
	//log.info("NoticeServiceImpl listSearchNotice noticeSearchList.size()"+ noticeSearchList.size());
				
	return noticeSearchList;
}
	
	
	
@Override
public int conditionNoticeCount(Notice notice) {
	//log.info("NoticeServiceImpl  conditionNoticeCount Start" );
	int condNoticeCnt = mh.condNotice(notice);
	//log.info("NoticeServiceImpl conditionEmpCount condNoticeCnt->" + condNoticeCnt);
	return condNoticeCnt;
}

	
//=============================자주묻는질문게시판========================================================
	
@Override
public List<Notice> listFaq(Notice notice) {
	//log.debug("FaqServiceImpl Start listFaq...");
	List<Notice> faqList = mh.selectFaqList(notice);
	//log.debug("FaqServiceImpl End listFaq...");
	return faqList;
}

	
	
	
//=============================문의게시판========================================================
@Override
public int totalInquire() {
	//log.info("InquireServiceImpl Start totalInquire...");
	int totalInquireCnt = mh.totalInquire();
	//log.info("InquireServiceImpl Start totalInquireCnt..."+ totalInquireCnt);
	return totalInquireCnt;
}

@Override
public List<Inquire> listInquire(Inquire inquire) {
	//log.info("InquireServiceImpl Start listInquire...");
	List<Inquire> inquireList = mh.selectInquireList(inquire);		
	return inquireList;
}

//문의글 시퀀스 가져오기
@Override
public int seqInq(Inquire inquire) {
	int result = 0;
	//log.info("InquireServiceImpl seqInq Start" );
	result = mh.seqInquire(inquire);
	return result;
}

@Override
public int insertInquire(Inquire inquire) {
	int result = 0;
	//log.info("InquireServiceImpl insert Start...");
	result = mh.insertInquire(inquire);		
	return result;
}

@Override
public int insertInqImg(Inq_Img inq_Img) {
	int result = 0;
	//log.info("InquireServiceImpl insert Start" );
	result = mh.insertInqImg(inq_Img);
	return result;
}


@Override
public Inquire detailInquire(int g_writing_id) {
	//log.info(("InquireServiceImpl detail ..."));
	Inquire inquire = null;
	inquire = mh.detailInquire(g_writing_id);	
	return inquire;
}

@Override
public List<Inq_Img> listInq_Img(Inq_Img inq_Img) {
	//log.info("InquireServiceImpl Start listInq_Img");
	List<Inq_Img> inqImgList = mh.selectInqImgList(inq_Img);
	return inqImgList;
}


@Override
public int updateInquire(Inquire inquire) {
	//log.info("mhServiceImpl update ...");
	int updateCount = 0;
	updateCount = mh.updateInquire(inquire);		
	return updateCount;
}


@Override
public int deleteInquire(int g_writing_id) {
	int result = 0;
	//log.info("mhServiceImpl delete Start...");
	result = mh.deleteInquire(g_writing_id);		
	return result;
}

//문의사항  사진전체삭제
@Override
public int deleteInqImg(int g_writing_id) {
	int result = 0;
	//log.info("InquireServiceImpl deleteInqImg Start");
	result = mh.deleteInqImg(g_writing_id);
	return result;
}

//문의사항 사진 하나만 삭제
@Override
public int deleteInqOneImg(int g_writing_id, int img_id) {
	int result = 0;
	//log.info("InquireServiceImpl deleteInqOneImg Start");
	result = mh.deleteInqOneImg(g_writing_id,img_id);
	return result;
}


@Override
public List<Inquire> listSearchInquire(Inquire inquire) {
	List<Inquire> inquireSearchList = null;
	//log.info("InquireServiceImpl listSearchInquire Start...");
	inquireSearchList = mh.inquireSearchList(inquire);
	//log.info("InquireServiceImpl listSearchInquire inquireSearchList.size()"+ inquireSearchList.size());
			
	return inquireSearchList;
}
	
	
	
@Override
public int conditionInquireCount(Inquire inquire) {
	//log.info("InquireServiceImpl  conditionInquireCount Start" );
	int conditionInquireCnt = mh.condInquireCnt(inquire);
	//log.info("InquireServiceImpl  conditionInquireCount conditionInquireCnt" + conditionInquireCnt);		
	return conditionInquireCnt;
}

//답변
@Override
public int replyInquire(Inquire inquire) {
	//log.info("InquireServiceImpl reply ...");
	int replyCount = 0;
	replyCount = mh.replyCount(inquire);
	return replyCount;
}


//공통코드 리스트
@Override
public List<CommonCode> getCommonCode() {
	List<CommonCode> result = mh.getCommonCode();
	return  result;
}
	
	
//옵션별 갯수
@Override   
public int conditionOptionCount(String code) {
	//log.info("InquireServiceImpl  conditionOptionCount Start" );
	int conditionInquireCnt = mh.condOptionInqCnt(code);
	//log.info("InquireServiceImpl  conditionOptionCount conditionInquireCnt" + conditionInquireCnt);		
	return conditionInquireCnt;
}
//옵션별 리스트
@Override
public List<Inquire> listFilterOptionInquire(Inquire inquire) {
	List<Inquire> inquireOptionFilterList = null;
	//log.info("InquireServiceImpl listFilterOptionInquire Start...");
	inquireOptionFilterList = mh.mhOptionInquireList(inquire);
	//log.info("InquireServiceImpl listFilterOptionInquire inquireTraFilterList.size()"+ inquireOptionFilterList.size());
	return inquireOptionFilterList;
}

			



}
