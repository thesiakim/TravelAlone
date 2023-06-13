package com.travelAlone.s20230404.dao.mh;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.mh.Inq_Img;
import com.travelAlone.s20230404.model.mh.Inquire;
import com.travelAlone.s20230404.model.mh.Not_Img;
import com.travelAlone.s20230404.model.mh.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MhDaoImpl implements MhDao {

	
	private final SqlSession session;
	
//=============================공지사항========================================================

@Override
public int totalNotice() {
	int totNoticeCount = 0;
	//log.info("NoticeDaoImpl Start total..." );
	try {
		totNoticeCount = session.selectOne("mhNoticeTotal");
		//log.info("NoticeDaoImpl totalNotice totNoticeCount->" +totNoticeCount);
	} catch (Exception e) {
		//log.info("NoticeDaoImpl totalNotice Exception->"+e.getMessage());
	}		
	return totNoticeCount;
}


//글목록
@Override
public List<Notice> selectNoticeList(Notice notice) {
	List<Notice> noticeList = new ArrayList<Notice>(); 			
	try {
		//log.info("NoticeDaoImpl selectNoticeList mhNoticeList Start...");					
		noticeList = session.selectList("mhNoticeList",notice);
		//log.info("NoticeDaoImpl selectNoticeList mhNoticeList End...");
	} catch (Exception e) {
		e.getStackTrace();
		//log.info("NoticeDaoImpl selectNoticeList mhNoticeList error...");
	}
	
	return noticeList;
}
//글번호
@Override
public int seqNotice(Notice notice) {
	int result = 0;
	//log.info("NoticeDaoImpl seqNotice Start");
	try {
		result = session.selectOne("mhSeqNotice",notice);
	} catch (Exception e) {
		//log.info("NoticeDaoImpl seqNotice Exception->"+e.getMessage());
	}
	return result;
}

//글작성
	@Override
	public int insertNotice(Notice notice) {
		int result = 0;
		//log.info("NoticeDaoImpl insert Start..." );
		try {
			result = session.insert("insertNotice",notice);
		} catch (Exception e) {
		//log.info("NoticeDaoImpl insert Exception->"+e.getMessage());
		}		
		return result;
}

	
	
//이미지 디비삽입
@Override
public int insertNotImg(Not_Img not_Img) {
	int result = 0;
	//log.info("NoticeDaoImpl insert Start");
	//log.info("NoticeDaoImpl insertImg not_Img->"+ not_Img);
	try {
		result = session.insert("insertNotImg",not_Img);
	} catch (Exception e) {
    //log.info("NoticeDaoImpl insert Exception->"+e.getMessage());
	}	
	
	return result;
}
	
	
@Override
public int updateNotice(Notice notice) {
	//log.info("NoticeDaoImpl updateNotice start..");
	int updateCount= 0;
	try {
		updateCount = session.update("mhNoticeUpdate", notice);
	} catch (Exception e) {
	//log.info("NoticeDaoImpl updateEmp Exception->" + e.getMessage());
	}
	return updateCount;
}	
	
	
	
//글자세히보기
@Override
public Notice detailNotice(int g_notice_id) {
	//log.info("mhDaoImpl detail start..");
	Notice notice = new Notice();
	
	try {
		notice = session.selectOne("mhNoticeSelOne", g_notice_id);
		//log.info("NoticeDaoImpl detail notice.getG_notice_title()->"+ notice.getG_notice_title());

	} catch (Exception e) {
		//log.info("NoticeDaoImpl detail Exception->"+e.getMessage());
	}
	
	return notice;
}


//공지사항 이미지 리스트
@Override
public List<Not_Img> selectNotImgList(Not_Img not_Img) {
	List<Not_Img> notImgList = new ArrayList<Not_Img>();
	try {
		//log.info("NoticeDaoImpl selectNotImgList notImgList Start");
		notImgList = session.selectList("mhNotImgList", not_Img);
	} catch (Exception e) {
		//log.info("NoticeDaoImpl selectNotImgList Exception " + e.getMessage());
	}
	
	return notImgList;
}


@Override
public int deleteNotice(int g_notice_id) {
	//log.info("NoticeDaoImpl delete start..");
	int result = 0;
	//log.info("NoticeDaoImpl delete g_notice_id->"+ g_notice_id);
	try {
		result = session.delete("deleteNotice",g_notice_id);
		//log.info("NoticeDaoImpl delete result->"+ result);
		
	} catch (Exception e) {
		//log.info("NoticeDaoImpl delete Exception->"+ e.getMessage());
	}				
	return result;
}


	
@Override
public int deleteNotImg(int g_notice_id) {
	//log.info("NoticeDaoImpl deleteNotImg start");
	int result = 0;
	//log.info("NoticeDaoImpl deleteNotImg g_notice_id->"+ g_notice_id);
	try {
		result = session.delete("deleteNotImg",g_notice_id);
	} catch (Exception e) {
	// log.info("NoticeDaoImpl delete Exception->"+ e.getMessage());
	}
	
	return result;
}	
	
	
	
@Override
public int deleteNotOneImg(int g_notice_id, int img_id) {
	Not_Img not_Img = new Not_Img();
	not_Img.setG_notice_id(g_notice_id);
	not_Img.setImg_id(img_id);
	
	//log.info("NoticeDaoImpl deleteNotOneImg start");
	int result = 0;
	//log.info("NoticeDaoImpl deleteNotOneImg g_notice_id->"+ g_notice_id);
	//log.info("NoticeDaoImpl deleteNotOneImg img_id->"+ img_id);
	try {
		result = session.delete("deleteNotOneImg",not_Img );
	} catch (Exception e) {
		//log.info("NoticeDaoImpl delete Exception->"+ e.getMessage());
	}
	
	
	return result;
}	


	
@Override
public List<Notice> noticeSearchList(Notice notice) {
	List<Notice> noticeSearchList = null;
	//log.info("NoticeDaoImpl noticeSearchList start..");
	try {
		noticeSearchList = session.selectList("mhNoticeSearchList",notice);
	} catch (Exception e) {
		//log.info("NoticeDaoImpl noticeSearchList Exception " + e.getMessage());
	}
	
	
	return noticeSearchList;
}
	
	
@Override
public int condNotice(Notice notice) {
	int conditionNoticeCount = 0;
	//log.info("NoticeDaoImpl Start total...");
	try {
		conditionNoticeCount = session.selectOne("condNoticeCnt",notice);
		//log.info("NoticeDaoImpl conditionNoticeCount->"+conditionNoticeCount);
	
	} catch (Exception e) {
		//log.info("NoticeDaoImpl Exception"+ e.getMessage());
	}
	
	return conditionNoticeCount;
}
//=========================자주묻는질문============================================================================
	@Override
	public List<Notice> selectFaqList(Notice notice) {
		List<Notice> faqList = new ArrayList<Notice>(); 
		try {
			//log.info("NoticeDaoImpl selectFaqList mhFaqList Start...");		
			faqList = session.selectList("mhFaqList",notice);
			//log.info("NoticeDaoImpl selectFaqList mhFaqList End...");
		} catch (Exception e) {
			e.getStackTrace();
			//log.info("NoticeDaoImpl selectNoticeList selectFaqList error...");
		}
		
		return faqList;
	}
	
	
	
	
//=========================문의게시판============================================================================
	
@Override
public int totalInquire() {
	int totalInquireCount = 0;
	//log.info("mhDaoImpl Start totalInq...");
	try {
		totalInquireCount = session.selectOne("mhInqTotal");
		//log.info("mhDaoImpl totalInquire totalInquireCount->" +totalInquireCount);
	} catch (Exception e) {
		//log.info("mhDaoImpl Exception"+e.getMessage());
	}
					
	return totalInquireCount;
}

@Override
public List<Inquire> selectInquireList(Inquire inquire) {
	List<Inquire> inquireList = new ArrayList<Inquire>();
	try {
		//log.info("InquireDaoImpl selectInquireList mhInquireList Start...");
		inquireList = session.selectList("mhInquireList", inquire);
	} catch (Exception e) {
		e.getStackTrace();
	//	log.error("{}",e.getMessage());
	//	log.info("InquireDaoImpl selectInquireList mhInquireList error...");
	}
	
	
	return inquireList;
}

@Override
public int seqInquire(Inquire inquire) {
	int result = 0;
	//log.info("InquireDaoImpl seqInquire Start");
	try {
		result = session.selectOne("mhSeqInquire",inquire);
	} catch (Exception e) {
		//log.info("InquireDaoImpl mhSeqInquire Exception->"+e.getMessage());
	}
	return result;
}


@Override
public int insertInquire(Inquire inquire) {
	int result = 0;
	//log.info("InquireDaoImpl insert Start... " );
	try {
		
		result = session.insert("insertInquire",inquire);
	} catch (Exception e) {
		//log.info("InquireDaoImpl insert Exception->" + e.getMessage());
	}
	return result;
}


//이미지 디비삽입
@Override
public int insertInqImg(Inq_Img inq_Img) {
	int result = 0;
	//log.info("InquireImpl insert Start");
	//log.info("InquireImpl insertImg inq_Img->"+ inq_Img);
	try {
		result = session.insert("insertInqImg",inq_Img);
	} catch (Exception e) {
		//log.info("InquireImpl insert Exception->"+e.getMessage());
	}				
	return result;
}


@Override
public Inquire detailInquire(int g_writing_id) {
	//log.info("InquireDaoImpl detail start..");
	Inquire inquire = new Inquire();
	try {
		inquire = session.selectOne("mhInquireSelOne",g_writing_id );
		//log.info("InquireDaoImpl detail inquire.getG_title()->"+ inquire.getG_title());			
	} catch (Exception e) {
		//log.info("InquireDaoImpl detail Exception->" + e.getMessage());
	}
	
	return inquire;
}

@Override
public List<Inq_Img> selectInqImgList(Inq_Img inq_Img) {
	List<Inq_Img> inqImgList = new ArrayList<Inq_Img>();
	try {
		//log.info("InquireDaoImpl selectInqImgList inqImgList Start");
		inqImgList = session.selectList("mhInqImgList", inq_Img);
	} catch (Exception e) {
		//log.info("InquireDaoImpl selectInqImgList Exception " +e.getMessage());
	}
	
	return inqImgList;
}


@Override
public int updateInquire(Inquire inquire) {
	//log.info("InquireDaoImpl updateInquire start..");
	int updateCount = 0;
	try {
		updateCount = session.update("mhInquireUpdate", inquire);
	} catch (Exception e) {
	//log.info("InquireDaoImpl updateInquire Exception->" + e.getMessage());	
	}
	return updateCount;
}


@Override
public int deleteInquire(int g_writing_id) {
	//log.info("InquireDaoImpl delete start..");
	int result = 0;
	//log.info("InquireDaoImpl delete g_writing_id->"+ g_writing_id);
	try {
		result = session.delete("deleteInquire",g_writing_id);
		//log.info("InquireDaoImpl delete result->"+ result);
	} catch (Exception e) {
	//	log.info("InquireDaoImpl delete Exception->"+ e.getMessage());
	}
	
	return result;
}

//문의사항 사진전체삭제
	@Override
	public int deleteInqImg(int g_writing_id) {
		//log.info("InquireDaoImpl deleteInqImg start");
		int result = 0;
		//log.info("InquireDaoImpl deleteInqImg g_writing_id->"+ g_writing_id);
		try {
			result = session.delete("deleteInqImg", g_writing_id);			
		} catch (Exception e) {
			//log.info("InquireDaoImpl delete Exception->"+ e.getMessage());
		}		
		return result;
	}



@Override
public int deleteInqOneImg(int g_writing_id, int img_id) {
	Inq_Img inq_Img = new Inq_Img();
	inq_Img.setG_writing_id(g_writing_id);
	inq_Img.setImg_id(img_id);
	
	//log.info("InquireDaoImpl deleteInqImg start");
	int result = 0;
	//log.info("InquireDaoImpl deleteHouImg g_writing_id->"+ g_writing_id);
	//log.info("InquireDaoImpl deleteHouImg img_id->"+ img_id);
	try {
		result = session.delete("deleteInqOneImg",inq_Img );
	} catch (Exception e) {
		//log.info("InquireDaoImpl delete Exception->"+ e.getMessage());
	}
			
	return result;
}

//검색
@Override
public List<Inquire> inquireSearchList(Inquire inquire) {
	List<Inquire> inquireSearchList = null;
	//log.info("InquireDaoImpl inquireSearchList start..");
	
	try {
		inquireSearchList = session.selectList("mhinquireSearchList",inquire);
} catch (Exception e) {
	//log.info("InquireDaoImpl mhinquireSearchList Exception " + e.getMessage());
}
	
	return inquireSearchList;
}


//검색결과갯수
@Override
public int condInquireCnt(Inquire inquire) {
	int conditionInquireCount = 0;
	//log.info("InquireDaoImpl Start total...");
	try {
		conditionInquireCount = session.selectOne("condInquireCnt",inquire);
		//log.info("InquireDaoImpl conditionInquireCount->"+conditionInquireCount);		
	} catch (Exception e) {
		//log.info("InquireDaoImpl Exception"+ e.getMessage());
	}			
	return conditionInquireCount;
}

//공통코드 호출부  여행지문의 숙소문의 맛집문의 같은거
@Override
public List<CommonCode> getCommonCode() {
	//log.info("getCommonCode 호출부 .......");
	List<CommonCode> result = session.selectList("mhCommonCode");
	//log.info("getCommonCode data {},{} .......",result.get(0).getCode(),result.get(0).getValue());
	
	return  result;
}

@Override
public List<Inquire> mhOptionInquireList(Inquire inquire) {
	List<Inquire> inquireFilterList = null;
	//log.info("InquireDaoImpl inquireFilterList start..");
	try {
		inquireFilterList = session.selectList("mhOptionInquireList",inquire);
	} catch (Exception e) {
		//log.info("InquireDaoImpl mhTraInqFilterList Exception " + e.getMessage());
	}
	return inquireFilterList;
}

@Override
public int condOptionInqCnt(String code) {
	int count = 0;		
	//log.info("InquireDaoImpl Start total...");
	try {
		count = session.selectOne("condOptionInquireCnt",code);
		//log.info("InquireDaoImpl condOptionInquireCnt->"+count);		
	} catch (Exception e) {
		//log.info("InquireDaoImpl Exception"+ e.getMessage());
	}			
	return count;
}

			

//답변하기
@Override
public int replyCount(Inquire inquire) {
	//log.info("InquireDaoImpl replyInquire start..");
	int replyCount = 0;
	//log.info("InquireDaoImpl reply" + inquire.getG_writing_id());
	//log.info("InquireDaoImpl reply" + inquire.getG_content());
	
	try {
		replyCount = session.update("mhInquireReply", inquire);
	} catch (Exception e) {
		//log.info("InquireDaoImpl replyInquire Exception->" + e.getMessage());	
	}				
	return replyCount;
}

	


	

}
