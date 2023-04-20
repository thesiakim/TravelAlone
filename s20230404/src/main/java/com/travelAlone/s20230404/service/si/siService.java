package com.travelAlone.s20230404.service.si;

import com.travelAlone.s20230404.model.si.ResultList;

public interface siService {

	ResultList search(String keyword, String category);
	void       upsertSearch(String keyword);

}
