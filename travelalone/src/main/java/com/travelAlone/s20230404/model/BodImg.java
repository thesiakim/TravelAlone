package com.travelAlone.s20230404.model;
	
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
public class BodImg {
		
		private Long		    board_id;			private Long		    img_id;
		private String	        img_context;		private String	        img_original_file;
		private String	        img_stored_file;	private	String	        common_imagesType;
		private LocalDateTime	create_date;		private LocalDateTime	modified_date;

	@Builder
	public BodImg(long board_id, long img_id, String img_context, String img_original_file, String img_stored_file, String common_imagesType, LocalDateTime create_date, LocalDateTime modified_date) {
		this.board_id = board_id;
		this.img_id = img_id;
		this.img_context = img_context;
		this.img_original_file = img_original_file;
		this.img_stored_file = img_stored_file;
		this.common_imagesType = common_imagesType;
		this.create_date = create_date;
		this.modified_date = modified_date;
	}
}



