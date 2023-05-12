package com.travelAlone.s20230404.model.dto.km;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MypageInterestUpdateRequestDto {

    private long id;
    private List<String> savedTagIds = new ArrayList<>();


}
