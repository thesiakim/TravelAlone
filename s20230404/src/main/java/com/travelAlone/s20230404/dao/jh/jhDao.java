package com.travelAlone.s20230404.dao.jh;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.Warning;

public interface jhDao {

	int 				updateCount(Board board);
	int 				reportMember(Warning warning);

}
