package com.travelAlone.s20230404.service.sk;

import java.util.List;

import com.travelAlone.s20230404.model.Res;

public interface SkService {

	int			totalRes();
	List<Res> 	listRes(Res res);
	Res			detailRes(int rid);
	int			insertRes(Res res);
	int			updateRes(Res res);
	int			deleteRes(int res_id);
}
