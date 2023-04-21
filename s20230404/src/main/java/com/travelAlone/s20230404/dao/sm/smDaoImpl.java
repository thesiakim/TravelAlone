package com.travelAlone.s20230404.dao.sm;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.Travel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class smDaoImpl implements smDao {

	private final SqlSession session;
	
	
    @Override
    public int totalTravel() {
    	int totTravelCount = 0;
    	log.info("smDaoImpl Start total...");
    	try {
    		totTravelCount =session.selectOne("smTravelTotal");
    		log.info("smDaoImpl totalTravel totTravelCount->" + totTravelCount);
		} catch (Exception e) {
			log.info("smDaoImpl totalTravel Exception " +e.getMessage());
		}						
		return totTravelCount;
	}

    
    @Override
    public List<Travel> selectTravelList(Travel travel) {
		List<Travel> travelList = new ArrayList<Travel>();
		
	try {
		log.info("smDaoImpl selectTravelList smTravelList Start...");					
		travelList = session.selectList("smTravelList", travel);
		log.info("smDaoImpl selectTravelList smTravelList End...");
	} catch (Exception e) {
		log.info("smDaoImpl smTravelList Exception " +e.getMessage());
	}
		return travelList;
	}

  //정보글 자세히보기
  	@Override
  	public Travel detailTravel(int tid) {
  		log.info("smDaoImpl detail start..");
  		Travel travel = new Travel();
  		try {
  			travel = session.selectOne("smTravelSelOne", tid);
  			log.info("smDaoImpl detail Travel.getT_name()->"+ travel.getT_name());
  		} catch (Exception e) {
  			log.info("smDaoImpl smTravelSelOne Exception " +e.getMessage());
  		}				
  		return travel;
  	}


	@Override
	public int insertTravel(Travel travel) {
		int result = 0;
		log.info("smDaoImpl insert Start...");
		try {
			result = session.insert("insertTravel",travel);
		} catch (Exception e) {
			log.info("smDaoImpl insert Exception" + e.getMessage());
		}
		
		return result;
	}


	@Override
	public int updateTravel(Travel travel) {
		log.info("smDaoImpl updateTravel  start");
		int updateCount= 0;
		try {
			updateCount = session.update("smTravelUpdate",travel);
		} catch (Exception e) {
			log.info("smDaoImpl updateTravel Exception->"+e.getMessage());
		}		
		
		return updateCount;
	}


	@Override
	public int deleteTravel(int travel_id) {
		log.info("smDaoImpl delete start..");
		int result = 0;
		log.info("smDaoImpl delete travel_id->"+ travel_id);
		try {
			result = session.delete("deleteTravel",travel_id);
			log.info("smDaoImpl delete result->"+ result);
		} catch (Exception e) {
			log.info("smDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}

	
	
}
