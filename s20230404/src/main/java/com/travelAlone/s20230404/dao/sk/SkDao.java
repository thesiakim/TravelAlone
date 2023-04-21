package com.travelAlone.s20230404.dao.sk;

import java.util.List;

import com.travelAlone.s20230404.model.Res;

public interface SkDao {
	
	int					totalRes();
	List<Res>			selectResList(Res res);
	Res					detailRes(int rid);
	int					insertRes(Res res);
	int					updateRes(Res res);
	int					deleteRes(int res_id);

}