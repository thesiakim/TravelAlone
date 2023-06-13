package com.travelAlone.s20230404.model.mh;

import lombok.Data;

@Data
public class Not_Img {

	 private long 			img_id;
	 private long 			g_notice_id;
	 private String 		img_context;
	 private String 		img_original_file;
	 private String 		img_stored_file;
	
	
}



//G_NOTICE_ID			NUMBER
//IMG_ID				NUMBER
//IMG_CONTEXT			VARCHAR2(255 BYTE)
//IMG_ORIGINAL_FILE		VARCHAR2(255 BYTE)
//IMG_STORED_FILE		VARCHAR2(255 BYTE)