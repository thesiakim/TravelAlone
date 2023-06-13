package com.travelAlone.s20230404.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Hou_Fav {

	private     long 				member_id;
	private     long 				house_id;
	private     LocalDate 	 		create_date;
    private     LocalDate	 		modified_date;
	private		int					isfavHou;
}




//MEMBER_ID			NUMBER		회원id
//HOUSE_ID			NUMBER		숙소ID
//CREATE_DATE		DATE		생성일
//MODIFIED_DATE		DATE		수정일