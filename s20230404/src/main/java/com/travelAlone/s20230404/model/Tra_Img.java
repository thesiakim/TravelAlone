package com.travelAlone.s20230404.model;

import lombok.Data;

@Data
public class Tra_Img {
	
	 private long travel_id;
	 private long img_id;
	 private String img_context;
	 private String img_original_file;
	 private String img_stored_file;

}

//IMG_ID				= imgup_id;
//TRAVEL_ID				= 여행지번호
//IMG_CONTEXT  			= 이미지경로
//IMG_ORIGINAL_FILE		= 원본이미지이름
//IMG_STORED_FILE       = savedName;
