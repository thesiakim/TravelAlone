package com.travelAlone.s20230404.dao.mh;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Hou_Fav;
import com.travelAlone.s20230404.model.Hou_Img;
import com.travelAlone.s20230404.model.Hou_Rev;
import com.travelAlone.s20230404.model.House;

public interface HouseDao {

	int 					totalHouse();
	List<House> 			selectHouseList(House house);
	House 					detailHouse(int hid);
	List<Hou_Img> 			selectHouImgList(Hou_Img hou_Img);
	int 					seqHouse(House house);
	int 					insertHou(House house);
	int 					insertImg(Hou_Img hou_Img);
	int 					updateHouse(House house);
	int 					deleteHouse(int house_id);
	int 					deleteHouImg(int house_id);	
	int 					deleteHouOneImg( int house_id,int img_id);
	
	//검색
	int 					condHouseCnt(House house);
	List<House> 			houseSearchList(House house);
	
	//필터링  숙소구분
	List<CommonCode> 		getCommonCode();
	int 					condOptionInqCnt(String code);
	List<House> 			mhOptionHouseList(House house);
	//지역구분
	List<CommonCode> 		getCommonLocCode();
	List<House> 			mhOptionLocList(House house);
	int 					condOptionLocCnt(String code);
	
	//리뷰리스트가져오기
	List<Hou_Rev> 			selectHouRevList(Hou_Rev hou_Rev);
	int 					insertHouRev(Hou_Rev hou_Rev);
	int 					updateHouseRev(Hou_Rev hou_Rev);
	int 					deleteHouseRev(int review_id);
	int 					houRevDelAll(int house_id);
	
	
	//즐겨찾기
	int 					insertHouFav(Hou_Fav hou_Fav);
	int 					deleteHouFav(Hou_Fav hou_Fav);
	int 					selectHouFav(Hou_Fav hou_Fav);
	int 					totalHouRev(int hid);
	
	


	
	
	
}
