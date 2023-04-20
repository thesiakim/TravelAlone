package com.travelAlone.s20230404.model.si;

import java.util.List;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Travel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ResultList {
	
	private List<House> houseList;
	private List<Travel> travelList;
	private List<Board> boardlList;
	private List<Res> restaurantlList;
	private List<Board> boardList;
	
}
