package com.travelAlone.s20230404.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Tra_Fav {

	private  long 				member_id;
	private  long 				travel_id;
	private LocalDate 	 	create_date;
    private LocalDate	 	modified_date;
	private		int				isfavTra;
	
}
