package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Hou_Img {
	
	 private long house_id;
	 private long img_id;
	 private String img_context;
	 private String img_original_file;
	 private String img_stored_file;
	 private String common_imagesType;
	 private LocalDateTime  create_date;
	 private LocalDateTime  modified_date;

}
