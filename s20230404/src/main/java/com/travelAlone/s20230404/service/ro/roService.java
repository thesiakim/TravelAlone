package com.travelAlone.s20230404.service.ro;

import java.util.List;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.dto.ro.BoardWriteRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface roService {

	int boardAllCnt();

	List<Board> listAllBoard(Board board);

	int boardCnt(Board board);

	List<Board> listBoard(Board board);
	
	List<Board> detailBoard(int board_id);

	int insertBoard(BoardWriteRequestDto requestDto, List<MultipartFile> files) throws Exception;

	int insertReBoard(Board board);
	
}
