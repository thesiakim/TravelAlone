package com.travelAlone.s20230404.model.mh;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Inquire {

	private int 			g_writing_id;
	private long 			member_id;
	private String			g_common_csboard;
	private String  		g_title;
	private String 			g_content;
	private String 			g_passwd;
	private int  			g_view_cnt;
	private char			g_reply_yn;
	private String			g_reply_content;
	private LocalDate		create_date;
	private LocalDate		modified_date;
	
	// 조회용
	private String search;   	private String keyword;
	private String pageNum;  
	//            1                           10
	private int start; 		 	private int end;
	
	
	//조회용 코드 
	private String code;

	// member조인용
	private String m_nickname;


	
	
}

//G_WRITING_ID 		= 문의글 번호
//MEMBER_ID			= 사용자아이디
//G_COMMON_CSBOARD  = 문의글 종류
//G_TITLE			= 문의글제목
//G_CONTENT			= 문의글 내용
//G_PASSWD			= 문의글 비밀번호
//G_VIEW_CNT		= 문의글 조회수
//G_REPLY_YN		= 문의글 답변여부
//G_REPLY_CONTENT	= 문의글  답변내용
//CREATE_DATE		= 문의글 작성일
//MODIFIED_DATE 	= 문의글 수정일