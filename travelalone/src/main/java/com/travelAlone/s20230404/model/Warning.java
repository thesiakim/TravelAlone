package com.travelAlone.s20230404.model;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Warning {
	
	private long w_id;						// 신고 ID
	private long member_id;					// 회원 ID
	private long u_nickname;				// 신고자 ID
	private String w_common_warning;		// 신고 사유
	private long w_cnt;						// 신고 수
	private LocalDateTime	create_date;	// 생성일	        
	private LocalDateTime	modified_date;	// 수정일
	
}