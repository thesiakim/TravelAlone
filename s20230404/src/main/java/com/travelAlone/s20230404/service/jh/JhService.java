package com.travelAlone.s20230404.service.jh;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.Warning;
import com.travelAlone.s20230404.model.dto.ro.BoardWriteRequestDto;

public interface JhService {

	int 			updateCount(Board board);
	int 			reportMember(Warning warning);
	int   			insertBoard(BoardWriteRequestDto requestDto, List<MultipartFile> files) throws Exception;
	int				insertReLevel(Board board);

}