package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;

import com.travelAlone.s20230404.domain.km.MemberJpa;
import lombok.Data;

@Data
public class Member {
	
	private long member_id;
	private String m_email;
	private String m_nickname;
	private String m_name;
	private String m_password;
	private String m_common_gender;
	private String m_phone;
	private String m_common_role;
	private String m_img_context;
	private String m_img_original_file;
	private String m_img_stored_file;
	private String m_common_imagestype;
	private LocalDateTime create_date;
	private LocalDateTime modified_date;
	
	public void of(MemberJpa memberJpa){
		this.member_id = memberJpa.getId();
		this.m_email = memberJpa.getEmail();
		this.m_nickname = memberJpa.getNickname();
		this.m_name = memberJpa.getName();
		this.m_password = memberJpa.getPassword();
		this.m_common_gender = memberJpa.getGender();
		this.m_phone = memberJpa.getPhone();
		this.m_common_role = memberJpa.getRole().toString();
		this.m_img_context = memberJpa.getImgContext();
		this.m_img_original_file = memberJpa.getImgOriginalFile();
		this.m_img_stored_file = memberJpa.getImgStoredFile();
		this.m_common_imagestype = memberJpa.getImagesType();
	}

	public void updateProfile(String m_img_context, String m_img_original_file, String m_img_stored_file, String m_common_imagestype){
		this.m_img_context = m_img_context;
		this.m_img_original_file = m_img_original_file;
		this.m_img_stored_file = m_img_stored_file;
		this.m_common_imagestype = m_common_imagestype;
	}
}
