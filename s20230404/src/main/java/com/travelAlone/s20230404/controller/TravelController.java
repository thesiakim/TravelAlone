package com.travelAlone.s20230404.controller;


import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Tra_Img;
import com.travelAlone.s20230404.model.Tra_Rev;
import com.travelAlone.s20230404.model.Travel;

import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.sm.TravelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TravelController {

	private final TravelService sm;
	
	// ===================여행지===================
	//여행지메인 보기
	@RequestMapping(value = "tra")
	public String notice(Travel travel , String currentPage, Model model) {
		log.info("smController Start travel...");
		int traTotal = sm.traTotal();		
		
		//페이징
		Paging page = new Paging(traTotal, currentPage);
		travel.setStart(page.getStart());
		travel.setEnd(page.getEnd());
		
		
		//여기에 게시판 코드 불러오는 코드 작성
		List<CommonCode> commonCode = sm.getCommonCode();
		log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
		model.addAttribute("boardList",commonCode);
		
		
		//지역 코드 가져오기
		List<CommonCode> commonLocCode = sm.getCommonLocCode();
		log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
		model.addAttribute("boardLocList",commonLocCode);
		
		
		
		//여행지리스트
		List<Travel> traList = sm.traList(travel);
		log.info("smController list traList.size()=>"+ traList.size());
				
		model.addAttribute("traTotal", traTotal);
		model.addAttribute("traList", traList);
		model.addAttribute("page", page);
		return "smTra/tra";
	}
	
	//여행지  정보글조회
	@GetMapping(value = "traDetail")
	public String traDetail(int tid , Model model, Tra_Img tra_Img ) {
		log.info("smController Start traDetail");
		log.info("smController traDetail travel_id->"+ tid );
		
		//여행지정보 서비스
		Travel travel = sm.traDetail(tid);
		
		//사진 리스트
		log.info("Tra_Img Start");
		tra_Img.setTravel_id(tid);
		List<Tra_Img> listImg = sm.traImgList(tra_Img);
		log.info("TravelController  listImg.size()=>"+ listImg.size());
		model.addAttribute("traImgList", listImg);

		//리뷰리스트
		List<Tra_Rev> traRevList = sm.traRevList(tid);
		log.info("TravelController list traRevList.size()=>"+ traRevList.size());
		
		model.addAttribute("travel", travel);	
		model.addAttribute("traRevList", traRevList);

		return "smTra/traDetail";
	}
	
	//정보글작성  페이지 이동
	@GetMapping(value = "traWriteForm")
	public String traWriteForm(Travel travel, Model model) {
		log.info("smController  traWriteForm Start..." );
		return "smTra/traWriteForm";
	}
	
	
	//정보글작성
		@PostMapping(value = "traWriteForm")
		public String traWrite(HttpServletRequest request,  List<MultipartFile> file1, Tra_Img tra_Img,
				Travel travel , Model model) throws Exception  {
			log.info("TravelController  traWrite Start...");
			//1. 시퀀스 가져오기 
			//여행지 시퀀스 가져오는 쿼리
			int traSeq = sm.traSeq(travel);
			log.info("TravelController traWrite traSeq->" + traSeq );
			
			//2. 가져온 시퀀스 세팅하여 house에 insert
			travel.setTravel_id(traSeq);
			int insertResult = sm.traInsert(travel);
			log.info("TravelController traWrite insertResult->"+insertResult );
			
			//3. 가져온 시퀀스 세팅하여 img insert
			//이미지 넣기
			String img_context = request.getSession().getServletContext().getRealPath("/traUpload/");
			log.info("IMG POST Start");
			//for문으로 여러파일 넣기
			for (MultipartFile multipartFile : file1) {
				log.info("originalName: {}, img_context : {}",multipartFile.getOriginalFilename(), img_context);
				String img_stored_file = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(),  img_context);
				// Service --> DB IMG CRUD
				tra_Img.setImg_original_file(multipartFile.getOriginalFilename());
				tra_Img.setImg_stored_file(img_stored_file);
				tra_Img.setTravel_id(traSeq);

				int insertImgResult = sm.traImgInsert(tra_Img);
				log.info("TravelController traImgInsert insertImgResult->"+ insertImgResult);
			}
	
			if (insertResult > 0) {
				return "redirect:tra";}
			else {
				model.addAttribute("msg","입력 실패 확인해 보세요");
				return "forward:smTra/traWriteForm";
			}
			
		}
	
		private String uploadFile(String originalName,byte[] fileData, String img_context) throws Exception {
			 UUID uid = UUID.randomUUID();
			 // requestPath = requestPath + "/resources/image";
			 log.info("img_context->" + img_context);
			 
			// Directory 생성 
			 File fileDirectory = new File(img_context);
			 if (!fileDirectory.exists()) {
				 fileDirectory.mkdirs();
				log.info("업로드용 폴더 생성" + img_context ); 
				
			}
			 String img_stored_file = uid.toString() + "_" + originalName;
			 log.info("img_stored_file ->" + img_stored_file);
			 File target = new File(img_context, img_stored_file);
			 
			 FileCopyUtils.copy(fileData,target);
			
			
			return img_stored_file;
		}
		

		//정보글수정 페이지이동
		@GetMapping(value = "traUpdateForm")
		public String traUpdateForm(int travel_id, Model model) {
			log.info("TravelController Start updateForm...");
			Travel travel = sm.traDetail(travel_id);			
			log.info("smController traUpdateForm travel->" + travel);			
			model.addAttribute("travel", travel);	
			return "smTra/traUpdateForm";
		}
		
		
		//정보글수정 처리
		@PostMapping(value = "traUpdate")
		public String traUpdate(Travel travel , Model model) {
			log.info("TravelController Start update");
			int updateCount = sm.traUpdate(travel);
			log.info("TravelController traUpdate updateCount ->" + updateCount);
			
			model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
			model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
			
			return "forward:tra";
		}	
		
		//정보 글 삭제
		@RequestMapping(value = "traDelete")
		public String traDelete(int travel_id, Model model) {
			log.info("TravelController Start delete... t_id :" +travel_id);
			int result2 = sm.traDelete(travel_id);
			int result = sm.traDelete(travel_id);			
			return "redirect:tra";
		}
		
		//여행지검색
		@RequestMapping(value = "traSearch")
		public String traSearch(Travel travel, String currentPage, Model model) {
			log.info("TravelController travelSearch Start ..." );
			
			int traTotal = sm.traSearch(travel);
			log.info("TravelController traSearch totalInquire =>" + traTotal);
			// Paging 작업
			Paging page = new Paging(traTotal, currentPage);
			
			travel.setStart(page.getStart());
			travel.setEnd(page.getEnd());
			
			List<Travel> traSearchList = sm.traSearchList(travel);
			log.info("TravelController traSearch traSearchList.size()=>" + 
					traSearchList.size());
			
			model.addAttribute("traTotal", traTotal);
			model.addAttribute("traList", traSearchList);
			model.addAttribute("page", page);
			model.addAttribute("search", travel.getKeyword());
			
			return "smTra/tra";
		}
		
		//여행지종류 구분 필터
		@GetMapping(value = "traFilter")
		public String traCodeFilter(@RequestParam(name = "code") 
		String code, Travel travel, String currentPage, Model model  ) {
			log.info("TravelController traFilter Start" );
			
			//여행지코드
			List<CommonCode> commonCode = sm.getCommonCode();
			log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
			model.addAttribute("boardList",commonCode);
			
			int traTotal = sm.traFilter(code);
			log.info("TravelController traFilter traTotal =>" + traTotal);
			
			
			//페이징
			Paging page = new Paging(traTotal, currentPage);
			travel.setStart(page.getStart());
			travel.setEnd(page.getEnd());
			travel.setCode(code);
			
			List<Travel> traOptList = sm.traOptList(travel);
			log.info("TravelController  traOptList.size()=>" + traOptList.size());
			model.addAttribute("traTotal", traTotal);
			model.addAttribute("traOptList", traOptList);
			model.addAttribute("page", page);
			model.addAttribute("search", travel.getKeyword());
									
			return "smTra/tra";
		}
		
		
		
		//지역 구분 필터
		@GetMapping(value = "traLocFilter")
		public String traLocFilter(@RequestParam(name = "code") 
		String code, Travel travel, String currentPage, Model model) {
			log.info("TravelController traLocFilter Start" );
			
			//지역 코드 가져오기
			List<CommonCode> commonLocCode = sm.getCommonLocCode();
			log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
			model.addAttribute("boardLocList",commonLocCode);
			
			
			int totalLoc = sm.traLocFilter(code);
			log.info("TravelController traLocFilter totalLoc =>" + totalLoc);
			
			
			//페이징
			Paging page = new Paging(totalLoc, currentPage);
			travel.setStart(page.getStart());
			travel.setEnd(page.getEnd());
			travel.setCode(code);
			
			List<Travel> traLocList = sm.traLocList(travel);
			log.info("TravelController  traLocList.size()=>" + traLocList.size());
			model.addAttribute("totalLoc", totalLoc);
			model.addAttribute("traLocList", traLocList);
			model.addAttribute("page", page);
			model.addAttribute("search", travel.getKeyword());
			
			return "smTra/tra";
		}
	//리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰
		
		//정보 리뷰작성  페이지 이동
		@GetMapping(value = "traRevWriteForm")
		public String traRevWriteForm (Tra_Rev tra_Rev, Model model) {
			log.info("TravelController  traRevWriteForm Start..." );	
			model.addAttribute("tra_rev", tra_Rev);
			return "smTra/traRevWriteForm";
		}
		
		//리뷰작성
		@PostMapping(value = "traRevWriteForm")
		public String traRevWrite(Tra_Rev tra_Rev, Model model) {
			log.info("TravelController  traRevWrite Start...");
			int insertResult = sm.traRevInsert(tra_Rev);
			log.info("TravelController traRevWrite insertResult->"+insertResult );
			if(insertResult >0) {
				return "redirect:traDetail?tid="+tra_Rev.getTravel_id();
			}
			else {				
				model.addAttribute("msg","입력 실패 확인해 보세요");
				return "forward:smTra/traRevWriteForm";
			}
			
		}
		//리뷰글수정 페이지이동
//		@GetMapping(value = "traRevUpdateForm")
//		public String traRevUpdateForm(@RequestParam("review_id") Optional<Integer>  review_id, Model model) {
//			log.info("TravelController  traRevUpdateForm Start..." );
//				
//			return "smTra/traRevUpdateForm";
//		}
		
		@GetMapping(value = "traRevUpdateForm")
		public String traRevUpdateForm(@RequestParam("review_id") Optional<Integer> review_id,
		                               @RequestParam(value = "travel_id", required = true) long travel_id,
		                               Model model) {
			//Required request parameter 'review_id' for method parameter type long is not present
			log.info("TravelController  traRevUpdateForm Start..." );
			
			return "smTra/traRevUpdateForm";
		}
		
		
		
		
		
		//리뷰글수정 처리
		@PostMapping(value = "traRevUpdate")
		public String traRevUpdate(@RequestParam("review_id") int review_id, Tra_Rev tra_Rev, Model model) {
		    log.info("TravelController Start update");
			int updateCount = sm.traRevUpdate(tra_Rev);
			log.info("TravelController traRevUpdate updateCount ->" + updateCount);
			
			model.addAttribute("uptCnt",updateCount);   //
			model.addAttribute("kk3","Message Test");   // 
			
			return "forward:tra";
		}	
		
		//리뷰글 삭제
		@RequestMapping(value = "traRevDelete")
		public String traRevDelete(int review_id, Model model) {
			log.info("TravelController Start delete... r_id :" + review_id);
			int result = sm.traRevDelete(review_id);
			return "redirect:tra";
		}

		
}
