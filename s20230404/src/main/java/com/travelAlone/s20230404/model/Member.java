package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Member {
	
	private long member_id;
	private String m_email;
	private String m_nickname;
	private String m_name;
	private String m_passwd;
	private String m_common_gender;
	private String m_phone;
	private String m_common_role;
	private String m_img_context;
	private String m_img_original_file;
	private String m_img_stored_file;
	private String m_common_imagestype;
	private LocalDateTime create_date;
	private LocalDateTime modified_date;
	

}
