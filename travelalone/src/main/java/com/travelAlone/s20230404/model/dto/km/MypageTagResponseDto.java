package com.travelAlone.s20230404.model.dto.km;

import com.travelAlone.s20230404.model.Common;
import com.travelAlone.s20230404.model.Interest;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MypageTagResponseDto {

    private List<Common> commonInterests;
    private List<Interest> savedInterests;

    public MypageTagResponseDto(List<Common> commonInterests, List<Interest> savedInterests) {
        this.commonInterests = commonInterests;
        this.savedInterests = savedInterests;
    }
}
