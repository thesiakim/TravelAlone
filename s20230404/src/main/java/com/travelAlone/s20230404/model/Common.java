package com.travelAlone.s20230404.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Common {

    private String bcd;
    private int mcd;
    private String word;
    private LocalDate create_Date;
    private LocalDate modified_Date;
    private boolean savedChecked;
}
