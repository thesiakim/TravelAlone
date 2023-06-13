package com.travelAlone.s20230404.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Res_Fav {

	private long 				member_id;
	private long 				restaurant_id;
	private LocalDate 	 		create_date;
    private LocalDate	 		modified_date;
	private int					isfavRes;

}

//MEMBER_ID			NUMBER		회원id
//RESTAURANT_ID		NUMBER		맛집ID
//CREATE_DATE		DATE		생성일
//MODIFIED_DATE		DATE		수정일