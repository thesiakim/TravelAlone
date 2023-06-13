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
@Table(name = "HOUSE")
public class HouseJPA {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOUSE_ID_SEQ")
	    @SequenceGenerator(name = "HOUSE_ID_SEQ", sequenceName = "HOUSE_ID_SEQ", allocationSize = 1)
	    private Long            house_id;

	    private String			h_name;
		private String			h_content;
		private String			h_address;
		private String			h_checkinout;
		private String			h_call;
		private String			h_room;
		private String			h_eat;
		private String			h_parking;
		private String			h_common_house;
		private String			h_common_loc;
		private int             h_count;
		private LocalDateTime	create_date;
		private LocalDateTime	modified_date;
	    

}
