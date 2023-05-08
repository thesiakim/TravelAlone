package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Res {
	
	   private long            restaurant_id;
	   private String          r_name;
	   private String          r_content;
	   private String          r_address;
	   private String          r_hour;
	   private String          r_call;
	   private String          r_parking;
	   private String          r_menu;
	   private String          r_common_restaurant;
	   private String          r_common_loc;
	   private LocalDateTime   create_date;
	   private LocalDateTime   modified_date;
	   private String		   r_revcount;
	   private String		   r_avgscore;
	   private int             r_count;
	   
	   // 페이지조회용
	   private String search;      private String keyword;
	   private String pageNum;  
	   //            1                           10
	   private int start;           private int end;
	   
	   //조회용 코드 
	   private String code;
	   
	   //create_date 날짜 형식 변경
		public String getFormattedCreateDate() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			    return create_date.format(formatter);
			  }
		

		//조회용
		private String img_stored_file;

		// member 조인
		private String m_nickname;
}

	   
 //RESTAURANT_ID		NUMBER					맛집ID
 //R_NAME				VARCHAR2(50 BYTE)		맛집이름
 //R_CONTENT			VARCHAR2(4000 BYTE)		내용
 //R_ADDRESS			VARCHAR2(4000 BYTE)		주소
 //R_HOUR				VARCHAR2(4000 BYTE)		운영시간
 //R_CALL				VARCHAR2(4000 BYTE)		전화번호
 //H_PARKING			VARCHAR2(4000 BYTE)		주차
 //R_MENU				VARCHAR2(4000 BYTE)		메뉴
 //R_COMMON_RESTAURANT	VARCHAR2(20 BYTE)		맛집 종류
 //R_COMMON_LOC			VARCHAR2(20 BYTE)		맛집 지역
 //CREATE_DATE			DATE					생성일
 //MODIFIED_DATE		DATE					수정일
