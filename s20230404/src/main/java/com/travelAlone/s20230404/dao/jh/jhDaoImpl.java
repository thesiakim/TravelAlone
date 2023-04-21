package com.travelAlone.s20230404.dao.jh;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class jhDaoImpl implements jhDao {
	
	private final SqlSession	session;
	
}
