package com.travelAlone.s20230404.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Score {

    private long score_id;
    private long member_id;
    private String s_common_spec;
    private LocalDate create_date;
    private LocalDate modified_date;



}
