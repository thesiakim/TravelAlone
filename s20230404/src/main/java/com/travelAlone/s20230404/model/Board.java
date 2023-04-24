package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Data;

@Data
public class Board {
	private long	        board_id;					private	long		    member_id;
	private String	        b_title;					private String	        b_content;
	private int		        b_view_cnt;					private	int		        b_like_cnt;
	private String	        b_common_board;				private int		        b_ref;
	private int		        b_re_step;			        private int		        b_re_level;
	private LocalDateTime	create_date;		        private LocalDateTime	modified_date;
	
	
	// 조인용
	private String	m_nickname;
	
	// 이미지용
	private int replyCnt;
	private List<Bod_Img> bod_Img;
	
	// 조회용
	private int		startRow;
	private int 	endRow;
	private String	pageNum;
	private int		mcd;
	private String	orderl;
	
	// create_date 날짜 형식 변경
	public String getFormattedCreateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return create_date.format(formatter);
    }
	
}
