package com.travelAlone.s20230404.model.dto.km;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MypageFavoriteResponseDto {
    private long id;
    private String name;
    private String loc;
    private LocalDate modifiedDate;
    private int reviewCount;
    private int score;
    private String thumbnail;

}
