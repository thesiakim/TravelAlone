package com.travelAlone.s20230404.service.mh;

import java.util.List;


import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Hou_Img;
import com.travelAlone.s20230404.model.Hou_Rev;
import com.travelAlone.s20230404.model.House;

public interface HouseService {

	int 					totalHouse();
	List<House> 			listHouse(House house);
	House 					detailHouse(int hid);
	int 					insertHou(House house);
	int			 			updateHouse(House house);
	int 					deleteHouse(int house_id);
	int 					conditionHouseCount(House house);
	List<House>				 listSearchHouse(House house);
	
	//공통코드 활용(숙소종류)
	List<CommonCode> 		getCommonCode();
	int 					conditionOptionCount(String code);
	List<House> 			listFilterOptionHouse(House house);
	
	
	//공통코드 활용(지역종류)
	List<CommonCode> 		getCommonLocCode();	
	int 					conditionOptionLocCount(String code);
	List<House> 			listFilterOptionLoc(House house);
	
	//리뷰 
	List<Hou_Rev> 			listHouRev(int hid);
	int 					insertHouRev(Hou_Rev hou_Rev);
	int 					updateHouseRev(Hou_Rev hou_Rev);
	int 					deleteHouRev(int review_id);
	
	
	//이미지 업로드
	int insertImg(Hou_Img hou_Img);
	int seqHou(House house);
	
	List<Hou_Img> listHou_Img(Hou_Img hou_Img);
	int deleteHouImg(int house_id);
	
	
	

	
	
	

	
	
	
	
	
	
	
	
	
	
}
