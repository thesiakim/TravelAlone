package com.travelAlone.s20230404.model;

import lombok.Data;

@Data
public class Res_Img {
	
	 private long 			img_id;
	 private long 			restaurant_id;
	 private String 		img_context;
	 private String 		img_original_file;
	 private String 		img_stored_file;

}
