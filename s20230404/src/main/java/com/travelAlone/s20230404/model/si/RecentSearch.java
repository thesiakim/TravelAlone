package com.travelAlone.s20230404.model.si;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RecentSearch {
	
	private Long recent_id;
	private Long member_id;
	private String search_term;
	private LocalDateTime create_date;
	
	public RecentSearch(Long member_id, String search_term) {
		this.member_id = member_id;
		this.search_term = search_term;
	}

}
