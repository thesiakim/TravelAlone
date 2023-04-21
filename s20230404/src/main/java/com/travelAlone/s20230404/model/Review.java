package com.travelAlone.s20230404.model;

	import java.time.LocalDateTime;

	import lombok.ToString;
	import lombok.Getter;
	import lombok.Setter;

	@Getter
	@Setter
	@ToString
	public class Review {
		
	    private long member_id;
	    private long review_id;
	    private long restaurant_id;
	    private long house_id;
	    private long travel_id;
	    private String r_common_tsr_category;
	    private String r_content;
	    private String r_score;
	    private LocalDateTime  create_date;
	    private LocalDateTime  modified_date;
		
	    // 페이지조회용
	    private String search;      private String keyword;
	    private String pageNum;  
	    //            1                          10
	    private int start;           private int end;
	    
	    
	}


	//MEMBER_ID					NUMBER					회원 ID
	//REVIEW_ID					NUMBER					리뷰 ID
	//RESTAURANT_ID				NUMBER					맛집 ID
	//HOUSE_ID					NUMBER					여행지 ID
	//TRAVEL_ID					NUMBER					여행지 ID
	//R_COMMON_TSR_CATEGORY		VARCHAR2(20 BYTE)		여행, 숙소, 맛집 분류
	//R_CONTENT					VARCHAR2(20 BYTE)		내용
	//R_SCORE					NUMBER					평점
	//CREATE_DATE				DATE					생성일
	//MODIFIED_DATE				DATE					수정일

