package com.travelAlone.s20230404.model;
	
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Bod_Img {
		
		private long		    board_id;			private long		    img_id;
		private String	        img_context;		private String	        img_original_file;
		private String	        img_stored_file;	private	String	        common_imagesType;
		private LocalDateTime	create_date;		private LocalDateTime	modified_date;
		
		
	}



