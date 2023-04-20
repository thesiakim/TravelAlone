package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;

import lombok.Data;

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
	   private int r_count;
	   
	   // 페이지조회용
	   private String search;      private String keyword;
	   private String pageNum;  
	   //            1                           10
	   private int start;           private int end;


}
