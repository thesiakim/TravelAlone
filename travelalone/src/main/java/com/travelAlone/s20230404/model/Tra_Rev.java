package com.travelAlone.s20230404.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Tra_Rev {
	private  long 				review_id;
	private  long 				travel_id;
	private  long 				member_id;
	private  String 			r_content;
	private  int	 			r_score;
	private  LocalDate 	 		create_date;
    private  LocalDate 	 		modified_date;
	

    //member조인용
	private String m_nickname;
	
	// 페이지조회용
	private String search;   	private String keyword;
	private String pageNum;  
	//            1                          10
	private int start; 		 	private int end;
	// 리뷰 조인용
	private String			t_name;

}

//REVIEW_ID		NUMBER					리뷰ID
//TRAVEL_ID		NUMBER					여행지ID
//MEMBER_ID		NUMBER					회원ID
//R_CONTENT		VARCHAR2(4000 BYTE)		내용
//R_SCORE		NUMBER					점수
//CREATE_DATE	DATE					생성일
//MODIFIED_DATE	DATE					수정일