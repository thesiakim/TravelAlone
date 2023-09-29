package com.travelAlone.s20230404.service.si;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelAlone.s20230404.repository.si.SearchRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class SearchServiceJpa {
	
private final SearchRepository searchRepository;
	
	public List<String> autoTravelSearch(String keyword) {
		return searchRepository.autoTravelSearch(keyword);
	}


	public List<String> autoHouseSearch(String keyword) {
		return searchRepository.autoHouseSearch(keyword);
	}


	public List<String> autoResSearch(String keyword) {
		return searchRepository.autoResSearch(keyword);
	}

	
}
