package com.travelAlone.s20230404.service.sk;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Img;
import com.travelAlone.s20230404.model.Res_Rev;

public interface SkService {

	int					totalRes();
	List<Res> 			listRes(Res res);
	Res					detailRes(int rid);
	int					insertRes(Res res);
	int					updateRes(Res res);
	int					deleteRes(int res_id);
	int					conditionResCount(Res res);
	List<Res>			listSearchRes(Res res);
	
	List<CommonCode> 	getCommonCode();
	int				 	resFilter(String code);
	List<Res>			optResList(Res res);
	
	List<CommonCode>	getCommonLocCode();
	int					conditionOptionLocCount(String code);
	List<Res>			listFilterOptionLoc(Res res);
	
	List<Res_Rev>		listResRev(int rid);
	int					insertResRev(Res_Rev res_Rev);
	int					updateResRev(Res_Rev res_Rev);
	int					deleteResRev(int review_id);
	
	int insertImg(Res_Img res_Img);
	int seqRes(Res res);
	
	List<Res_Img> listRes_Img(Res_Img res_Img);
	int deleteResImg(int restaurant_id);
}
