package com.travelAlone.s20230404.service.ro;

import java.util.List;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.dto.ro.BoardWriteRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface roService {

	int 				boardAllCnt();
	List<Board> 		listAllBoard(Board board);
	int 				boardCnt(Board board);
	List<Board> 		listBoard(Board board);
	List<Board> 		detailBoard(int board_id);
	int 				insertReBoard(Board board);
	int 				veiwCount(int board_id);
	int 				deleteBoard(long board_id);
	void				deleteReBoard(Board board);
	int					updateReBoard(Board board);
	
}
