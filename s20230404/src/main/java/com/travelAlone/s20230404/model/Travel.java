package com.travelAlone.s20230404.model;

import java.time.LocalDateTime;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class Travel {
	
    private long travel_id;
    private String t_name;
    private String t_content;
    private String t_address;
    private String t_call;
    private String t_hour;
    private String t_parking;
    private String t_fee;
    private String t_common_travel;
    private String t_common_loc;
    private LocalDateTime  create_date;
    private LocalDateTime  modified_date;
    private int t_count;
	

}
