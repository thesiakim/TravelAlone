package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;

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
	private String 				r_content;
	private int	 				r_score;
	private LocalDateTime 	 	create_date;
    private LocalDateTime 	 	modified_date;
	

//member조인용
	private String m_nickname;

}

//REVIEW_ID		NUMBER					리뷰ID
//TRAVEL_ID		NUMBER					여행지ID
//MEMBER_ID		NUMBER					회원ID
//R_CONTENT		VARCHAR2(4000 BYTE)		내용
//R_SCORE		NUMBER					점수
//CREATE_DATE	DATE					생성일
//MODIFIED_DATE	DATE					수정일