package com.travelAlone.s20230404.model.dto.km;

import lombok.Data;

@Data
public class MypageReviewRequestDto {

    private long memberId;
    private int page;
    private String category;

    public MypageReviewRequestDto(long memberId, String category, int page) {
        this.memberId = memberId;
        this.page = page;
        this.category = category;
    }
}
