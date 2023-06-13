package com.travelAlone.s20230404.service.mh;

import java.util.List;


import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Hou_Fav;
import com.travelAlone.s20230404.model.Hou_Img;
import com.travelAlone.s20230404.model.Hou_Rev;
import com.travelAlone.s20230404.model.House;

public interface HouseService {

	int 					totalHouse();
	List<House> 			listHouse(House house);
	House 					detailHouse(int hid);
	List<Hou_Img> 			listHou_Img(Hou_Img hou_Img);
	int 					seqHou(House house);
	int 					insertHou(House house);
	int 					insertImg(Hou_Img hou_Img);
	int			 			updateHouse(House house);
	int 					deleteHouse(int house_id);
	int 					deleteHouImg(int house_id);
	int 					deleteHouOneImg(int house_id,int img_id );
	int 					deleteHouRevAll(int house_id);
	int 					conditionHouseCount(House house);
	List<House>				listSearchHouse(House house);
	
	//공통코드 활용(숙소종류)
	List<CommonCode> 		getCommonCode();
	int 					conditionOptionCount(String code);
	List<House> 			listFilterOptionHouse(House house);
	
	
	//공통코드 활용(지역종류)
	List<CommonCode> 		getCommonLocCode();	
	int 					conditionOptionLocCount(String code);
	List<House> 			listFilterOptionLoc(House house);
	
	//리뷰 
	List<Hou_Rev> 			listHouRev(Hou_Rev hou_Rev);
	int 					insertHouRev(Hou_Rev hou_Rev);
	int 					updateHouseRev(Hou_Rev hou_Rev);
	int 					deleteHouRev(int review_id);
	
	

	//즐겨찾기
	int 					insertHouFav(Hou_Fav hou_Fav);
	int 					deleteHouFav(Hou_Fav hou_Fav);	
	int 					isHou_Fav(Hou_Fav hou_Fav);
	
	int 					totalHouRev(int hid);
	
	
	
	
	
	
	

	
	
	

	
	
	
	
	
	
	
	
	
	
}
