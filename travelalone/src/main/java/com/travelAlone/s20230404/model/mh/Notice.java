package com.travelAlone.s20230404.model.mh;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Notice {
	
	private int 				g_notice_id; 
	private long 				member_id;
	private String 				g_common_csboard;
	private String 				g_notice_title;
	private String 				g_notice_content;
	//private LocalDateTime		create_date;
	private LocalDate			create_date;
	private LocalDateTime 		modified_date;
	
	
	// 조회용  ㅎㅇㅎㅇㅎㅇㅎㅇ
	private String search;   	private String keyword;
	private String pageNum;  
	//            1                           10
	private int start; 		 	private int end;

	
//	g_notice_id : 게시글ID			G_NOTICE_ID
//	member_id	: 회원id				MEMBER_ID
//	g_common_csboard :게시판종류		G_COMMON_CSBOARD
//	g_notice_title :제목				G_NOTICE_TITLE
//	g_notice_content: 내용			G_NOTICE_CONTENT
//	create_date:생성일				CREATE_DATE
//	modified_date:수정일				MODIFIED_DATE
}
