package com.travelAlone.s20230404.dao.sm;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Tra_Fav;
import com.travelAlone.s20230404.model.Tra_Img;
import com.travelAlone.s20230404.model.Tra_Rev;
import com.travelAlone.s20230404.model.Travel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TravelDaoImpl implements TravelDao {

	private final SqlSession session;
	
	
	@Override
	public int traTotal() {
		int totTravelCount = 0;
//		log.info("smDaoImpl Start total...");
		try {
			totTravelCount = session.selectOne("traTotal");
//			log.info("smDaoImpl traTotal totTravelCount->" + totTravelCount);
		} catch (Exception e) {
//			log.info("smDaoImpl traTotal Exception " +e.getMessage());
		}						
		return totTravelCount;
	}

	
	@Override
	public List<Travel> traList(Travel travel) {
		List<Travel> travelList = new ArrayList<Travel>();
		
	try {
//		log.info("smDaoImpl traList Start...");					
		travelList = session.selectList("traList",travel);
//		log.info("smDaoImpl traList End...");
	} catch (Exception e) {
//		log.info("smDaoImpl traList Exception " +e.getMessage());
	}
		return travelList;
	}

	
	//정보글 조회
	@Override
	public Travel traDetail(int tid) {
//		log.info("smDaoImpl traDetail start..");
		Travel travel = new Travel();
		try {
			travel = session.selectOne("traDetail", tid);
//			log.info("smDaoImpl traDetail travel.getT_name()->"+ travel.getT_name());
		} catch (Exception e) {
//			log.info("smDaoImpl traDetail Exception " +e.getMessage());
		}				
		return travel;
	}

	
	//정보글 작성
	@Override
	public int traInsert(Travel travel) {
		int result = 0;
//		log.info("TravelDaoImpl traInsert Start...");
		try {
			result = session.insert("traInsert",travel);
		} catch (Exception e) {
//			log.info("TravelDaoImpl traInsert Exception" + e.getMessage());
		}
		
		return result;
	}

	
	//정보글 수정
	@Override
	public int traUpdate(Travel travel) {
//		log.info("TravelDaoImpl traUpdate  start");
		int updateCount= 0;
		try {
			updateCount = session.update("traUpdate",travel);
		} catch (Exception e) {
//			log.info("TravelDaoImpl traUpdate Exception->"+e.getMessage());
		}		
		
		return updateCount;
	}

	
	//정보글 삭제
	@Override
	public int traDelete(int travel_id) {
//		log.info("TravelDaoImpl traDelete start..");
		int result = 0;
//		log.info("TravelDaoImpl traDelete travel_id->"+ travel_id);
		try {
			result = session.delete("traDelete",travel_id);
//			log.info("TravelDaoImpl traDelete result->"+ result);
		} catch (Exception e) {
//			log.info("TravelDaoImpl traDelete Exception->"+ e.getMessage());
		}
		
		return result;
	}


	//여행지 검색 결과 갯수
	@Override
	public int traSearch(Travel travel) {
		int conditionTravelCount = 0;
//		log.info("TravelDaoImpl traSearch Start...");
		try {
			conditionTravelCount = session.selectOne("traSearch",travel);
//			log.info("TravelDaoImpl conditionInquireCount->"+conditionTravelCount);		
		} catch (Exception e) {
//			log.info("TravelDaoImpl traSearch Exception"+ e.getMessage());
		}			
		return conditionTravelCount;
	}

	
	//여행지 검색 결과
	@Override
	public List<Travel> traSearchList(Travel travel) {
		List<Travel> travelSearchList = null;
//		log.info("TravelDaoImpl traSearchList start");
		try {
			travelSearchList = session.selectList("traSearchList", travel);
		} catch (Exception e) {
//			log.info("TravelDaoImpl traSearchList Exception " + e.getMessage());
		}
		
		return travelSearchList;
	}

	
	//여행지 필터 구분
	@Override
	public List<CommonCode> traCommonCode() {
//		log.info("traCommonCode 호출부 .......");
		List<CommonCode> result = session.selectList("traCommonCode");
//		log.info("traCommonCode data {},{} .......",result.get(0).getCode(),result.get(0).getValue());
		return result;
	}

	
	//여행지 필터 갯수
	@Override
	public int traFilter(String code) {
		int count = 0;		
//		log.info("TravelDaoImpl traFilter Start...");
		try {
			count = session.selectOne("traFilter",code);
//			log.info("TravelDaoImpl traFilter->"+count);		
		} catch (Exception e) {
//			log.info("TravelDaoImpl traFilter Exception"+ e.getMessage());
		}			
		return count;
	}

	
	//여행지 필터 리스트
	@Override
	public List<Travel> traOptList(Travel travel) {
		List<Travel> traOptList = null;
//		log.info("TravelDaoImpl traOptList start");
		try {
			traOptList = session.selectList("traOptList",travel);

		} catch (Exception e) {
//			log.info("TravelDaoImpl traOptList Exception " + e.getMessage());
		}

		return traOptList;
	}

	
	//지역코드 가져오기
	@Override
	public List<CommonCode> traCommonLocCode() {
//		log.info("traCommonLocCode 호출부 .......");
		List<CommonCode> result = session.selectList("traCommonLocCode");
//		log.info("traCommonLocCode data {},{} .......",result.get(0).getCode(),result.get(0).getValue());
		return result;
	}
	
	
	@Override
	public List<Travel> traLocList(Travel travel) {
		List<Travel> traLocList = null;
//		log.info("TravelDaoImpl traLocList start");
		try {
			traLocList = session.selectList("traLocList",travel);
			
		} catch (Exception e) {
//			log.info("TravelDaoImpl traLocList Exception " + e.getMessage());
		}
		
		return traLocList;
	}
	
	
	@Override
	public int traLocFilter(String code) {
		int count = 0;
//		log.info("TravelDaoImpl traLocFilter Start");
		try {
			count = session.selectOne("traLocFilter",code);
//			log.info("TravelDaoImpl traLocFilter->"+count);		
		} catch (Exception e) {
//			log.info("TravelDaoImpl Exception"+ e.getMessage());
		}
		
		return count;
	}

	
//====================================리뷰====================================	
	@Override
	public List<Tra_Rev> traRevList(Tra_Rev tra_Rev) {
		List<Tra_Rev> traRevList = new ArrayList<Tra_Rev>();
		log.info("tra_Rev.getTravel_id() ->" + tra_Rev.getTravel_id() );
		try {
//			log.info("TravelDaoImpl traRevList Start...");
			traRevList = session.selectList("traRevList", tra_Rev);
//			log.info("TravelDaoImpl traRevList End...");
		} catch (Exception e) {
//			log.info("TravelDaoImpl traRevList Exception " +e.getMessage());
		}				
		return traRevList;
	}

	
	//리뷰 작성
	@Override
	public int traRevInsert(Tra_Rev tra_Rev) {
		int result = 0;
//		log.info("TravelDaoImpl insert Start...");
		try {
			result = session.insert("traRevInsert",tra_Rev);			
		} catch (Exception e) {
//			log.info("TravelDaoImpl insert Exception" + e.getMessage());
		}
		
		return result;
	}

	
	//리뷰 수정
	@Override
	public int traRevUpdate(Tra_Rev tra_Rev) {
//		log.info("TravelDaoImpl traRevUpdate  start");
		int updateCount= 0;
		try {
			updateCount = session.update("traRevUpdate" , tra_Rev);			
		} catch (Exception e) {
//			log.info("TravelDaoImpl traRevUpdate Exception->"+e.getMessage());
		}
		return updateCount;
	}

	
	//리뷰 삭제
	@Override
	public int traRevDelete(int review_id) {
//		log.info("TravelDaoImpl delete start..");
		int result = 0;
//		log.info("TravelDaoImpl delete review_id->"+ review_id);
		try {
			result = session.delete("deleteTraRev",review_id);
//			log.info("TravelDaoImpl delete result->"+ result);
		} catch (Exception e) {
//			log.info("TravelDaoImpl delete Exception->"+ e.getMessage());
		}	
		return result;
	}

	
	//리뷰 전체삭제
	@Override
	public int traRevDelAll(int travel_id) {
//		log.info("TravelDaoImpl traRevDelAll start..");
		int result = 0;
//		log.info("TravelDaoImpl traRevDelAll travel_id->"+ travel_id);
		try {
			result = session.delete("deleteTraRevAll",travel_id);
//			log.info("TravelDaoImpl traRevDelAll result->"+ result);
		} catch (Exception e) {
//			log.info("TravelDaoImpl delete Exception->"+ e.getMessage());
		}
		return result;
	}
	
			
	@Override
	public int totalTraRev(int tid) {
		int totalTraRev = 0;
		log.info("TravelDaoImpl Start total");
		log.info("TravelDaoImpl tid->"+tid);
		try {
			totalTraRev = session.selectOne("traRevTotal",tid);
			log.info("TravelDaoImpl totalTraRev->" + totalTraRev);
		} catch (Exception e) {
			log.info("TravelDaoImpl totalTraRev Exception " +e.getMessage());
		}							
		return totalTraRev;
	}

	
//====================================이미지 삽입====================================
		//이미지 삽입
		@Override
		public int traImgInsert(Tra_Img tra_Img) {
			int result = 0;
//			log.info("TravelDaoImpl traImgInsert Start");
//			log.info("TravelDaoImpl traImgInsert tra_Img->"+ tra_Img);
			
			try {
				result = session.insert("traImgInsert",tra_Img);
			} catch (Exception e) {
//				log.info("TravelDaoImpl traImgInsert Exception->"+e.getMessage());
			}				
			return result;
		}


		@Override
		public int traSeq(Travel travel) {
			int result = 0;
//			log.info("TravelDaoImpl traSeq Start");
			try {
				result = session.selectOne("traSeq",travel);
			} catch (Exception e) {
//				log.info("TravelDaoImpl traSeq Exception->"+e.getMessage());
			}
			return result;
		}


		@Override
		public List<Tra_Img> traImgList(Tra_Img tra_Img) {
			List<Tra_Img> traImgList = new ArrayList<Tra_Img>();
			try {
//				log.info("TravelDaoImpl TraImgList Start");
				traImgList = session.selectList("traImgList" , tra_Img);
			} catch (Exception e) {
//				log.info("ImgImpl selectImgList Exception " +e.getMessage());
			}			
			return traImgList;
		}

		//이미지 삭제
		@Override
		public int traImgDelete(int travel_id) {
//			log.info("TravelDaoImpl traImgDelete start..");
			int result = 0;
//			log.info("TravelDaoImpl traImgDelete travel_id->"+ travel_id);
			try {
				result = session.delete("traImgDelete",travel_id );
			} catch (Exception e) {
//				log.info("TravelDaoImpl delete Exception->"+ e.getMessage());
			}		
			return result;
		}
		
		//이미지 개별삭제
		@Override
		public int traOneImgDelete(int travel_id, int img_id) {
			Tra_Img tra_Img =new Tra_Img();
			tra_Img.setTravel_id(travel_id);
			tra_Img.setImg_id(img_id);
			
			
//			log.info("TravelDaoImpl traOneImgDelete start");
			int result = 0;
//			log.info("TravelDaoImpl traOneImgDelete travel_id->"+ travel_id);
//			log.info("TravelDaoImpl traOneImgDelete img_id->"+ img_id);
			try {
				result = session.delete("traOneImgDelete",tra_Img );
			} catch (Exception e) {
//				log.info("TravelDaoImpl delete Exception->"+ e.getMessage());
			}
			return result;
		}

		
//====================================즐겨찾기====================================
		//즐겨찾기 추가
		@Override
		public int insertTraFav(Tra_Fav tra_Fav) {
			int result = 0;
//			log.info("TravelDaoImpl insertTraFav Start");
			try {
				result = session.insert("insertTraFav",tra_Fav);
			} catch (Exception e) {
//				log.info("TravelDaoImpl insert Exception" + e.getMessage());
			}			
			return result;
		}

		//즐겨찾기 해제
		@Override
		public int deleteTraFav(Tra_Fav tra_Fav) {
			int result = 0;
//			log.info("TravelDaoImpl deleteTraFav Start");
			try {
				result = session.delete("deleteTraFav",tra_Fav);
			} catch (Exception e) {
//				log.info("TravelDaoImpl delete Exception" + e.getMessage());
			}			
			return result;
		}


		@Override
		public int selectTraFav(Tra_Fav tra_Fav) {
//			log.info("TravelDaoImpl selectTraFav start..");
			int traFav = 0;
//			log.info("data check tra : {}, mem : {}",tra_Fav.getTravel_id(),tra_Fav.getMember_id());
			try {
				traFav = session.selectOne("isTra_Fav",tra_Fav);
			} catch (Exception e) {
//				log.info("TravelDaoImpl isTra_Fav Exception " +e.getMessage());
			}			
			return traFav;
		}


	
	
}
