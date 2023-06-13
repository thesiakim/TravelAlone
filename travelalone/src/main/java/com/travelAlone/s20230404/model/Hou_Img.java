package com.travelAlone.s20230404.model;


import lombok.Data;

@Data
public class Hou_Img {
	
	 private long 			img_id;
	 private long 			house_id;
	 private String 		img_context;
	 private String 		img_original_file;
	 private String 		img_stored_file;
	

}


//IMG_ID				= imgup_id;
//HOUSE_ID				= 숙소번호
//IMG_CONTEXT  			=  이미지경로
//IMG_ORIGINAL_FILE		= 원본이미지이름
//IMG_STORED_FILE     =  savedName;
//COMMON_IMAGESTYPE   =  contentType; null해도됨
