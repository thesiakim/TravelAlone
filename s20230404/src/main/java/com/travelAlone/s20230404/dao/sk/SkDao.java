package com.travelAlone.s20230404.dao.sk;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Img;
import com.travelAlone.s20230404.model.Res_Rev;

public interface SkDao {
	
	int					totalRes();
	List<Res>			selectResList(Res res);
	Res					detailRes(int rid);
	int					insertRes(Res res);
	int					updateRes(Res res);
	int					deleteRes(int res_id);
	
	// 검색
	int					condResCnt(Res res);
	List<Res> 			resSearchList(Res res);
	
	// 필터링 맛집 구분
	List<CommonCode>	getCommonCode();
	int					resFilter(String code);
	List<Res>			optResList(Res res);
	
	// 지역 구분
	List<CommonCode> 	getCommonLocCode();
	List<Res>			optionLocList(Res res);
	int					condOptionLocCnt(String code);
	
	// 리뷰 리스트 가져오기
	List<Res_Rev>		selectResRevList(int rid);
	int					insertResRev(Res_Rev res_Rev);
	int					updateResRev(Res_Rev res_Rev);
	int					deleteResRev(int review_id);
	
	//이 미지 삽입
	int 					insertImg(Res_Img res_Img);
	int 					seqRes(Res res);
	List<Res_Img> selectResImgList(Res_Img res_Img);
	int deleteResImg(int res_id);

}