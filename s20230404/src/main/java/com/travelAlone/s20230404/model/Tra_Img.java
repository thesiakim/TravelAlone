package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class Tra_Img {
	
	 private long travel_id;
	 private long img_id;
	 private String img_context;
	 private String img_original_file;
	 private String img_stored_file;
	 private String common_imagesType;
	 private LocalDateTime  create_date;
	 private LocalDateTime  modified_date;
	   

}
