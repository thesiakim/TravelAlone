package com.travelAlone.s20230404.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Hou_Rev {
	private  long 				review_id;
	private  long 				house_id;
	private  long 				member_id;
	private String 				r_content;
	private int	 				r_score;
	private LocalDate 	 	create_date;
    private LocalDate	 	modified_date;
    
    
	// member조인용
	private String m_nickname;
	
	// 페이지조회용
	private String search;   	private String keyword;
	private String pageNum;  
	//            1                           10
	private int start; 		 	private int end;
	// 리뷰 조인용
	private String			h_name;
	

}



//REVIEW_ID		NUMBER					리뷰id
//HOUSE_ID		NUMBER					숙소ID
//MEMBER_ID		NUMBER					회원id
//R_CONTENT		VARCHAR2(4000 BYTE)		내용
//R_SCORE			NUMBER					점수
//CREATE_DATE		DATE					생성일
//MODIFIED_DATE	DATE					수정일