package com.travelAlone.s20230404.model.mh;

import lombok.Data;

@Data
public class Inq_Img {

	 private long 			img_id;
	 private long 			g_writing_id;
	 private String 		img_context;
	 private String 		img_original_file;
	 private String 		img_stored_file;
	
	
}

//G_WRITING_ID				NUMBER
//IMG_ID					NUMBER
//IMG_CONTEXT				VARCHAR2(255 BYTE)
//IMG_ORIGINAL_FILE			VARCHAR2(255 BYTE)
//IMG_STORED_FILE			VARCHAR2(4000 BYTE)