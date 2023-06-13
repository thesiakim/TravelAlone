package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Board {

	private long board_id;					// 글 ID
	private long member_id;					// 회원 ID
	private String b_title;					// 제목
	private String b_content;				// 내용
	private int b_view_cnt;					// 조회수
	private int b_like_cnt;					// 추천수
	private String b_common_board;			// 게시판 분류
	private int b_ref;						// 글 그룹
	private int b_re_step;					// 댓글 순서
	private int b_re_level;					// 댓글 단계
	private LocalDateTime create_date;		// 생성일
	private LocalDateTime modified_date;	// 수정일
	private int img_stored_file_yn;			// 이미지 여부

	// 조인용
	private String m_nickname;		

	// 조회용
	private String orderList;
	private int start;
	private int end;
	private String pageNum;
	private int mcd;
	private String listCategory;

	//	사진 파일 이름 가져오기
	private List<String> img_stored_file = new ArrayList<>();
	
	// 파일 유무 확인
	private String keyWord;
	
	// create_date 날짜 형식 변경
	public String getFormattedCreateDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return create_date.format(formatter);
	}
	
	//전체 검색 결과 페이지용 create_date 날짜 형식 변경
	public String getFormattedCreateDateSearch() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		return create_date.format(formatter);
		  }
	
	// 이미지 업로드용
	public Board(long member_id, String b_title, String b_content, String b_common_board) {
		this.member_id = member_id;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_common_board = b_common_board;
	}

}