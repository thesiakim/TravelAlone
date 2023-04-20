package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class House {
	
	private long house_id;
	private String h_name;
	private String h_content;
	private String h_address;
	private String h_checkinout;
	private String h_call;
	private String h_room;
	private String h_eat;
	private String h_parking;
	private String h_common_house;
	private String h_common_loc;
    private LocalDateTime  create_date;
    private LocalDateTime  modified_date;
    private int h_count;
    
    // 페이지조회용
    private String search;      private String keyword;
    private String pageNum;  
    //            1                          10
    private int start;           private int end;



}
