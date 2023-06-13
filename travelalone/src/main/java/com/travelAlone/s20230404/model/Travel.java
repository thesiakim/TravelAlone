package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class Travel {
	
    private long 		   travel_id;
    private String 		   t_name;
    private String 		   t_content;
    private String 		   t_address;
    private String 		   t_call;
    private String 		   t_hour;
    private String 		   t_parking;
    private String 		   t_fee;
    private String 		   t_common_travel;
    private String 		   t_common_loc;
    private LocalDateTime  create_date;
    private LocalDateTime  modified_date;
    private String		   t_revcount;
    private String		   t_avgscore;
    private int            t_count;
	
    
    // 페이지조회용
    private String search;      private String keyword;
    private String pageNum;  
    //            1                          10
    private int start;           private int end;
    
	//조회용 코드 
	private String code;
	
	// create_date 날짜 형식 변경
 	public String getFormattedCreateDate() {
 		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
 	    return create_date.format(formatter);
 	  }
 	
 	//조회용
 	private String img_stored_file;
	
	
}


//TRAVEL_ID			NUMBER					여행지 ID
//T_NAME			VARCHAR2(50 BYTE)		여행지명
//T_CONTENT			VARCHAR2(4000 BYTE)		여행지 정보
//T_ADDRESS			VARCHAR2(4000 BYTE)		주소
//T_CALL			VARCHAR2(4000 BYTE)		전화번호
//T_HOUR			VARCHAR2(4000 BYTE)		운영시간
//T_PARKING			VARCHAR2(4000 BYTE)		주차
//T_FEE				VARCHAR2(4000 BYTE)		입장료
//T_COMMON_TRAVEL	VARCHAR2(20 BYTE)		여행지 종류
//T_COMMON_LOC		VARCHAR2(20 BYTE)		여행지 지역
//CREATE_DATE		DATE					생성일
//MODIFIED_DATE		DATE					수정일
