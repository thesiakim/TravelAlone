package com.travelAlone.s20230404.service.ro;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travelAlone.s20230404.dao.jh.JhDao;
import com.travelAlone.s20230404.dao.ro.RoDao;
import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.dto.ro.BoardWriteRequestDto;
import com.travelAlone.s20230404.service.jh.UploadHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoServiceImpl implements RoService {

	private final RoDao      rd;
	private final JhDao      jd;
   
	// 전체 게시판 개수
	@Override
	public int boardAllCnt() {
		log.info("roService boardAllCnt 시작");
		int boardAllCnt = rd.boardAllCnt();
		log.info("roService boardAllCnt는 "+ boardAllCnt);
      
		return boardAllCnt;
	}

	// 전체 게시판 list
	@Override
	public List<Board> listAllBoard(Board board) {
		List<Board> listBoardAll = null;
		log.info("roService listAllBoard 시작");
	      
		listBoardAll = rd.listAllBoard(board);
		log.info("roService listAllBoard listBoardAll.size()는 "+ listBoardAll.size());
	      
		return listBoardAll;
	}

	// 게시판 개수
	@Override
	public int boardCnt(Board board) {
		log.info("roService boardCnt 시작");
		int boardCnt = rd.boardCnt(board);
		log.info("roService boardCnt는 "+ boardCnt);
	      
		return boardCnt;
	}

	// 게시판 list
	@Override
	public List<Board> listBoard(Board board) {
		List<Board> listBoard = null;
		log.info("roService listBoard 시작");
	      
		listBoard = rd.listBoard(board);
		log.info("roService listBoard listBoard.size()는 "+ listBoard.size());
	      
		return listBoard;
	}
   
	// 조회수
	@Override
	public int veiwCount(int board_id) {
		log.info("roServiceImpl veiwCount Start...");
		int veiwCount = 0;
		veiwCount = rd.veiwCount(board_id);
		return veiwCount;
	}
   
	// 게시물 정보 list
	@Override
	public List<Board> detailBoard(long board_id) {
		List<Board> listBoardS = null;
		log.info("roService detailBoard 시작");
	      
		listBoardS = rd.detailBoard(board_id);
		List<String> listBoardImg = rd.detailBoardImg(board_id);
	      
		if(listBoardImg.size() > 0) {
			// listBoardS가 list형태인데 select해서 받은 값이 하나 밖에 없어서 첫번째의 0번째 인덱스의 Board.java에 이미지들을 넣기 위해 get(0) 사용
			listBoardS.get(0).setImg_stored_file(listBoardImg);         
			log.info("roService detailBoard listBoardC.size()는 "+ listBoardS.size());
		}
		return listBoardS;
	}

	// 게시물 작성
	@Override
	public int insertBoard(BoardWriteRequestDto requestDto, List<MultipartFile> files) throws Exception {
		int insertResult = 0;
		log.info("roServiceImpl insertBoard start");
	      
		long boardId = rd.insertBoard(requestDto.toBoard());
	      
		List<BodImg> bodImgs = UploadHandler.parseFileInfo(files, boardId);
	      
		insertResult = rd.insertBodImg(bodImgs);
		if (insertResult >= 1) {
			insertResult = jd.updateBoardImgYn(boardId);
		}
		log.info("roServiceImpl insertBoard insertResult는 "+ insertResult);
		return insertResult;
	}
	
	// 게시물 댓글 작성
	@Override
	public int insertReBoard(Board board) {
		int insertResult = 0;
		log.info("roService insertReBoard 시작");
		insertResult = rd.insertReBoard(board);
		log.info("roService insertReBoard insertResult는 "+ insertResult);
	      
		return insertResult;
	}

	// 게시물 삭제
	@Override
	public int deleteBoard(long board_id) {
		int delImgResult = 0;
		int delResult = 0;
		log.info("roService deleteBoard 시작");
		// 삭제할 실제 이미지 list DB에서 갖고 오기
		List<String> listDelBodImgs = rd.detailBoardImg(board_id);
		log.info("roService deleteBoard listDelBodImgs.size()는 "+ listDelBodImgs.size());
		// 실제 게시물 이미지 파일 삭제
		for (String listDelFiles : listDelBodImgs) {
			UploadHandler.delete(listDelFiles);
		}
		// DB 게시물에 있는 모든 이미지 파일 삭제
		delImgResult = rd.deleteImgBoard(board_id);
		log.info("roService deleteBoard delImgResult는 "+ delImgResult);
		// DB 게시물 게시글 삭제
		delResult = rd.deleteBoard(board_id);
		log.info("roService deleteBoard delResult는 "+ delResult);
      
		log.info("roService deleteBoard delImgResult + delResult = "+ delImgResult + delResult);
      
		return delImgResult + delResult;
	}
   
	// 댓글 및 대댓글 삭제
	@Override
	public void deleteReBoard(Board board) {
		log.info("roService deleteReBoard 시작");
		rd.deleteReBoard(board);
	}
   
	// 댓글 수정
	@Override
	public int updateReBoard(Board board) {
		int updateCount = 0;
		log.info("roService updateReBoard 시작");
      
		updateCount = rd.updateReBoard(board);
      
		return updateCount;
	}
   
	// 게시물 수정
	@Override
	public int updateBoard(Board board, List<MultipartFile> imgFiles) {
		int updateResult = 0;
		log.info("roService updateReBoard 시작");
		updateResult = rd.updateBoard(board);
		try {
			// 실제 게시글 이미지 파일 저장
			List<BodImg> bodImgs = UploadHandler.parseFileInfo(imgFiles, board.getBoard_id());
			log.info("roService updateReBoard bodImgs.size()는 "+ bodImgs.size());
			updateResult = rd.insertBodImg(bodImgs);
         
			if (updateResult >= 1) {
				updateResult = jd.updateBoardImgYn(board.getBoard_id());
			}
			log.info("roServiceImpl insertBoard insertImgResult는 "+ updateResult);
         
			} catch (Exception e) {
				e.printStackTrace();
			}
		return updateResult;
	}
   
	// 선택한 게시물 이미지 삭제
	@Override
	public int deleteImgBoard(BodImg bodImg) {
		int deleteResult = 0;
		log.info("roService deleteImgBoard 시작");
		// 삭제할 실제 이미지 DB에서 갖고 오기
		String delBoardImg = rd.selBoardImg(bodImg);
		// 실제 이미지 파일 삭제
		UploadHandler.delete(delBoardImg);
		// DB 게시물 이미지 파일 삭제
		deleteResult = rd.deleteImgOneBoard(bodImg);
		log.info("roService deleteImgBoard deleteResult는 "+ deleteResult);
		return deleteResult;
	}
      
	// 게시물 이미지 리스트
	@Override
	public List<BodImg> listImgBoard(long board_id) {
		log.info("roService listImgBoard 시작");
		List<BodImg> listBoardImg = rd.listBoardImg(board_id);
		log.info("roService listImgBoard listDelBodImgs.size()는 "+ listBoardImg.size());
		return listBoardImg;
	}
}