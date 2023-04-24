package com.travelAlone.s20230404.service.jh;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.Warning;

public interface jhService {

	int 			updateCount(Board board);
	int 			reportMember(Warning warning);

}
