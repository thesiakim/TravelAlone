package com.travelAlone.s20230404.dao.sm;

import java.util.List;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Tra_Fav;
import com.travelAlone.s20230404.model.Tra_Img;
import com.travelAlone.s20230404.model.Tra_Rev;
import com.travelAlone.s20230404.model.Travel;

public interface TravelDao {

	int 					traTotal();
	List<Travel> 			traList(Travel travel);
	Travel 					traDetail(int tid);
	int 					traInsert(Travel travel);
	int 					traUpdate(Travel travel);
	int 					traDelete(int travel_id);
	//검색
	int 					traSearch(Travel travel);
	List<Travel> 			traSearchList(Travel travel);
	
	
	//필터링  여행지구분
	List<CommonCode> 		traCommonCode();
	int 					traFilter(String code);
	List<Travel> 			traOptList(Travel travel);
	
	
	//지역구분
	List<CommonCode> 		traCommonLocCode();
	int 					traLocFilter(String code);
	List<Travel> 			traLocList(Travel travel);
	
	
	//리뷰리스트가져오기
	int 					totalTraRev(int tid);
	List<Tra_Rev> 			traRevList(Tra_Rev tra_Rev);
	int 					traRevInsert(Tra_Rev tra_Rev);
	int 					traRevUpdate(Tra_Rev tra_Rev);
	int 					traRevDelete(int review_id);
	int 					traRevDelAll(int travel_id);

	
	//이미지 삽입
	int 					traImgInsert(Tra_Img tra_Img);
	int 					traSeq(Travel travel);
	List<Tra_Img> 			traImgList(Tra_Img tra_Img);
	int 					traImgDelete(int travel_id);
	int 					traOneImgDelete(int travel_id, int img_id);
	int 					insertTraFav(Tra_Fav tra_Fav);
	int 					deleteTraFav(Tra_Fav tra_Fav);
	int 					selectTraFav(Tra_Fav tra_Fav);

	
	

	

	
}
