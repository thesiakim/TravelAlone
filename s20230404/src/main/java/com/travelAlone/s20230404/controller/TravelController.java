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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Tra_Fav;
import com.travelAlone.s20230404.model.Tra_Img;
import com.travelAlone.s20230404.model.Tra_Rev;
import com.travelAlone.s20230404.model.Travel;

import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.si.SiService;
import com.travelAlone.s20230404.service.sm.TravelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TravelController {

	private final TravelService sm;
	private final SiService SiService;
	
//===============================여행지===============================
	//여행지메인 보기
	@RequestMapping(value = "tra")
	public String travel(@LoginUser MemberJpa memberJpa,   Travel travel , String currentPage, Model model) {
//		log.info("TravelController Start travel...");
		int traTotal = sm.traTotal();		
		
		//페이징
		Paging page = new Paging(traTotal, currentPage);
		travel.setStart(page.getStart());
		travel.setEnd(page.getEnd());
		
		
		//boardList
		//여기에 게시판 코드 불러오는 코드 작성
		List<CommonCode> traCommonCode = sm.traCommonCode();
//		log.info("boardTraList data : {}, {}",traCommonCode.get(0).getCode(),traCommonCode.get(0).getValue());
		model.addAttribute("boardTraList",traCommonCode);
		
		
		//boardLocList
		//지역 코드 가져오기
		List<CommonCode> traCommonLocCode = sm.traCommonLocCode();
//		log.info("commonLocCode data : {}, {}",traCommonLocCode.get(0).getCode(),traCommonLocCode.get(0).getValue());
		model.addAttribute("traCommonLocCode",traCommonLocCode);
		
		
		//인기 여행지
		List<Travel> popularTravel = SiService.getPopularTravel();
		model.addAttribute("popularTravel", popularTravel);
		
		
		//여행지 리스트
		List<Travel> traList = sm.traList(travel);
//		log.info("smController list traList.size()=>"+ traList.size());
				
		  if (memberJpa != null) {
//				 log.info("TravelController travel memberJpa.getId()는 "+ memberJpa.getId()); 
//				 log.info("TravelController travel memberJpa.getId()는 "+ memberJpa.getRole()); 
				 model.addAttribute("user_id", memberJpa.getId());
				 model.addAttribute("user_role", memberJpa.getRole());
				 
				  }
			
		model.addAttribute("traTotal", traTotal);
		model.addAttribute("traList", traList);
		model.addAttribute("page", page);
		return "smTra/tra";
	}
	
//===============================READ===============================		
	//여행지 정보글조회
	@GetMapping(value = "traDetail")
	public String traDetail(@LoginUser MemberJpa memberJpa, String currentPage,
			int tid , Model model, Tra_Img tra_Img,Tra_Fav tra_Fav, Tra_Rev tra_Rev) {
//		log.info("TravelController Start traDetail");
//		log.info("TravelController traDetail travel_id->"+ tid );
		
		
		//여행지 정보 서비스
		Travel travel = sm.traDetail(tid);
		
		
		//사진 리스트
//		log.info("Tra_Img Start");
		tra_Img.setTravel_id(tid);
		List<Tra_Img> listImg = sm.traImgList(tra_Img);
//		log.info("TravelController  listImg.size()=>"+ listImg.size());
		model.addAttribute("traImgList", listImg);

		
		//즐겨찾기 테이블 넣기
//		log.info("isTra_Fav Start");
//		log.info("isTra_Fav tid"+ tid);		
		
		
		//변수 추가
		int isfavTra  = 0;
		int favResult = 0;
		if (memberJpa != null) {				
//		log.info("isTra_Fav memberJpa.getId()-> " + memberJpa.getId());			
//		log.info("isTra_Fav memberJpa.getRole-> " + memberJpa.getRole());			
		tra_Fav.setTravel_id(tid);
		tra_Fav.setMember_id(memberJpa.getId());
		tra_Fav.setIsfavTra(isfavTra);		
		favResult = sm.isTra_Fav(tra_Fav);
//		log.info("TravelController favResult=>{}", favResult);
		 model.addAttribute("user_id", memberJpa.getId());	
		 model.addAttribute("user_role", memberJpa.getRole());
		
		}
		model.addAttribute("isfavTra", favResult);
		
			
		//리뷰갯수
		int totalTraRev = sm.totalTraRev(tid);	
		
		//페이징
		Paging page = new Paging(totalTraRev, currentPage);
		tra_Rev.setStart(page.getStart());
		tra_Rev.setEnd(page.getEnd());
		log.info("tra_Rev.getStart()"+ tra_Rev.getStart());
		log.info("tra_Rev.getEnd()"+ tra_Rev.getEnd());
		tra_Rev.setTravel_id(tid);
		
		
		//리뷰리스트
		List<Tra_Rev> traRevList = sm.traRevList(tra_Rev);
		log.info("TravelController list traRevList.size()=>"+ traRevList.size());
		
		model.addAttribute("page", page);
		model.addAttribute("travel", travel);	
		model.addAttribute("traRevList", traRevList);

		return "smTra/traDetail";
	}
	

//===============================CREATE===============================	
	//정보글작성  페이지 이동
	@GetMapping(value = "traWriteForm")
	public String traWriteForm(@LoginUser MemberJpa memberJpa,Travel travel, Model model) {
//		log.info("TravelController  traWriteForm Start..." );
		  if(memberJpa != null) {
//		         log.info("TravelController traWriteForm memberJpa.getId()는 "+ memberJpa.getId());
		         model.addAttribute("user_id", memberJpa.getId());
		        
		         return "smTra/traWriteForm";
		  
		  }else {
	    	  return  "km/login";
	      }		 	
	}
	
	
	//정보글작성
		@PostMapping(value = "traWriteForm")
		public String traWrite(HttpServletRequest request,  List<MultipartFile> file1, Tra_Img tra_Img,
				Travel travel , Model model) throws Exception  {
//			log.info("TravelController  traWrite Start...");
			//1. 시퀀스 가져오기 
			//여행지 시퀀스 가져오는 쿼리
			int traSeq = sm.traSeq(travel);
//			log.info("TravelController traWrite traSeq->" + traSeq );
			
			//2. 가져온 시퀀스 세팅하여 house에 insert
			travel.setTravel_id(traSeq);
			int insertResult = sm.traInsert(travel);
//			log.info("TravelController traWrite insertResult->"+insertResult );
			
			//3. 가져온 시퀀스 세팅하여 img insert
			//이미지 넣기
			String img_context = "images"+File.separator+"traUpload" + File.separator;
			
//			log.info("IMG POST Start");
			//for문으로 여러파일 넣기
			
			for (MultipartFile multipartFile : file1) {
//				log.info("originalName: {}, img_context : {}",multipartFile.getOriginalFilename(), img_context);
				String img_stored_file = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(),  img_context);
				
				// Service --> DB IMG CRUD
				tra_Img.setImg_original_file(multipartFile.getOriginalFilename());
				tra_Img.setImg_stored_file(img_stored_file);
				tra_Img.setTravel_id(traSeq);

				int insertImgResult = sm.traImgInsert(tra_Img);
//				log.info("TravelController traImgInsert insertImgResult->"+ insertImgResult);
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
//			 log.info("img_context->" + img_context);
			 
			// Directory 생성 
			 File fileDirectory = new File(img_context);
			 if (!fileDirectory.exists()) {
				 fileDirectory.mkdirs();
//				log.info("업로드용 폴더 생성" + img_context ); 
				
			}
			 String img_stored_file = img_context + uid.toString() + "_" + originalName;
//			 log.info("img_stored_file ->" + img_stored_file);
			 File target = new File(img_stored_file);
			 
			 FileCopyUtils.copy(fileData,target);
			 return img_stored_file;
		}
		

//===============================UPDATE===============================			
		//정보글 수정 페이지 이동
		@GetMapping(value = "traUpdateForm")
		public String traUpdateForm(int travel_id, Model model, Tra_Img tra_Img) {
//			log.info("TravelController Start updateForm...");
			Travel travel = sm.traDetail(travel_id);			
//			log.info("smController traUpdateForm travel->" + travel);			
		
			//사진 리스트
//			log.info("Tra_Img Start");
			tra_Img.setTravel_id(travel_id);
			List<Tra_Img> traImgList = sm.traImgList(tra_Img);
//			log.info("TravelController  traImgList.size()=>"+ traImgList.size());
			model.addAttribute("traImgList", traImgList);
			
			model.addAttribute("travel", travel);	
			return "smTra/traUpdateForm";
		}
		
		
		//정보글 수정 처리
		@PostMapping(value = "traUpdate")
		public String traUpdate(Travel travel , Model model,
				HttpServletRequest request,  List<MultipartFile> file1, Tra_Img tra_Img
				) throws Exception {
				
			//이미지 삽입
			String img_context = "images"+File.separator+"traUpload" + File.separator;
//			log.info("IMG POST Start");
			
			for (MultipartFile multipartFile : file1) {
//			log.info("originalName: {}, img_context : {}",multipartFile.getOriginalFilename(),img_context);
			String img_stored_file = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(),  img_context);
			// Service --> DB IMG CRUD
			tra_Img.setImg_original_file(multipartFile.getOriginalFilename());
			tra_Img.setImg_stored_file(img_stored_file);


			int traImgInsertResult = sm.traImgInsert(tra_Img);
//			log.info("TravelController traImgInsert traImgInsertResult->"+ traImgInsertResult);
		}
			
			
//			log.info("TravelController Start update");
			int updateCount = sm.traUpdate(travel);
//			log.info("TravelController traUpdate updateCount ->" + updateCount);
			
			
			
			model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
			model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
			
			return "forward:tra";
		}	
		
//===============================DELETE===============================		
		//정보 글 삭제
		@RequestMapping(value = "traDelete")
		public String traDelete(int travel_id, Model model) {
//			log.info("TravelController Start delete... travel_id :" +travel_id);
			int result2 = sm.traImgDelete(travel_id);
			int result3 = sm.traRevDelAll(travel_id);
			int result = sm.traDelete(travel_id);			
			return "redirect:tra";
		}
		

		//정보글 수정 시 사진만 삭제
		@ResponseBody
		@RequestMapping(value = "deleteTraImg")
		public String deleteTraImg(int travel_id, int img_id, Model model) {
//			log.info("TravelController Start delete travel_id :" + travel_id);
//			log.info("TravelController Start delete img_id :" + img_id);
			int result = sm.traOneImgDelete(travel_id, img_id);
			String resultStr = Integer.toString(result);
			return resultStr;
		}
	
		
//===============================SEARCH===============================			
		//여행지 검색
		@RequestMapping(value = "traSearch")
		public String traSearch(Travel travel, String currentPage, Model model) {
//			log.info("TravelController travelSearch Start ..." );
			
			int traTotal = sm.traSearch(travel);
//			log.info("TravelController traSearch totalInquire =>" + traTotal);
			// Paging 작업
			Paging page = new Paging(traTotal, currentPage);
			
			travel.setStart(page.getStart());
			travel.setEnd(page.getEnd());
			
			List<Travel> traSearchList = sm.traSearchList(travel);
//			log.info("TravelController traSearch traSearchList.size()=>" + traSearchList.size());
			
			model.addAttribute("traTotal", traTotal);
			model.addAttribute("traList", traSearchList);
			model.addAttribute("page", page);
			model.addAttribute("search", travel.getKeyword());
			
			return "smTra/tra";
		}
		
		
		//여행지종류 구분 필터
		@GetMapping(value = "traCodeFilter")
		public String traCodeFilter(@RequestParam(name = "code") 
		String code, Travel travel, String currentPage, Model model  ) {
//			log.info("TravelController traCodeFilter Start" );
			
			
			//boardList
			//여행지코드
			List<CommonCode> traCommonCode = sm.traCommonCode();
//			log.info("boardTraList data : {}, {}",traCommonCode.get(0).getCode(),traCommonCode.get(0).getValue());
			model.addAttribute("boardTraList",traCommonCode);
			
			
			//boardLocList
			//지역 코드
			List<CommonCode> traCommonLocCode = sm.traCommonLocCode();
//			log.info("traCommonLocCode data : {}, {}",traCommonLocCode.get(0).getCode(),traCommonLocCode.get(0).getValue());
			model.addAttribute("traCommonLocCode",traCommonLocCode);
			
			int traTotal = sm.traFilter(code);
//			log.info("TravelController traFilter traTotal =>" + traTotal);
			
			
			//페이징
			Paging page = new Paging(traTotal, currentPage);
			travel.setStart(page.getStart());
			travel.setEnd(page.getEnd());
			travel.setCode(code);
			
			List<Travel> traOptList = sm.traOptList(travel);
//			log.info("TravelController  traOptList.size()=>" + traOptList.size());
			model.addAttribute("traTotal", traTotal);
			model.addAttribute("traList", traOptList);
			model.addAttribute("page", page);
			model.addAttribute("search", travel.getKeyword());
									
			return "smTra/tra";
		}

		
		//지역 구분 필터
		@GetMapping(value = "traLocFilter")
		public String traLocFilter(@RequestParam(name = "code") 
		String code, Travel travel, String currentPage, Model model) {
//			log.info("TravelController traLocFilter Start" );
			
			
			//여행지코드
			List<CommonCode> traCommonCode = sm.traCommonCode();
//			log.info("boardTraList data : {}, {}",traCommonCode.get(0).getCode(),traCommonCode.get(0).getValue());
			model.addAttribute("boardTraList",traCommonCode);

			
			//boardLocList
			//지역 코드 가져오기
			List<CommonCode> traCommonLocCode = sm.traCommonLocCode();
//			log.info("traCommonLocCode data : {}, {}",traCommonLocCode.get(0).getCode(),traCommonLocCode.get(0).getValue());
			model.addAttribute("traCommonLocCode",traCommonLocCode);
			
			
			int totalLoc = sm.traLocFilter(code);
//			log.info("TravelController traLocFilter totalLoc =>" + totalLoc);
			
			
			//페이징
			Paging page = new Paging(totalLoc, currentPage);
			travel.setStart(page.getStart());
			travel.setEnd(page.getEnd());
			travel.setCode(code);
			
			List<Travel> traLocList = sm.traLocList(travel);
//			log.info("TravelController  traLocList.size()=>" + traLocList.size());
			model.addAttribute("totalLoc", totalLoc);
			model.addAttribute("traList", traLocList);
			model.addAttribute("page", page);
			model.addAttribute("search", travel.getKeyword());
			
			return "smTra/tra";
		}
	
		
//===============================REVIEW===============================	
		//정보 리뷰작성  페이지 이동
		@GetMapping(value = "traRevWriteForm")
		public String traRevWriteForm (@LoginUser MemberJpa memberJpa,Tra_Rev tra_Rev, Model model) {
//			log.info("TravelController  traRevWriteForm Start..." );	
		
			
			  if(memberJpa != null) {
			
//			log.info("TravelController traRevWriteForm memberJpa.getId()는 "+ memberJpa.getId());
			model.addAttribute("user_id", memberJpa.getId());
			model.addAttribute("tra_rev", tra_Rev);
			
			return "smTra/traRevWriteForm";
			
			
			  }else {
		    	  return  "km/login";
		      }	
			
			
		}
		
		
		//리뷰작성
		@PostMapping(value = "traRevWriteForm")
		public String traRevWrite(@LoginUser MemberJpa memberJpa,
				Tra_Rev tra_Rev, Model model) throws Exception {
//			log.info("TravelController  traRevWrite Start...");
			
			  if (memberJpa == null){
			         throw new Exception("로그인 해주세요!");
			      }
			
//			  log.info("TravelController writeFormTraRev memberJpa.getId()는 "+ memberJpa.getId());
			  tra_Rev.setMember_id( memberJpa.getId());
			  
			  
			int traRevInsertResult = sm.traRevInsert(tra_Rev);
//			log.info("TravelController traRevInsert Result->"+traRevInsertResult );
			
			
			if(traRevInsertResult >0) {
				return "redirect:traDetail?tid="+tra_Rev.getTravel_id();
			}
			else {				
				model.addAttribute("msg","입력 실패 확인해 보세요");
				return "forward:smTra/traRevWriteForm";
			}
			
		}
		
		
		//리뷰글 수정 페이지 이동
		@GetMapping(value = "traRevUpdateForm")
		public String traRevUpdateForm(@RequestParam("review_id") Optional<Integer> review_id,
		                               @RequestParam(value = "travel_id", required = true) long travel_id,
		                               Model model) {
			//Required request parameter 'review_id' for method parameter type long is not present
//			log.info("TravelController  traRevUpdateForm Start..." );
			
			return "smTra/traRevUpdateForm";
		}

		
		//리뷰글 수정 처리
		@PostMapping(value = "traRevUpdate")
		public String traRevUpdate(@RequestParam("review_id") int review_id, Tra_Rev tra_Rev, Model model) {
//		    log.info("TravelController Start update");
			
		    int updateCount = sm.traRevUpdate(tra_Rev);
//			log.info("TravelController traRevUpdate updateCount ->" + updateCount);
			
			model.addAttribute("uptCnt",updateCount);   //
			model.addAttribute("kk3","Message Test");   // 
			
			return "forward:tra";
		}	
		
		//리뷰글 삭제
		@RequestMapping(value = "traRevDelete")
		public String traRevDelete(int review_id, Model model) {
//			log.info("TravelController Start delete... review_id :" + review_id);
			int result = sm.traRevDelete(review_id);
			return "redirect:tra";
		}

		
//===============================FAVORITE===============================		
		//즐겨찾기 추가
		@ResponseBody
		@RequestMapping(value = "insertTraFav")
		public String traFav(@LoginUser MemberJpa memberJpa,
				Tra_Fav tra_Fav, Model model) throws Exception {
//			log.info("TravelController  tra_Fav Start");
			if (memberJpa == null){
		         throw new Exception("로그인 해주세요!");
		      }
//			 log.info("TravelController tra_Fav memberJpa.getId()는 "+ memberJpa.getId());
//			 log.info("TravelController tra_Fav tra_Fav.getTravel_id()는 "+ tra_Fav.getTravel_id());
			tra_Fav.setMember_id(memberJpa.getId());
			tra_Fav.setTravel_id(tra_Fav.getTravel_id());
	
			int insertResult = sm.insertTraFav(tra_Fav);
//			log.info("TravelController tra_Fav insertResult->"+insertResult );
		
			
			return "smTra/tra";
		}
		
		
		//즐겨찾기 해제
		@RequestMapping(value = "deleteTraFav")
		public String deleteTraFav(@LoginUser MemberJpa memberJpa,Tra_Fav tra_Fav, Model model) throws Exception {
//			log.info("TravelController  deleteTraFav Start");
			if (memberJpa == null){
		         throw new Exception("로그인 해주세요!");
		      }
			tra_Fav.setMember_id(memberJpa.getId());
//			log.info("TravelController traFav memberJpa.getId()는 "+ memberJpa.getId());
//			log.info("TravelController Start delete getTravel_id :" + tra_Fav.getTravel_id());
			
			tra_Fav.setMember_id(memberJpa.getId());
			tra_Fav.setTravel_id(tra_Fav.getTravel_id());
			
			int result = sm.deleteTraFav(tra_Fav);
			return  "smTra/tra";
		}
		


		
}
