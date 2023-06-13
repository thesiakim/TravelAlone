package com.travelAlone.s20230404.domain.si;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "RESTAURANT")
public class ResJPA {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESTAURANT_ID_SEQ")
    @SequenceGenerator(name = "RESTAURANT_ID_SEQ", sequenceName = "RESTAURANT_ID_SEQ", allocationSize = 1)
	 private long            restaurant_id;
	
	 private String          r_name;
	 private String          r_content;
	 private String          r_address;
	 private String          r_hour;
	 private String          r_call;
	 private String          r_parking;
	 private String          r_menu;
	 private String          r_common_restaurant;
	 private String          r_common_loc;
	 private int             r_count;
	 private LocalDateTime   create_date;
	 private LocalDateTime   modified_date;


}
