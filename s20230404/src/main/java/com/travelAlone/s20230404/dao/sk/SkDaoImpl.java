package com.travelAlone.s20230404.dao.sk;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Res;
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
	public int totalRes() {
		int totResCount = 0;
		log.info("SkDaoImpl Start total");
		try {
			totResCount = session.selectOne("resTotal");
			log.info("SkDaoImpl totalRes totresCount->" + totResCount);
		} catch (Exception e) {
			log.info("SkDaoImpl totalRes Exception" + e.getMessage());
		}
		return totResCount;
	}

	// 맛집 정보글 리스트 조회
	@Override
	public List<Res> selectResList(Res res){
		List<Res>resList = new ArrayList<Res>();
		
	try {
		log.info("SkDaoImpl selectResList SkresList start");
		resList = session.selectList("resList", res);
		log.info("SkDaoImpl selectResList SkResList End");
	} catch (Exception e) {
		log.info("SkDaoImpl SkResList Exception" + e.getMessage());
	}
		return resList;
	}

	// 맛집 정보글 상세페이지
	@Override
	public Res detailRes(int rid) {
		log.info("SkDaoImpl detail start");
		Res res = new Res();
		try {
			res = session.selectOne("resSelOne", rid);
			
			log.info("SkDaoImpl detail res.getR_name"+res.getR_name());
		} catch (Exception e) {
			log.info("SkDaoImpl SkResSelOne Exception" + e.getMessage());
		}
		return res;
	}
	
	// 맛집 정보글 작성
	@Override
	public int insertRes(Res res) {
		int result = 0;
		log.info("SkDaoImpl insert Start");
		try {
			result = session.insert("insertRes",res);
		} catch (Exception e) {
			log.info("SkDaoImpl insert Exception" + e.getMessage());
		}
		
		return result;
	}
	
	// 맛집 정보글 수정
	@Override
	public int updateRes(Res res) {
		log.info("SkDaoImpl updateRes  start");
		int updateCount= 0;
		try {
			updateCount = session.update("resUpdate",res);
		} catch (Exception e) {
			log.info("SkDaoImpl updateRes Exception->"+e.getMessage());
		}		
		
		return updateCount;
	}

	// 맛집 정보글 삭제
	@Override
	public int deleteRes(int res_id) {
		log.info("SkDaoImpl delete start");
		int result = 0;
		log.info("SkDaoImpl delete res_id->"+ res_id);
		try {
			result = session.delete("deleteRes",res_id);
			log.info("SkDaoImpl delete result->"+ result);
		} catch (Exception e) {
			log.info("SkDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}
	
	// 맛집 검색 횟수
	@Override
	public int condResCnt(Res res) {
		int conditionResCount = 0;
		log.info("SkDaoImpl Start total");
		try {
			conditionResCount = session.selectOne("condResCnt", res);
			log.info("SkDaoImpl conditionInquireCount->"+conditionResCount);
		} catch (Exception e) {
			log.info("SkDaoImpl Exception"+e.getMessage());
		}
		return conditionResCount;
	}

	// 맛집 검색 결과
	@Override
	public List<Res> resSearchList(Res res) {
		List<Res> resSearchList = null;
		log.info("SkDaoImpl resSearchList start");
		try {
			resSearchList = session.selectList("resSearchList", res);
		} catch (Exception e) {
			log.info("SkDaoImpl resSearchList Exception " + e.getMessage());
		}
		return resSearchList;
	}

	// 맛집 필터 구분
	@Override
	public List<CommonCode> getCommonCode() {
		log.info("getCommonCode 호출부");
		List<CommonCode> result = session.selectList("resCommonCode");
		log.info("getCommonCode data {},{}",result.get(0).getCode(),result.get(0).getValue());
		return result;
	} 

	// 맛집 필터 갯수
	@Override
	public int resFilter(String code) {
		int count = 0;
		log.info("SkDaoImpl start total");
		try {
			count = session.selectOne("resFilter",code);
			log.info("SkDaoImpl condOptionInquireCnt->"+count);
		} catch (Exception e) {
			log.info("SkDaoImpl Exception"+e.getMessage());
		}
		return count;
	}

	// 맛집 필터
	@Override
	public List<Res> optResList(Res res) {
		List<Res> resFilterList = null;
		log.info("SkDaoImpl resFilterList start");
		try {
			resFilterList = session.selectList("optResList",res);
		} catch (Exception e) {
			log.info("SkDaoImpl resFilterList Exception" + e.getMessage());
		}
		return resFilterList;
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
	public List<Res> optionLocList(Res res) {
		List<Res> locFilterList = null;
		log.info("SkDaoImpl locFilterList start");
		try {
			locFilterList = session.selectList("optionLocList",res);
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
			count = session.selectOne("condOptionLocCnt",code);
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
			result = session.insert("insertResRev",res_Rev);
		} catch (Exception e) {
			log.info("SkDaoImpl insert Exception"+e.getMessage());
		}
		return result;
	}

	@Override
	public int updateResRev(Res_Rev res_Rev) {
		log.info("SkDaoImpl updateRes start");
		int updateCount=0;
		try {
			updateCount = session.update("resRevUpdate", res_Rev);
		} catch (Exception e) {
			log.info("SkDaoImpl updateResRev Exception->"+e.getMessage());
		}
		return updateCount;
	}

	@Override
	public int deleteResRev(int review_id) {
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
			result = session.insert("insertImg",res_Img);
		} catch (Exception e) {
			log.info("SkDaoImpl insert Exception->"+e.getMessage());
		}				
		return result;	
	}

	@Override
	public int seqRes(Res res) {
		int result = 0;
		log.info("SkDaoImpl seqRes Start");
		try {
			result = session.selectOne("SeqRes",res);
		} catch (Exception e) {
			log.info("SkDaoImpl SeqRes Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<Res_Img> selectResImgList(Res_Img res_Img) {
		List<Res_Img> resImgList = new ArrayList<Res_Img>();
		try {
			log.info("SkDaoImpl selectResImgList resImgList Start");
			resImgList = session.selectList("resImgList" , res_Img);
		} catch (Exception e) {
			log.info("ImgImpl selectImgList Exception " +e.getMessage());
		}
		
		return resImgList;
	}

	@Override
	public int deleteResImg(int res_id) {
		log.info("SkDaoImpl deleteResImg start");
		int result = 0;
		log.info("SkDaoImpl deleteResImg res_id->"+ res_id);
		try {
			result = session.delete("deleteResImg",res_id );
		} catch (Exception e) {
			log.info("SkDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}
}
