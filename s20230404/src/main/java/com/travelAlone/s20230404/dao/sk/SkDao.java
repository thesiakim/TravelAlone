package com.travelAlone.s20230404.dao.sk;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Fav;
import com.travelAlone.s20230404.model.Res_Img;
import com.travelAlone.s20230404.model.Res_Rev;

public interface SkDao {
	
	int						totalRestaurant();
	List<Res>				selectRestaurantList(Res restaurant);
	Res						detailRestaurant(int rid);
	int						insertRes(Res restaurant);
	int						updateRestaurant(Res restaurant);
	int						deleteRestaurant(int restaurant_id);
	
	// 검색
	int						condRestaurantCnt(Res restaurant);
	List<Res> 				restaurantSearchList(Res restaurant);
	
	// 필터링 맛집 구분
	List<CommonCode>		getCommonCode();
	int						condOptionInqCnt(String code);
	List<Res>				skOptionRestaurantList(Res restaurant);
	
	// 지역 구분
	List<CommonCode> 		getCommonLocCode();
	List<Res>				skOptionLocList(Res restaurant);
	int						condOptionLocCnt(String code);
	
	// 리뷰 리스트 가져오기
	List<Res_Rev>			selectResRevList(int rid);
	int						insertResRev(Res_Rev res_Rev);
	int						updateRestaurantRev(Res_Rev res_Rev);
	int						deleteRestaurantRev(int review_id);
	
	// 이미지 삽입
	int 					insertImg(Res_Img res_Img);
	int 					seqRestaurant(Res restaurant);
	List<Res_Img> 			selectResImgList(Res_Img res_Img);
	int 					deleteResImg(int restaurant_id);
	int						deleteResOneImg(int restaurant_id,int img_id);
	int 					resRevDelAll(int restaurant_id);

	//즐겨찾기
	int 					insertResFav(Res_Fav res_Fav);
	int 					deleteResFav(Res_Fav res_Fav);
	int 					selectResFav(Res_Fav res_Fav);
	
}