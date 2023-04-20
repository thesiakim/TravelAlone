package com.travelAlone.s20230404.model;


import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class House {

	private int				house_id;
	private String			h_name;
	private String			h_content;
	private String			h_address;
	private String			h_checkinout;
	private String			h_call;
	private String			h_room;
	private String			h_eat;
	private String			h_parking;
	private String			h_common_house;
	private String			h_common_loc;
	private LocalDateTime	create_date;
	private LocalDateTime	modified_date;
	
	// 페이지조회용
	private String search;   	private String keyword;
	private String pageNum;  
	//            1                           10
	private int start; 		 	private int end;
	
}



//HOUSE_ID			NUMBER					숙소ID
//H_NAME			VARCHAR2(50 BYTE)		숙소명
//H_CONTENT			VARCHAR2(4000 BYTE)		내용
//H_ADDRESS			VARCHAR2(4000 BYTE)		주소
//H_CHECKINOUT		VARCHAR2(4000 BYTE)		체크인아웃
//H_CALL			VARCHAR2(4000 BYTE)		문의전화
//H_ROOM			VARCHAR2(4000 BYTE)		룸종류
//H_EAT				VARCHAR2(4000 BYTE)		조식
//H_PARKING			VARCHAR2(4000 BYTE)		주차
//H_COMMON_HOUSE	VARCHAR2(20 BYTE)		숙소종류
//H_COMMON_LOC		VARCHAR2(20 BYTE)		숙소 지역
//CREATE_DATE		DATE					생성일
//MODIFIED_DATE		DATE					수정일

