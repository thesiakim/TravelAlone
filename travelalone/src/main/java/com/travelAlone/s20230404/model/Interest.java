package com.travelAlone.s20230404.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Interest {
    private long interest_id;
    private long member_id;
    private String i_common_interest;
    private LocalDate create_date;
    private LocalDate modified_date;

    private String word;


}
