package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Board {
	private long	        board_id;			// 글 ID		
	private	long		    member_id;			// 회원 ID
	private String	        b_title;			// 제목
	private String	        b_content;			// 내용
	private int		        b_view_cnt;			// 조회수
	private	int		        b_like_cnt;			// 추천수
	private String	        b_common_board;		// 게시판분류
	private int		        b_ref;				// 글 그룹
	private int		        b_re_step;			// 댓글 순서 
	private int		        b_re_level;			// 댓글 단계
	private LocalDateTime	create_date;		// 생성일
	private LocalDateTime	modified_date;		// 수정일
	private	String			m_nickname;			// 닉네임
	
//	조회용
	private	String	search;		private	String	keyword;
	private	String	pageNum;
	private	int		start; 		private	int		end;
	
}
