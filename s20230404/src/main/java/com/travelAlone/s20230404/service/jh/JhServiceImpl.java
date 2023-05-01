package com.travelAlone.s20230404.service.jh;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travelAlone.s20230404.dao.jh.JhDao;
import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.BodImg;
import com.travelAlone.s20230404.model.Warning;
import com.travelAlone.s20230404.model.dto.ro.BoardWriteRequestDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class JhServiceImpl implements JhService {
	private final JhDao jd;

	// 추천 버튼
	@Override
	public int updateCount(Board board) {
		log.info("jhServiceImpl b_Like_Cnt start...");
		int updateCount = 0;
		updateCount = jd.updateCount(board);
		return updateCount;
	}

	// 신고 버튼
	@Override
	public int reportMember(Warning warning) {
		log.info("jhServiceImpl reportMember start...");
		int reportMember = 0;
		int updateResult = 0;
		
		reportMember = jd.reportMember(warning);
		updateResult = jd.reportUpdate(warning);
		
		return reportMember + updateResult;
	}
	
	// 이미지 삽입
	@Override
	public int insertBoard(BoardWriteRequestDto requestDto, List<MultipartFile> files) throws Exception {
		int insertResult = 0;
		log.info("jhServiceImpl insertBoard start");

		long boardId = jd.insertBoard(requestDto.toBoard());

		List<BodImg> bodImgs = UploadHandler.parseFileInfo(files, boardId);

		insertResult = jd.insertBodImg(bodImgs);
		if (insertResult >= 1) {
			insertResult = jd.updateBoardImgYn(boardId);
		}
		log.info("jhServiceImpl insertBoard insertResult는 "+ insertResult);
		
		return insertResult;
	}
	
	// 대댓글 작성
	@Override
	public int insertReLevel(Board board) {
		log.info("jhServiceImpl insertReLevel start");
		int insertReLevel = jd.insertReLevel(board);
		
		return insertReLevel;
	}
	

}