package com.travelAlone.s20230404.model;
	
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Bod_Img {
		
		private long		    board_id;			// 글 ID
		private long		    img_id;				// IMG ID
		private String	        img_context;		// 이미지 경로
		private String	        img_original_file;	// 원본 파일 이름
		private String	        img_stored_file;	// 저장된 파일 이름
		private	String	        common_imagesType;	// 이미지 타입
		private LocalDateTime	create_date;		// 생성일
		private LocalDateTime	modified_date;		// 수정일

		
}



