package com.travelAlone.s20230404.service.sm;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Hou_Img;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.model.Tra_Img;
import com.travelAlone.s20230404.model.Tra_Rev;
import com.travelAlone.s20230404.model.Travel;

public interface TravelService {

	int 					traTotal();
	List<Travel> 			traList(Travel travel);
	Travel 					traDetail(int tid);
	int 					traInsert(Travel travel);
	int			 			traUpdate(Travel travel);
	int 					traDelete(int travel_id);
	
	int 					traSearch(Travel travel);
	List<Travel>			traSearchList(Travel travel);
	
	//공통코드 활용(여행지종류)
	List<CommonCode> 		getCommonCode();
	int 					traFilter(String code);
	List<Travel> 			traOptList(Travel travel);
	
	
	//공통코드 활용(지역종류)
	List<CommonCode> 		getCommonLocCode();	
	int 					traLocFilter(String code);
	List<Travel> 			traLocList(Travel travel);
	
	
	//리뷰 
	List<Tra_Rev> 			traRevList(int tid);
	int 					traRevInsert(Tra_Rev tra_Rev);
	int 					traRevUpdate(Tra_Rev tra_Rev);
	int 					traRevDelete(int review_id);
	

	//이미지 업로드
	int 					traImgInsert(Tra_Img tra_Img);
	int 					traSeq(Travel travel);
	List<Tra_Img> 			traImgList(Tra_Img tra_Img);
	int 					traImgDelete(int travel_id);
}
