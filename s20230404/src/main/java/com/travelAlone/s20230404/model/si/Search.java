package com.travelAlone.s20230404.model.si;

import lombok.Data;

@Data
public class Search {
	
	private long search_id;
	private String s_keyword;
	private int s_count;
	private String create_date;
	private String modified_date;

}
