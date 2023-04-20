package com.travelAlone.s20230404.dao.mh;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.mh.Inquire;
import com.travelAlone.s20230404.model.mh.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class mhDaoImpl implements mhDao {

	
	private final SqlSession session;
//=========공지사항====================================
	@Override
	public int totalNotice() {
		int totNoticeCount = 0;
		System.out.println("mhDaoImpl Start total..." );

		try {
			totNoticeCount = session.selectOne("mhNoticeTotal");
			System.out.println("mhDaoImpl totalNotice totNoticeCount->" +totNoticeCount);

		} catch (Exception e) {
			System.out.println("mhDaoImpl totalNotice Exception->"+e.getMessage());
		}
		
		
		return totNoticeCount;
	}

	@Override
	public List<Notice> selectNoticeList(Notice notice) {
		List<Notice> noticeList = new ArrayList<Notice>(); 
				
		try {
			log.info("mhDaoImpl selectNoticeList mhNoticeList Start...");					
			noticeList = session.selectList("mhNoticeList",notice);
			log.info("mhDaoImpl selectNoticeList mhNoticeList End...");
		} catch (Exception e) {
			e.getStackTrace();
			log.info("mhDaoImpl selectNoticeList mhNoticeList error...");
		}
		
		return noticeList;
	}
	//글자세히보기
	@Override
	public Notice detailNotice(int g_notice_id) {
		System.out.println("mhDaoImpl detail start..");
		Notice notice = new Notice();
		
		try {
			notice = session.selectOne("mhNoticeSelOne", g_notice_id);
			System.out.println("mhDaoImpl detail notice.getG_notice_title()->"+ notice.getG_notice_title());

		} catch (Exception e) {
			System.out.println("mhDaoImpl detail Exception->"+e.getMessage());
		}
		
		return notice;
	}
//글작성
	@Override
	public int insertNotice(Notice notice) {
		int result = 0;
		System.out.println("mhDaoImpl insert Start..." );
		try {
			result = session.insert("insertNotice",notice);
		} catch (Exception e) {
			System.out.println("mhDaoImpl insert Exception->"+e.getMessage());
		}
		
		return result;
	}

	@Override
	public int updateNotice(Notice notice) {
		System.out.println("mhDaoImpl updateNotice start..");
		int updateCount= 0;
		try {
			updateCount = session.update("mhNoticeUpdate", notice);
		} catch (Exception e) {
			System.out.println("mhDaoImpl updateEmp Exception->" + e.getMessage());
		}
		return updateCount;
	}

	@Override
	public int deleteNotice(int g_notice_id) {
		System.out.println("mhDaoImpl delete start..");
		int result = 0;
		System.out.println("mhDaoImpl delete g_notice_id->"+ g_notice_id);
		try {
			result = session.delete("deleteNotice",g_notice_id);
			System.out.println("mhDaoImpl delete result->"+ result);
			
		} catch (Exception e) {
			System.out.println("mhDaoImpl delete Exception->"+ e.getMessage());
		}				
		return result;
	}

	@Override
	public List<Notice> noticeSearchList(Notice notice) {
		List<Notice> noticeSearchList = null;
		log.info("mhDaoImpl noticeSearchList start..");
		try {
			noticeSearchList = session.selectList("mhNoticeSearchList",notice);
		} catch (Exception e) {
			log.info("mhDaoImpl noticeSearchList Exception " + e.getMessage());
		}
		
		
		return noticeSearchList;
	}
	
	
	@Override
	public int condNotice(Notice notice) {
		int conditionNoticeCount = 0;
		log.info("mhDaoImpl Start total...");
		try {
			conditionNoticeCount = session.selectOne("condNoticeCnt",notice);
			log.info("mhDaoImpl conditionNoticeCount->"+conditionNoticeCount);
		
		} catch (Exception e) {
			log.info("mhDaoImpl Exception"+ e.getMessage());
		}
		
		return conditionNoticeCount;
	}
//=========================자주묻는질문============================================================================
	@Override
	public List<Notice> selectFaqList(Notice notice) {
		List<Notice> faqList = new ArrayList<Notice>(); 
		try {
			log.info("mhDaoImpl selectFaqList mhFaqList Start...");		
			faqList = session.selectList("mhFaqList",notice);
			log.info("mhDaoImpl selectFaqList mhFaqList End...");
		} catch (Exception e) {
			e.getStackTrace();
			log.info("mhDaoImpl selectNoticeList selectFaqList error...");
		}
		
		return faqList;
	}
	
	
	
	
//=========================문의게시판============================================================================
	@Override
	public int totalInquire() {
		int totalInquireCount = 0;
		log.info("mhDaoImpl Start totalInq...");
		try {
			totalInquireCount = session.selectOne("mhInqTotal");
			log.info("mhDaoImpl totalInquire totalInquireCount->" +totalInquireCount);
		} catch (Exception e) {
			log.info("mhDaoImpl Exception"+e.getMessage());
		}
						
		return totalInquireCount;
	}

	@Override
	public List<Inquire> selectInquireList(Inquire inquire) {
		List<Inquire> inquireList = new ArrayList<Inquire>();
		try {
			log.info("mhDaoImpl selectInquireList mhInquireList Start...");
			inquireList = session.selectList("mhInquireList", inquire);
		} catch (Exception e) {
			e.getStackTrace();
			log.info("mhDaoImpl selectInquireList mhInquireList error...");
		}
		
		
		return inquireList;
	}

	@Override
	public Inquire detailInquire(int g_writing_id) {
		log.info("mhDaoImpl detail start..");
		Inquire inquire = new Inquire();
		try {
			inquire = session.selectOne("mhInquireSelOne",g_writing_id );
			log.info("mhDaoImpl detail inquire.getG_title()->"+ inquire.getG_title());			
		} catch (Exception e) {
			log.info("mhDaoImpl detail Exception->" + e.getMessage());
		}
		
		return inquire;
	}

	@Override
	public int insertInquire(Inquire inquire) {
		int result = 0;
		log.info("mhDaoImpl insert Start... " );
		try {
			result = session.insert("insertInquire",inquire);
		} catch (Exception e) {
			log.info("mhDaoImpl insert Exception->" + e.getMessage());
		}
		return result;
	}

	@Override
	public int updateInquire(Inquire inquire) {
		log.info("mhDaoImpl updateInquire start..");
		int updateCount = 0;
		try {
			updateCount = session.update("mhInquireUpdate", inquire);
		} catch (Exception e) {
		log.info("mhDaoImpl updateInquire Exception->" + e.getMessage());	
		}
		return updateCount;
	}

	@Override
	public int deleteInquire(int g_writing_id) {
		log.info("mhDaoImpl delete start..");
		int result = 0;
		log.info("mhDaoImpl delete g_writing_id->"+ g_writing_id);
		try {
			result = session.delete("deleteInquire",g_writing_id);
			log.info("mhDaoImpl delete result->"+ result);
		} catch (Exception e) {
			log.info("mhDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}
	//검색
	@Override
	public List<Inquire> inquireSearchList(Inquire inquire) {
		List<Inquire> inquireSearchList = null;
		log.info("mhDaoImpl inquireSearchList start..");
		
		try {
			inquireSearchList = session.selectList("mhinquireSearchList",inquire);
	} catch (Exception e) {
		log.info("mhDaoImpl mhinquireSearchList Exception " + e.getMessage());
	}
		
		return inquireSearchList;
	}
	//검색결과갯수
	@Override
	public int condInquireCnt(Inquire inquire) {
		int conditionInquireCount = 0;
		log.info("mhDaoImpl Start total...");
		try {
			conditionInquireCount = session.selectOne("condInquireCnt",inquire);
			log.info("mhDaoImpl conditionInquireCount->"+conditionInquireCount);		
		} catch (Exception e) {
			log.info("mhDaoImpl Exception"+ e.getMessage());
		}			
		return conditionInquireCount;
	}

	//공통코드 호출부  여행지문의 숙소문의 맛집문의 같은거
		@Override
		public List<CommonCode> getCommonCode() {
			log.info("getCommonCode 호출부 .......");
			List<CommonCode> result = session.selectList("mhCommonCode");
			log.info("getCommonCode data {},{} .......",result.get(0).getCode(),result.get(0).getValue());
			
			return  result;
		}

		@Override
		public List<Inquire> mhOptionInquireList(Inquire inquire) {
			List<Inquire> inquireFilterList = null;
			log.info("mhDaoImpl inquireFilterList start..");
			try {
				inquireFilterList = session.selectList("mhOptionInquireList",inquire);
			} catch (Exception e) {
				log.info("mhDaoImpl mhTraInqFilterList Exception " + e.getMessage());
			}
			return inquireFilterList;
		}

		@Override
		public int condOptionInqCnt(String code) {
			int count = 0;		
			log.info("mhDaoImpl Start total...");
			try {
				count = session.selectOne("condOptionInquireCnt",code);
				log.info("mhDaoImpl condOptionInquireCnt->"+count);		
			} catch (Exception e) {
				log.info("mhDaoImpl Exception"+ e.getMessage());
			}			
			return count;
		}

	
	
	

	//답변하기
	@Override
	public int replyCount(Inquire inquire) {
		log.info("mhDaoImpl replyInquire start..");
		int replyCount = 0;
		System.out.println("mhDaoImpl reply" + inquire.getG_writing_id());
		System.out.println("mhDaoImpl reply" + inquire.getG_content());
		
		try {
			replyCount = session.update("mhInquireReply", inquire);
		} catch (Exception e) {
			log.info("mhDaoImpl replyInquire Exception->" + e.getMessage());	
		}				
		return replyCount;
	}

	
	

	

}
