package com.travelAlone.s20230404.domain.si;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "travel")
public class TravelJPA {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAVEL_ID_SEQ")
	    @SequenceGenerator(name = "TRAVEL_ID_SEQ", sequenceName = "TRAVEL_ID_SEQ", allocationSize = 1)
	    private Long            travel_id;

	    private String			t_name;
	    private String			t_content;
	    private String			t_address;
	    private String			t_call;
	    private String			t_hour;
	    private String			t_parking;
	    private String			t_fee; 
	    private String			t_common_travel;
	    private String			t_common_loc; 
	    private int			    t_count;
	    private LocalDateTime   create_date;
	    private LocalDateTime   modified_date;
	    

}
