package com.travelAlone.s20230404.dao.sk;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Fav;
import com.travelAlone.s20230404.model.Res_Img;
import com.travelAlone.s20230404.model.Res_Rev;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SkDaoImpl implements SkDao {

	private final SqlSession session;
	
	// 맛집 정보글 갯수 조회
	@Override
	public int totalRestaurant() {
		int totRestaurantCount = 0;
		log.info("SkDaoImpl Start total");
		try {
			totRestaurantCount = session.selectOne("skRestaurantTotal");
			log.info("SkDaoImpl totalRestaurant totRestaurantCount->" + totRestaurantCount);
		} catch (Exception e) {
			log.info("SkDaoImpl totalRestaurant Exception" + e.getMessage());
		}
		return totRestaurantCount;
	}

	// 맛집 정보글 리스트 조회
	@Override
	public List<Res> selectRestaurantList(Res restaurant){
		List<Res> restaurantList = new ArrayList<Res>();
		
	try {
		log.info("SkDaoImpl selectRestaurantList SkRestaurantList start");
		restaurantList = session.selectList("skRestaurantList", restaurant);
		log.info("SkDaoImpl selectRestaurantList SkRestaurantList End");
	} catch (Exception e) {
		log.info("SkDaoImpl SkRestaurantList Exception" + e.getMessage());
	}
		return restaurantList;
	}

	// 맛집 정보글 상세페이지
	@Override
	public Res detailRestaurant(int rid) {
		log.info("SkDaoImpl detail start");
		Res restaurant = new Res();
		try {
			restaurant = session.selectOne("skRestaurantSelOne", rid);
			
			log.info("SkDaoImpl detail restaurant.getR_name"+restaurant.getR_name());
		} catch (Exception e) {
			log.info("SkDaoImpl SkRestaurantSelOne Exception" + e.getMessage());
		}
		return restaurant;
	}
	
	// 맛집 정보글 작성
	@Override
	public int insertRes(Res restaurant) {
		int result = 0;
		log.info("SkDaoImpl insert Start");
		try {
			result = session.insert("insertRestaurant",restaurant);
		} catch (Exception e) {
			log.info("SkDaoImpl insert Exception" + e.getMessage());
		}
		
		return result;
	}
	
	// 맛집 정보글 수정
	@Override
	public int updateRestaurant(Res restaurant) {
		log.info("SkDaoImpl updateRestaurant  start");
		int updateCount= 0;
		try {
			updateCount = session.update("skRestaurantUpdate",restaurant);
		} catch (Exception e) {
			log.info("SkDaoImpl updateRestaurant Exception->"+e.getMessage());
		}		
		
		return updateCount;
	}

	// 맛집 정보글 삭제
	@Override
	public int deleteRestaurant(int restaurant_id) {
		log.info("SkDaoImpl delete start");
		int result = 0;
		log.info("SkDaoImpl delete restaurant_id->"+ restaurant_id);
		try {
			result = session.delete("deleteRestaurant",restaurant_id);
			log.info("SkDaoImpl delete result->"+ result);
		} catch (Exception e) {
			log.info("SkDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}
	
	// 맛집 검색 횟수
	@Override
	public int condRestaurantCnt(Res restaurant) {
		int conditionRestaurantCount = 0;
		log.info("SkDaoImpl Start total");
		try {
			conditionRestaurantCount = session.selectOne("condRestaurantCnt", restaurant);
			log.info("SkDaoImpl conditionInquireCount->"+conditionRestaurantCount);
		} catch (Exception e) {
			log.info("SkDaoImpl Exception"+e.getMessage());
		}
		return conditionRestaurantCount;
	}

	// 맛집 검색 결과
	@Override
	public List<Res> restaurantSearchList(Res restaurant) {
		List<Res> restaurantSearchList = null;
		log.info("SkDaoImpl restaurantSearchList start");
		try {
			restaurantSearchList = session.selectList("skrestaurantSearchList", restaurant);
		} catch (Exception e) {
			log.info("SkDaoImpl resSearchList Exception " + e.getMessage());
		}
		return restaurantSearchList;
	}

	// 맛집 필터 구분
	@Override
	public List<CommonCode> getCommonCode() {
		log.info("getCommonCode 호출부");
		List<CommonCode> result = session.selectList("restaurantCommonCode");
		log.info("getCommonCode data {},{}",result.get(0).getCode(),result.get(0).getValue());
		return result;
	} 

	// 맛집 필터 갯수
	@Override
	public int condOptionInqCnt(String code) {
		int count = 0;
		log.info("SkDaoImpl start total");
		try {
			count = session.selectOne("condOptionRestaurantCnt",code);
			log.info("SkDaoImpl condOptionInquireCnt->"+count);
		} catch (Exception e) {
			log.info("SkDaoImpl Exception"+e.getMessage());
		}
		return count;
	}

	// 맛집 필터
	@Override
	public List<Res> skOptionRestaurantList(Res restaurant) {
		List<Res> restaurantFilterList = null;
		log.info("SkDaoImpl restaurantFilterList start");
		try {
			restaurantFilterList = session.selectList("skOptionRestaurantList",restaurant);
		} catch (Exception e) {
			log.info("SkDaoImpl resFilterList Exception" + e.getMessage());
		}
		return restaurantFilterList;
	}

	// 지역 코드 가져오기
	@Override
	public List<CommonCode> getCommonLocCode() {
		log.info("getCommonLocCode 호출부");
		List<CommonCode> result = session.selectList("locCommonCode");
		log.info("getCommonLocCode data {},{}",result.get(0).getCode(),result.get(0).getValue());
		return result;
	}

	@Override
	public List<Res> skOptionLocList(Res restaurant) {
		List<Res> locFilterList = null;
		log.info("SkDaoImpl locFilterList start");
		try {
			locFilterList = session.selectList("skOptionLocList",restaurant);
		} catch (Exception e) {
			log.info("SkDaoImpl locFilterList Exception" + e.getMessage());
		}
		return locFilterList;
	}

	@Override
	public int condOptionLocCnt(String code) {
		int count = 0;
		log.info("SkDaoImpl start total");
		try {
			count = session.selectOne("condOptionLocResCnt",code);
			log.info("SkDaoImpl condOptionLocCnt->"+count);
		} catch (Exception e) {
			log.info("SkDaoImpl Exception"+ e.getMessage());
		}
		return count;
	}

	// 리뷰
	@Override
	public List<Res_Rev> selectResRevList(int rid) {
		List<Res_Rev> resRevList = new ArrayList<Res_Rev>();
		try {
			log.info("SkDaoImpl selectResRevList resRevList start");
			resRevList = session.selectList("resRevList",rid);
			log.info("SkDaoImpl selectResRevList resRevList End");
		} catch (Exception e) {
			log.info("SkDaoImpl resRevList Exception"+e.getMessage());
		}
		return resRevList;
	}

	@Override
	public int insertResRev(Res_Rev res_Rev) {
		int result = 0;
		log.info("SkDaoImpl insert Start");
		try {
			result = session.insert("insertRestaurantRev",res_Rev);
		} catch (Exception e) {
			log.info("SkDaoImpl insert Exception"+e.getMessage());
		}
		return result;
	}

	@Override
	public int updateRestaurantRev(Res_Rev res_Rev) {
		log.info("SkDaoImpl updateRes start");
		int updateCount=0;
		try {
			updateCount = session.update("skRestaurantRevUpdate", res_Rev);
		} catch (Exception e) {
			log.info("SkDaoImpl updateResRev Exception->"+e.getMessage());
		}
		return updateCount;
	}

	@Override
	public int deleteRestaurantRev(int review_id) {
		log.info("SkDaoImpl delete start");
		int result = 0;
		log.info("SkDaoImpl delete review_id->"+review_id);
		try {
			result = session.delete("deleteResRev",review_id);
			log.info("SkDaoImpl delete result->"+result);
		} catch(Exception e) {
			log.info("SkDaoImpl delete Exception->"+e.getMessage());
		}
		return result;
	}

	// 이미지 DB 삽입
	@Override
	public int insertImg(Res_Img res_Img) {
		int result = 0;
		log.info("SkDaoImpl insert Start");
		log.info("SkDaoImpl insertImg res_Img->"+ res_Img);
		
		try {
			result = session.insert("insertResImg",res_Img);
		} catch (Exception e) {
			log.info("SkDaoImpl insert Exception->"+e.getMessage());
		}				
		return result;	
	}

	@Override
	public int seqRestaurant(Res restaurant) {
		int result = 0;
		log.info("SkDaoImpl seqRestaurant Start");
		try {
			result = session.selectOne("skSeqRestaurant",restaurant);
		} catch (Exception e) {
			log.info("SkDaoImpl skSeqRestaurant Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<Res_Img> selectResImgList(Res_Img res_Img) {
		List<Res_Img> resImgList = new ArrayList<Res_Img>();
		try {
			log.info("SkDaoImpl selectResImgList resImgList Start");
			resImgList = session.selectList("skResImgList" , res_Img);
		} catch (Exception e) {
			log.info("ImgImpl selectImgList Exception " +e.getMessage());
		}
		
		return resImgList;
	}

	@Override
	public int deleteResImg(int restaurant_id) {
		log.info("SkDaoImpl deleteResImg start");
		int result = 0;
		log.info("SkDaoImpl deleteResImg res_id->"+ restaurant_id);
		try {
			result = session.delete("deleteResImg",restaurant_id );
		} catch (Exception e) {
			log.info("SkDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}

	@Override
	public int deleteResOneImg(int restaurant_id, int img_id) {
		Res_Img res_Img = new Res_Img();
		res_Img.setRestaurant_id(restaurant_id);
		res_Img.setImg_id(img_id);
		
		
		log.info("SkDaoImpl deleteResOneImg start");
		int result = 0;
		log.info("SkDaoImpl deleteResImg restaurant_id->"+ restaurant_id);
		log.info("SkDaoImpl deleteResImg img_id->"+ img_id);
		try {
			result = session.delete("deleteResOneImg",res_Img );
		} catch (Exception e) {
			log.info("SkDaoImpl delete Exception->"+ e.getMessage());
		}
		return result;
	}

	// 즐겨찾기
	@Override
	public int insertResFav(Res_Fav res_Fav) {
		int result = 0;
		log.info("SkDaoImpl insertResFav Start");
		try {
			result = session.insert("insertResFav",res_Fav);
		} catch (Exception e) {
			log.info("SkDaoImpl insert Exception" + e.getMessage());
			
		}
		return result;
	}

	// 즐겨찾기 삭제
	@Override
	public int deleteResFav(Res_Fav res_Fav) {
		int result = 0;
		log.info("SkDaoImpl deleteResFav Start");
		try {
			result = session.delete("deleteResFav",res_Fav);
		} catch (Exception e) {
			log.info("SkDaoImpl delete Exception" + e.getMessage());
			
		}
		return result;
	}

	@Override
	public int selectResFav(Res_Fav res_Fav) {
		log.info("SkDaoImpl selectResFav start");
		int resFav = 0;
		log.info("data check res : {}, mem : {}",res_Fav.getRestaurant_id(),res_Fav.getMember_id());
		try {
			resFav = session.selectOne("isRes_Fav",res_Fav);
		} catch (Exception e) {
			log.info("SkDaoImpl isRes_Fav Exception " +e.getMessage());
		}
		
		return resFav;
	}

	@Override
	public int resRevDelAll(int restaurant_id) {
		log.info("SkDaoImpl resRevDelAll start..");
		int result = 0;
		try {
			result = session.delete("deleteResRevAll" ,restaurant_id);
			log.info("SkDaoImpl deleteResRevAll result->"+ result);
			
		} catch (Exception e) {
			log.info("SkDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}
	
}
