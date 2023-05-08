package com.travelAlone.s20230404.service.sk;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Fav;
import com.travelAlone.s20230404.model.Res_Img;
import com.travelAlone.s20230404.model.Res_Rev;

public interface SkService {

	int					totalRestaurant();
	List<Res> 			listRestaurant(Res restaurant);
	Res					detailRestaurant(int rid);
	int					insertRes(Res restaurant);
	int					updateRestaurant(Res restaurant);
	int					deleteRestaurant(int restaurant_id);
	int					conditionRestaurantCount(Res restaurant);
	List<Res>			listSearchRestaurant(Res restaurant);
	
	List<CommonCode> 	getCommonCode();
	int				 	conditionOptionCount(String code);
	List<Res>			listFilterOptionRestaurant(Res restaurant);
	
	List<CommonCode>	getCommonLocCode();
	int					conditionOptionLocCount(String code);
	List<Res>			listFilterOptionLoc(Res restaurant);
	
	List<Res_Rev>		listResRev(int rid);
	int					insertResRev(Res_Rev res_Rev);
	int					updateRestaurantRev(Res_Rev res_Rev);
	int					deleteResRev(int review_id);
	int 				deleteResRevAll(int restaurant_id);
	
	int					insertImg(Res_Img res_Img);
	int					seqRes(Res restaurant);
	
	List<Res_Img>		listRes_Img(Res_Img res_Img);
	int					deleteResImg(int restaurant_id);
	
	int					deleteResOneImg(int restaurant_id,int img_id );
	
	int 				insertResFav(Res_Fav res_Fav);
	int 				deleteResFav(Res_Fav res_Fav);	
	int 				isRes_Fav(Res_Fav res_Fav);
	
}
