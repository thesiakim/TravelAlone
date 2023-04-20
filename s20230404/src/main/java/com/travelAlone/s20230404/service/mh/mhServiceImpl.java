package com.travelAlone.s20230404.service.mh;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.mh.mhDao;
import com.travelAlone.s20230404.model.mh.Inquire;
import com.travelAlone.s20230404.model.mh.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class mhServiceImpl implements mhService {
	private final mhDao		mh;
		//11:19분작업 ㅇㅎㅇㅎㅎㅇㅎㅇㅎㅇㅎㅇㅎㅎㅇㅎㅇㅎ
	    //11:37 시아작업
		//11:42 gdgdgddgd
	@Override
	public int totalNotice() {
		System.out.println("mhServiceImpl Start totalNotice..." );
		int totNoticeCnt = mh.totalNotice();
		System.out.println("mhServiceImpl totalNotice totalNoticeCnt->" + totNoticeCnt);
		//원격저장소에서 수정함
		return totNoticeCnt;
	}

	@Override
	public List<Notice> listNotice(Notice notice) {
		log.debug("mhServiceImpl Start listNotice...");
		List<Notice> noticeList = mh.selectNoticeList(notice);
		log.debug("mhServiceImpl End listNotice...");
		
		
		return noticeList;
	}

	@Override
	public Notice detailNotice(int g_notice_id) {
		System.out.println("mhServiceImpl detail ...");
		Notice notice = null; 
		notice = mh.detailNotice(g_notice_id);
		return notice;						
	}

	@Override
	public int insertNotice(Notice notice) {
		int result = 0;
		System.out.println("mhServiceImpl insert Start..." );
		result = mh.insertNotice(notice);	
		return result;
	}

	@Override
	public int updateNotice(Notice notice) {
		System.out.println("mhServiceImpl update ...");
		int updateCount = 0;
		updateCount = mh.updateNotice(notice);		
		return updateCount;
	}

	@Override
	public int deleteNotice(int g_notice_id) {
		int result = 0;
		System.out.println("mhServiceImpl delete Start..." );
		result = mh.deleteNotice(g_notice_id);		
		return result;
	}

	@Override
	public List<Notice> listSearchNotice(Notice notice) {
		List<Notice> noticeSearchList = null;
		log.info("mhServiceImpl listSearchNotice Start...");
		noticeSearchList = mh.noticeSearchList(notice);
		log.info("mhServiceImpl listSearchNotice noticeSearchList.size()"+ noticeSearchList.size());
					
		return noticeSearchList;
	}
	
	
	
	@Override
	public int conditionNoticeCount(Notice notice) {
		System.out.println("mhServiceImpl  conditionNoticeCount Start" );
		int condNoticeCnt = mh.condNotice(notice);
		System.out.println("EmpServiceImpl conditionEmpCount condNoticeCnt->" + condNoticeCnt);
		return condNoticeCnt;
	}

	
	//=============================자주묻는질문게시판========================================================
	
	@Override
	public List<Notice> listFaq(Notice notice) {
		log.debug("mhServiceImpl Start listFaq...");
		List<Notice> faqList = mh.selectFaqList(notice);
		log.debug("mhServiceImpl End listFaq...");
		return faqList;
	}

	
	
	
	//=============================문의게시판========================================================
	@Override
	public int totalInquire() {
		log.info("mhServiceImpl Start totalInquire...");
		int totalInquireCnt = mh.totalInquire();
		log.info("mhServiceImpl Start totalInquireCnt..."+ totalInquireCnt);
		return totalInquireCnt;
	}

	@Override
	public List<Inquire> listInquire(Inquire inquire) {
		log.info("mhServiceImpl Start listInquire...");
		List<Inquire> inquireList = mh.selectInquireList(inquire);		
		return inquireList;
	}

	@Override
	public Inquire detailInquire(int g_writing_id) {
		log.info(("mhServiceImpl detail ..."));
		Inquire inquire = null;
		inquire = mh.detailInquire(g_writing_id);	
		return inquire;
	}

	@Override
	public int insertInquire(Inquire inquire) {
		int result = 0;
		log.info("mhServiceImpl insert Start...");
		result = mh.insertInquire(inquire);		
		return result;
	}

	@Override
	public int updateInquire(Inquire inquire) {
		log.info("mhServiceImpl update ...");
		int updateCount = 0;
		updateCount = mh.updateInquire(inquire);		
		return updateCount;
	}

	@Override
	public int deleteInquire(int g_writing_id) {
		int result = 0;
		log.info("mhServiceImpl delete Start...");
		result = mh.deleteInquire(g_writing_id);		
		return result;
	}

	@Override
	public List<Inquire> listSearchInquire(Inquire inquire) {
		List<Inquire> inquireSearchList = null;
		log.info("mhServiceImpl listSearchInquire Start...");
		inquireSearchList = mh.inquireSearchList(inquire);
		log.info("mhServiceImpl listSearchInquire inquireSearchList.size()"+ inquireSearchList.size());
				
		return inquireSearchList;
	}
	
	
	
	@Override
	public int conditionInquireCount(Inquire inquire) {
		log.info("mhServiceImpl  conditionInquireCount Start" );
		int conditionInquireCnt = mh.condInquireCnt(inquire);
		log.info("mhServiceImpl  conditionInquireCount conditionInquireCnt" + conditionInquireCnt);		
		return conditionInquireCnt;
	}

	
	//여행지문의 갯수
	@Override   
	public int conditionTravelCount(Inquire inquire) {
		log.info("mhServiceImpl  conditionTravelCount Start" );
		int conditionTraInquireCnt = mh.condTraInqCnt(inquire);
		log.info("mhServiceImpl  conditionTravelCount conditionInquireCnt" + conditionTraInquireCnt);		
		return conditionTraInquireCnt;
	}
	//여행지문의 리스트
	@Override
	public List<Inquire> listFilterTraInquire(Inquire inquire) {
		List<Inquire> inquireTraFilterList = null;
		log.info("mhServiceImpl listFilterInquire Start...");
		inquireTraFilterList = mh.inquireTraFilterList(inquire);
		log.info("mhServiceImpl listFilterInquire inquireTraFilterList.size()"+ inquireTraFilterList.size());
		return inquireTraFilterList;
	}
	//숙소문의 갯수
	@Override
	public int conditionHouseCount(Inquire inquire) {
		log.info("mhServiceImpl  conditionHouseCount Start" );
		int conditionHouInquireCnt = mh.condHouInqCnt(inquire);
		log.info("mhServiceImpl  conditionHouseCount conditionHouInquireCnt" + conditionHouInquireCnt);		
		return conditionHouInquireCnt;
	}
	//숙소문의 리스트
	@Override
	public List<Inquire> listFilterHouInquire(Inquire inquire) {
		List<Inquire> inquireHouFilterList = null;
		log.info("mhServiceImpl listFilterInquire Start...");
		inquireHouFilterList = mh.inquireHouFilterList(inquire);
		log.info("mhServiceImpl listFilterInquire inquireFilterList.size()"+ inquireHouFilterList.size());
		return inquireHouFilterList;
	}

	@Override
	public int conditionResCount(Inquire inquire) {
		log.info("mhServiceImpl  conditionResCount Start" );
		int conditionResInquireCnt = mh.condResInqCnt(inquire);
		log.info("mhServiceImpl  conditionResCount conditionHouInquireCnt" + conditionResInquireCnt);		
		return conditionResInquireCnt;
	}

	@Override
	public List<Inquire> listFilterResInquire(Inquire inquire) {
		List<Inquire> inquireResFilterList = null;
		log.info("mhServiceImpl listFilterInquire Start...");
		inquireResFilterList = mh.inquireResFilterList(inquire);
		log.info("mhServiceImpl listFilterInquire inquireResFilterList.size()"+ inquireResFilterList.size());
		return inquireResFilterList;
	}

	@Override
	public int conditionEtcCount(Inquire inquire) {
		log.info("mhServiceImpl  conditionEtcCount Start" );
		int conditionEtcInquireCnt = mh.condEtcInqCnt(inquire);
		log.info("mhServiceImpl  conditionResCount conditionHouInquireCnt" + conditionEtcInquireCnt);		
		return conditionEtcInquireCnt;
	}

	@Override
	public List<Inquire> listFilterEtcInquire(Inquire inquire) {
		List<Inquire> inquireEtcFilterList = null;
		log.info("mhServiceImpl inquireEtcFilterList Start...");
		inquireEtcFilterList = mh.inquireEtcFilterList(inquire);
		log.info("mhServiceImpl listFilterInquire inquireEtcFilterList.size()"+ inquireEtcFilterList.size());
		return inquireEtcFilterList;
	}

			
	
	//답변
	@Override
	public int replyInquire(Inquire inquire) {
		log.info("mhServiceImpl reply ...");
		int replyCount = 0;
		replyCount = mh.replyCount(inquire);
		return replyCount;
	}







}
