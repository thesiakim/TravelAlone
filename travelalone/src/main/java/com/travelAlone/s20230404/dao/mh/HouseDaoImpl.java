package com.travelAlone.s20230404.dao.mh;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Hou_Fav;
import com.travelAlone.s20230404.model.Hou_Img;
import com.travelAlone.s20230404.model.Hou_Rev;
import com.travelAlone.s20230404.model.House;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class HouseDaoImpl implements HouseDao {

	private final SqlSession session;
	
	
	@Override
	public int totalHouse() {
		int totHouseCount = 0;
		//log.info("HouseDaoImpl Start total...");
		try {
			totHouseCount = session.selectOne("mhHouseTotal");
			//log.info("HouseDaoImpl totalHouse totHouseCount->" + totHouseCount);
		} catch (Exception e) {
			//log.info("HouseDaoImpl totalHouse Exception " +e.getMessage());
		}						
		return totHouseCount;
	}


	@Override
	public List<House> selectHouseList(House house) {
		List<House> houseList = new ArrayList<House>();
		
	try {
		//log.info("HouseDaoImpl selectHouseList mhHouseList Start...");					
		houseList = session.selectList("mhHouseList",house);
		//log.info("HouseDaoImpl selectHouseList mhHouseList End...");
	} catch (Exception e) {
		//log.info("HouseDaoImpl mhHouseList Exception " +e.getMessage());
	}
		return houseList;
	}

	//정보글 자세히보기
	@Override
	public House detailHouse(int hid) {
		//log.info("HouseDaoImpl detail start..");
		House house = new House();
		try {
			house = session.selectOne("mhHouseSelOne", hid);
			//log.info("HouseDaoImpl detail house.getH_name()->"+ house.getH_name());
		} catch (Exception e) {
			//log.info("HouseDaoImpl mhHouseSelOne Exception " +e.getMessage());
		}				
		return house;
	}

	//정보글 작성
	@Override
	public int insertHou(House house) {
		int result = 0;
		//log.info("HouseDaoImpl insert Start...");
		try {
			result = session.insert("insertHouse",house);
		} catch (Exception e) {
			//log.info("HouseDaoImpl insert Exception" + e.getMessage());
		}
		
		return result;
	}


	@Override
	public int updateHouse(House house) {
		//log.info("HouseDaoImpl updateHouse  start");
		int updateCount= 0;
		try {
			updateCount = session.update("mhHouseUpdate",house);
		} catch (Exception e) {
			//log.info("HouseDaoImpl updateHouse Exception->"+e.getMessage());
		}		
		
		return updateCount;
	}


	@Override
	public int deleteHouse(int house_id) {
		//log.info("HouseDaoImpl delete start..");
		int result = 0;
		//log.info("HouseDaoImpl delete house_id->"+ house_id);
		try {
			result = session.delete("deleteHouse",house_id);
			//result = session.delete("deleteHouImg",house_id );
			//log.info("HouseDaoImpl delete result->"+ result);
		} catch (Exception e) {
			//log.info("HouseDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}


	//숙소검색결과갯수
	@Override
	public int condHouseCnt(House house) {
		int conditionHouseCount = 0;
		//log.info("HouseDaoImpl Start total...");
		try {
			conditionHouseCount = session.selectOne("condHouseCnt",house);
			//log.info("HouseDaoImpl conditionInquireCount->"+conditionHouseCount);		
		} catch (Exception e) {
			//log.info("HouseDaoImpl Exception"+ e.getMessage());
		}			
		return conditionHouseCount;
	}

	
	//숙소 검색결과
	@Override
	public List<House> houseSearchList(House house) {
		List<House> houseSearchList = null;
		//log.info("HouseDaoImpl houseSearchList start");
		try {
			houseSearchList = session.selectList("mhhouseSearchList", house);
		} catch (Exception e) {
			//log.info("HouseDaoImpl houseSearchList Exception " + e.getMessage());
		}
		
		return houseSearchList;
	}

	
	
	
	
	//숙소필터구분
	@Override
	public List<CommonCode> getCommonCode() {
		//log.info("getCommonCode 호출부 .......");
		List<CommonCode> result = session.selectList("houseCommonCode");
		//log.info("getCommonCode data {},{} .......",result.get(0).getCode(),result.get(0).getValue());
		return result;
	}

	//숙소 필터갯수
	@Override
	public int condOptionInqCnt(String code) {
		int count = 0;		
		//log.info("HouseDaoImpl Start total...");
		try {
			count = session.selectOne("condOptionHouseCnt",code);
			//log.info("HouseDaoImpl condOptionInquireCnt->"+count);		
		} catch (Exception e) {
			//log.info("HouseDaoImpl Exception"+ e.getMessage());
		}			
		return count;
	}

	//숙소필터
	@Override
	public List<House> mhOptionHouseList(House house) {
		List<House> houseFilterList = null;
		//log.info("HouseDaoImpl houseFilterList start");
		try {
			houseFilterList = session.selectList("mhOptionHouseList",house);

		} catch (Exception e) {
			//log.info("HouseDaoImpl houseFilterList Exception " + e.getMessage());
		}

		return houseFilterList;
	}

	//지역코드가져오기
	@Override
	public List<CommonCode> getCommonLocCode() {
		//log.info("getCommonLocCode 호출부 .......");
		List<CommonCode> result = session.selectList("locCommonCode");
		//log.info("getCommonLocCode data {},{} .......",result.get(0).getCode(),result.get(0).getValue());
		return result;
	}


	@Override
	public List<House> mhOptionLocList(House house) {
		List<House> locFilterList = null;
		//log.info("HouseDaoImpl locFilterList start");
		try {
			locFilterList = session.selectList("mhOptionLocList",house);
			
		} catch (Exception e) {
			//log.info("HouseDaoImpl locFilterList Exception " + e.getMessage());
		}
		
		return locFilterList;
	}


	
	@Override
	public int condOptionLocCnt(String code) {
		int count = 0;
		//log.info("HouseDaoImpl Start total");
		try {
			count = session.selectOne("condOptionLocCnt",code);
			//log.info("HouseDaoImpl condOptionLocCnt->"+count);		
		} catch (Exception e) {
			//log.info("HouseDaoImpl Exception"+ e.getMessage());
		}
		
		return count;
	}

	//==========리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰====================================

	@Override
	public List<Hou_Rev> selectHouRevList(Hou_Rev hou_Rev) {
		List<Hou_Rev> houRevList = new ArrayList<Hou_Rev>();
		log.info("hou_Rev.getHouse_id() ->" + hou_Rev.getHouse_id() );
		try {
			//log.info("HouseDaoImpl selectHouRevList houRevList Start...");
			houRevList = session.selectList("houRevList",hou_Rev);
			//log.info("HouseDaoImpl selectHouRevList houRevList End...");
		} catch (Exception e) {
			//log.info("HouseDaoImpl houRevList Exception " +e.getMessage());
		}				
		return houRevList;
	}


	@Override
	public int insertHouRev(Hou_Rev hou_Rev) {
		int result = 0;
		//log.info("HouseDaoImpl insert Start...");
		try {
			result = session.insert("insertHouseRev",hou_Rev);			
		} catch (Exception e) {
			//log.info("HouseDaoImpl insert Exception" + e.getMessage());
		}
		
		return result;
	}


	@Override
	public int updateHouseRev(Hou_Rev hou_Rev) {
		//log.info("HouseDaoImpl updateHouse  start");
		int updateCount= 0;
		try {
			updateCount = session.update("mhHouseRevUpdate" , hou_Rev);			
		} catch (Exception e) {
			//log.info("HouseDaoImpl updateHouseRev Exception->"+e.getMessage());
		}
		return updateCount;
	}


	@Override
	public int deleteHouseRev(int review_id) {
		//log.info("HouseDaoImpl delete start..");
		int result = 0;
		//log.info("HouseDaoImpl delete review_id->"+ review_id);
		try {
			result = session.delete("deleteHouRev",review_id);
			//log.info("HouseDaoImpl delete result->"+ result);
		} catch (Exception e) {
			//log.info("HouseDaoImpl delete Exception->"+ e.getMessage());
		}
		
		
		return result;
	}


	//이미지 디비삽입
		@Override
		public int insertImg(Hou_Img hou_Img) {
			int result = 0;
			//log.info("HouseDaoImpl insert Start");
			//log.info("HouseDaoImpl insertImg hou_Img->"+ hou_Img);
			
			try {
				result = session.insert("insertHouImg",hou_Img);
			} catch (Exception e) {
				//log.info("HouseDaoImpl insert Exception->"+e.getMessage());
			}				
			return result;
		}


		@Override
		public int seqHouse(House house) {
			int result = 0;
			//log.info("HouseDaoImpl seqHouse Start");
			try {
				result = session.selectOne("mhSeqHouse",house);
			} catch (Exception e) {
				//log.info("HouseDaoImpl mhSeqHouse Exception->"+e.getMessage());
			}
			return result;
		}


		@Override
		public List<Hou_Img> selectHouImgList(Hou_Img hou_Img) {
			List<Hou_Img> houImgList = new ArrayList<Hou_Img>();
			try {
				//log.info("HouseDaoImpl selectHouImgList houImgList Start");
				houImgList = session.selectList("mhHouImgList" , hou_Img);
			} catch (Exception e) {
				//log.info("ImgImpl selectImgList Exception " +e.getMessage());
			}
			
			return houImgList;
		}


		@Override
		public int deleteHouImg(int house_id) {
			//log.info("HouseDaoImpl deleteHouImg start");
			int result = 0;
			//log.info("HouseDaoImpl deleteHouImg house_id->"+ house_id);
			try {
				result = session.delete("deleteHouImg",house_id );
			} catch (Exception e) {
				//log.info("HouseDaoImpl delete Exception->"+ e.getMessage());
			}
			
			return result;
		}


		@Override
		public int deleteHouOneImg(int house_id,int img_id) {
			Hou_Img hou_Img =new Hou_Img();
			hou_Img.setHouse_id(house_id);
			hou_Img.setImg_id(img_id);
			
			
			//log.info("HouseDaoImpl deleteHouOneImg start");
			int result = 0;
			//log.info("HouseDaoImpl deleteHouImg house_id->"+ house_id);
			//log.info("HouseDaoImpl deleteHouImg img_id->"+ img_id);
			try {
				result = session.delete("deleteHouOneImg",hou_Img );
			} catch (Exception e) {
			//	log.info("HouseDaoImpl delete Exception->"+ e.getMessage());
			}
			return result;
		}

		//즐겨찾기
		@Override
		public int insertHouFav(Hou_Fav hou_Fav) {
			int result = 0;
			//log.info("HouseDaoImpl insertHouFav Start");
			try {
				result = session.insert("insertHouFav",hou_Fav);
			} catch (Exception e) {
			//	log.info("HouseDaoImpl insert Exception" + e.getMessage());
				
			}
			return result;
		}

		//즐겨찾기 해제
		@Override
		public int deleteHouFav(Hou_Fav hou_Fav) {
			int result = 0;
			//log.info("HouseDaoImpl deleteHouFav Start");
			try {
				result = session.delete("deleteHouFav",hou_Fav);
			} catch (Exception e) {
			//	log.info("HouseDaoImpl delete Exception" + e.getMessage());
				
			}
			return result;
		}


		@Override
		public int selectHouFav(Hou_Fav hou_Fav) {
			//log.info("HouseDaoImpl selectHouFav start..");
			int houFav = 0;
			//log.info("data check hou : {}, mem : {}",hou_Fav.getHouse_id(),hou_Fav.getMember_id());
			try {
				houFav = session.selectOne("isHou_Fav",hou_Fav);
			} catch (Exception e) {
				//log.info("HouseDaoImpl isHou_Fav Exception " +e.getMessage());
			}
			
			return houFav;
		}


		@Override
		public int houRevDelAll(int house_id) {
			//log.info("HouseDaoImpl houRevDelAll start..");
			int result = 0;
			//log.info("HouseDaoImpl houRevDelAll house_id->"+ house_id);
			try {
				result = session.delete("deleteHouRevAll" ,house_id);
			//	log.info("HouseDaoImpl houRevDelAll result->"+ result);
			} catch (Exception e) {
				//log.info("HouseDaoImpl delete Exception->"+ e.getMessage());				
			}
									
			return result;
		}


		@Override
		public int totalHouRev(int hid) {
			int totalHouRev = 0;
			log.info("HouseDaoImpl Start total");
			log.info("HouseDaoImpl hid->"+hid);
			try {
				totalHouRev = session.selectOne("mhHouRevTotal",hid);
				log.info("HouseDaoImpl totalHouRev totalHouRev->" + totalHouRev);
			} catch (Exception e) {
				log.info("HouseDaoImpl totalHouRev Exception " +e.getMessage());
			}							
			return totalHouRev;
		}

		
		

	
	
}
