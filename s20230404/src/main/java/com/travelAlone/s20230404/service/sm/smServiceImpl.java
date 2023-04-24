package com.travelAlone.s20230404.service.sm;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.sm.smDao;
import com.travelAlone.s20230404.model.Travel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class smServiceImpl implements smService {
	private final smDao sm;

	@Override
	public int totalTravel() {
		log.info("smServiceImpl start totalTravel ");
		int totTravelCnt = sm.totalTravel();
		log.info("smServiceImpl  totalTravel totTravelCnt->" + totTravelCnt );		
		return totTravelCnt;
	}

	@Override
	public List<Travel> listTravel(Travel travel) {
		log.debug("smServiceImpl Start listTravel...");
		List<Travel> travelList = sm.selectTravelList(travel);
		log.debug("smServiceImpl End listTravel...");
		return travelList;
	}

	@Override
	public Travel detailTravel(int tid) {
		log.info("smServiceImpl detail");
		Travel travel = null;
		travel = sm.detailTravel(tid);		
		return travel;
	}

	@Override
	public int insertTravel(Travel travel) {
		int result = 0;
		log.info("smServiceImpl insert Start...");
		result = sm.insertTravel(travel);
		return result;
	}

	@Override
	public int updateTravel(Travel travel) {
		log.info("smServiceImpl update");
		int updateCount = 0;
		updateCount = sm.updateTravel(travel);		
		return updateCount;
	}

	@Override
	public int deleteTravel(int travel_id) {
		int result = 0;
		log.info("smServiceImpl delete Start");
		result = sm.deleteTravel(travel_id);		
		return result;
	}
		
}
