package com.travelAlone.s20230404.model.dto.km;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MypageReviewResponseDto {

    private long review_id;
    private long house_id;
    private long restaurant_id;
    private long travel_id;
    private int r_score;
    private String r_content;
    private LocalDate create_date;
    private LocalDate modified_date;

}
