package com.travelAlone.s20230404.repository.si;

import java.util.List;


import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Repository
@Slf4j
public class SiRepository {
	
	private final EntityManager em;

	public List<String> autoTravelSearch(String keyword) {
		//log.info("keyword : {}", keyword);
		List<String> travel = em.createNativeQuery(
			    "SELECT DISTINCT SUBSTR(words.t_name, words.start_pos, words.end_pos - words.start_pos) as t_name_part " +
			    	    "FROM ( " +
			    	    "     SELECT t_name, LEVEL as word_num, " +
			    	    "            INSTR(' ' || t_name || ' ', ' ', 1, LEVEL) as start_pos, " +
			    	    "            INSTR(' ' || t_name || ' ', ' ', 1, LEVEL + 1) as end_pos " +
			    	    "     FROM Travel " +
			    	    "     CONNECT BY LEVEL <= LENGTH(t_name) - LENGTH(REPLACE(t_name, ' ', '')) + 1 " +
			    	    "     ) words " +
			    	    "WHERE SUBSTR(t_name, start_pos, end_pos - start_pos) LIKE '%' || :keyword || '%'")
			    	.setParameter("keyword", keyword)
			    	.getResultList();
		
		return travel;
	}

	public List<String> autoHouseSearch(String keyword) {
		
		List<String> house = em.createNativeQuery(
			    "SELECT DISTINCT SUBSTR(words.h_name, words.start_pos, words.end_pos - words.start_pos) as h_name_part "
			    + "FROM ( "
			    + "     SELECT h_name, LEVEL as word_num, "
			    + "            INSTR(' ' || h_name || ' ', ' ', 1, LEVEL) as start_pos, "
			    + "            INSTR(' ' || h_name || ' ', ' ', 1, LEVEL + 1) as end_pos "
			    + "     FROM House "
			    + "     CONNECT BY LEVEL <= LENGTH(h_name) - LENGTH(REPLACE(h_name, ' ', '')) + 1 "
			    + "     ) words "
			    + "WHERE SUBSTR(h_name, start_pos, end_pos - start_pos) LIKE '%' || :keyword || '%'")
			.setParameter("keyword", keyword)
			.getResultList();
					
		return house;
	}

	public List<String> autoResSearch(String keyword) {
		List<String> res = em.createNativeQuery(
			    "SELECT DISTINCT SUBSTR(words.r_name, words.start_pos, words.end_pos - words.start_pos) as r_name_part "
			    + "FROM ( "
			    + "     SELECT r_name, LEVEL as word_num, "
			    + "            INSTR(' ' || r_name || ' ', ' ', 1, LEVEL) as start_pos, "
			    + "            INSTR(' ' || r_name || ' ', ' ', 1, LEVEL + 1) as end_pos "
			    + "     FROM Restaurant "
			    + "     CONNECT BY LEVEL <= LENGTH(r_name) - LENGTH(REPLACE(r_name, ' ', '')) + 1 "
			    + "     ) words "
			    + "WHERE SUBSTR(r_name, start_pos, end_pos - start_pos) LIKE '%' || :keyword || '%'")
			.setParameter("keyword", keyword)
			.getResultList();
						
		return res;
	}

	
	
	

}
