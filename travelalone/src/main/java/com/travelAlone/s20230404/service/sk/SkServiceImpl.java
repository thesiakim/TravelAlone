package com.travelAlone.s20230404.service.sk;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.sk.SkDao;
import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Fav;
import com.travelAlone.s20230404.model.Res_Img;
import com.travelAlone.s20230404.model.Res_Rev;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkServiceImpl implements SkService {
	private final SkDao sk;

	@Override
	public int totalRestaurant() {
		log.info("SkServiceImpl start totalRes");
		int totRestaurantCnt = sk.totalRestaurant();
		log.info("SkServiceImpl totalRes totResCnt->" + totRestaurantCnt);
		return totRestaurantCnt;
	}

	@Override
	public List<Res> listRestaurant(Res restaurant) {
		log.debug("SkServiceImpl start listRestaurant");
		List<Res> resList = sk.selectRestaurantList(restaurant);
		log.debug("SkServiceImpl End listRestaurant");
		return resList;
	}

	@Override
	public Res detailRestaurant(int rid) {
		log.info("SkServiceImpl detail");
		Res restaurant = null;
		restaurant = sk.detailRestaurant(rid);
		return restaurant;
	}

	@Override
	public int insertRes(Res restaurant) {
		int result = 0;
		log.info("SkServiceImpl insert start");
		result = sk.insertRes(restaurant);
		return result;
	}

	@Override
	public int updateRestaurant(Res restaurant) {
		log.info("SkServiceImpl update");
		int updateCount = 0;
		updateCount = sk.updateRestaurant(restaurant);
		return updateCount;
	}

	@Override
	public int deleteRestaurant(int restaurant_id) {
		int result = 0;
		log.info("SkServiceImpl delete Start");
		result = sk.deleteRestaurant(restaurant_id);		
		return result;
	}

	@Override
	public int conditionRestaurantCount(Res restaurant) {
		log.info("SkServiceImpl conditionRestaurantCount start");
		int conditionRestaurantCount = sk.condRestaurantCnt(restaurant);
		log.info("SkServiceImpl conditionRestaurantCount conditionRestaurantCnt" + conditionRestaurantCount);	
		return conditionRestaurantCount;
	}

	@Override
	public List<Res> listSearchRestaurant(Res restaurant) {
		List<Res> restaurantSearchList = null;
		log.info("SkServiceImpl listSearchRestaurant start");
		restaurantSearchList = sk.restaurantSearchList(restaurant);
		log.info("SkServiceImpl listSearchRestaurant inquireRestaurantList.size()"+restaurantSearchList.size());
		return restaurantSearchList;
	}

	// 공통 맛집 코드 리스트
	@Override
	public List<CommonCode> getCommonCode() {
		List<CommonCode> result = sk.getCommonCode();
		return result;
	}

	// 맛집 종류 갯수
	@Override
	public int conditionOptionCount(String code) {
		log.info("SkServiceImpl conditionOptionCount Start");
		int conditionInquireCnt = sk.condOptionInqCnt(code);
		log.info("SkServiceImpl conditionOptionCount conditionInquireCnt" + conditionInquireCnt);
		return conditionInquireCnt;
	}

	@Override
	public List<Res> listFilterOptionRestaurant(Res restaurant) {
		List<Res> restaurantOptionFilterList = null;
		log.info("SkServiceImpl listFilterOptionRestaurant Start");
		restaurantOptionFilterList = sk.skOptionRestaurantList(restaurant);
		log.info("SkServiceImpl listFilterOptionRes resOptionFilterList.size()" + restaurantOptionFilterList.size());
		return restaurantOptionFilterList;
	}

	// 지역 코드 가져오기
	@Override
	public List<CommonCode> getCommonLocCode() {
		List<CommonCode> result = sk.getCommonLocCode();
		return result;
	}

	@Override
	public int conditionOptionLocCount(String code) {
		log.info("SkServiceImpl conditionOptionLocCount start");
		int conditionLocCnt = sk.condOptionLocCnt(code);
		log.info("SkServiceImpl conditionOptionLocCount conditionLocCnt" + conditionLocCnt);
		return conditionLocCnt;
	}

	@Override
	public List<Res> listFilterOptionLoc(Res restaurant) {
		List<Res> locOptionFilterList = null;
		log.info("SkServiceImpl listFilterOptionLoc start");
		locOptionFilterList = sk.skOptionLocList(restaurant);
		log.info("SkServiceImpl listFilterOptionLoc locOptionFilterList.size()"+locOptionFilterList.size());
		return locOptionFilterList;
	}

	@Override
	public List<Res_Rev> listResRev(int rid) {
		log.debug("SkServiceImpl Start listResRev");
		List<Res_Rev> resRevList = sk.selectResRevList(rid);
		
		log.debug("SkServiceImpl End listResRev");
		return resRevList;
	}

	@Override
	public int insertResRev(Res_Rev res_Rev) {
		int result = 0;
		log.info("SkServiceImpl insert start");
		result = sk.insertResRev(res_Rev);
		return result;
	}

	@Override
	public int updateRestaurantRev(Res_Rev res_Rev) {
		log.info("SkServiceImpl update");
		int updateCount = 0;
		updateCount = sk.updateRestaurantRev(res_Rev);
		return updateCount;
	}

	@Override
	public int deleteResRev(int review_id) {
		int result = 0;
		log.info("SkServiceImpl delete Start");
		result = sk.deleteRestaurantRev(review_id);
		return result;
	}

	@Override
	public int insertImg(Res_Img res_Img) {
		int result = 0;
		log.info("SkServiceImpl insert Start..." );
		result = sk.insertImg(res_Img);
		return result;
	}

	@Override
	public int seqRes(Res restaurant) {
		int result = 0;
		log.info("SkServiceImpl seqRes Start..." );
		result = sk.seqRestaurant(restaurant);
		return result;
	}

	@Override
	public List<Res_Img> listRes_Img(Res_Img res_Img) {
		log.info("SkServiceImpl Start listRes_Img");
		List<Res_Img> resImgList = sk.selectResImgList(res_Img);						
		return resImgList;
	}

	@Override
	public int deleteResImg(int restaurant_id) {
		int result = 0;
		log.info("SkServiceImpl deleteResImg Start");
		result = sk.deleteResImg(restaurant_id);		
		return result;
	}

	@Override
	public int deleteResOneImg(int restaurant_id, int img_id) {
		int result = 0;
		log.info("SkServiceImpl deleteResOneImg Start");
		result = sk.deleteResOneImg(restaurant_id,img_id);		
		return result;
	}

	// 맛집 즐겨찾기
	@Override
	public int insertResFav(Res_Fav res_Fav) {
		int result = 0;
		log.info("SkServiceImpl insert Start");
		result = sk.insertResFav(res_Fav);
		return result;
	}

	// 맛집 즐겨찾기 삭제
	@Override
	public int deleteResFav(Res_Fav res_Fav) {
		int result = 0;
		log.info("SkServiceImpl delete Start");
		result = sk.deleteResFav(res_Fav);
		return result;
	}

	@Override
	public int isRes_Fav(Res_Fav res_Fav) {
		log.info("SkServiceImpl isRes_Fav Start");
		int restaurantFavList = sk.selectResFav(res_Fav);				
		return restaurantFavList;
	}

	@Override
	public int deleteResRevAll(int restaurant_id) {
		int result = 0;
		log.info("SkServiceImpl deleteResRevAll Start");
		result = sk.resRevDelAll(restaurant_id);
		return result;
	}
}