package com.travelAlone.s20230404.service.jh;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.ro.roDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class jhServiceImpl implements jhService {
	
	private final roDao		rd;
}
