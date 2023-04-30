package com.travelAlone.s20230404.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Res;
import com.travelAlone.s20230404.model.Res_Img;
import com.travelAlone.s20230404.model.Res_Rev;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.sk.SkService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SkController {

	private final SkService sk;
	
	//===================================맛집===================================
	
	// 맛집 메인
	@RequestMapping(value = "res")
	public String notice(Res res, String currentPage, Model model) {
		log.info("SkController Start res");
		int totalRes = sk.totalRes();
		
	// 페이징
		Paging page = new Paging(totalRes, currentPage);
		res.setStart(page.getStart());
		res.setEnd(page.getEnd());
	
	//게시판 코드
		List<CommonCode> commonCode = sk.getCommonCode();
		log.info("boardList data : {}, {}", commonCode.get(0).getCode(),commonCode.get(0).getValue());
		model.addAttribute("boardList,commonCode");
		
	// 지역코드
		List<CommonCode> commonLocCode = sk.getCommonLocCode();
		log.info("boardLocList data : {}, {}", commonLocCode.get(0).getCode(),commonLocCode.get(0));
		model.addAttribute("boardLocList",commonLocCode);
		
	// 맛집 목록
		List<Res> listRes = sk.listRes(res);
		log.info("SkController list listRes.size()=>"+ listRes.size());
		
		model.addAttribute("totalRes",totalRes);
		model.addAttribute("resList", listRes);
		model.addAttribute("page", page);
		return "sk/res";
	}
	
	// 맛집 정보글
	@GetMapping("resDetail")
	public String resDetail(int rid , Model model, Res_Img res_Img ) {
		log.info("SkController Start resDetail");
		log.info("SkController resDetail res_id->"+ rid );
	// 맛집 정보 서비스
		Res res = sk.detailRes(rid);
		
	// 사진 리스트
		log.info("Res_Img Start");
		res_Img.setRestaurant_id(rid);
		List<Res_Img> listImg = sk.listRes_Img(res_Img);
		log.info("SkController  listImg.size()=>"+ listImg.size());
		model.addAttribute("imgResList", listImg);
		
	// 리뷰 리스트
		List<Res_Rev> listResRev = sk.listResRev(rid);
		log.info("SkController list listResRev.size()=>"+listResRev.size());
		
		model.addAttribute("restaurant", res);
		model.addAttribute("resRevList", listResRev);
		
	return "sk/resDetail";
	}
	
	// 정보글 작성 페이지
	@GetMapping(value = "resWriteForm")
	public String resWriteForm(Res res, Model model) {
		log.info("SkController  resWriteForm Start" );
		return "sk/resWriteForm";
	}
	
//	// 정보글작성
//	@PostMapping(value = "resWriteForm")
//	public String resWrite(HttpServletRequest request,  List<MultipartFile> file1, Res_Img res_Img,
//			Res res , Model model) throws Exception  {
//		log.info("SkController  resWrite Start...");
//		// 1. 시퀀스 가져오기 
//		// 맛집 시퀀스 가져오는 쿼리
//		int resSeq = sk.seqRes(res);
//		log.info("SkController resWrite resSeq->" + resSeq );
//		
//		//2. 가져온 시퀀스 세팅하여 res에 insert
//		res.setRestaurant_id(resSeq);
//		int insertResult = sk.insertRes(res);
//		log.info("SkController resWrite insertResult->"+insertResult );
//		
//		// 3. 가져온 시퀀스 세팅하여 img insert
//		// 이미지 넣기
//		String img_context = request.getSession().getServletContext().getRealPath("/ResUpload/");
//		log.info("IMG POST Start"); 
//		for (MultipartFile multipartFile : file1) {
//			log.info("originalName: {}, img_context : {}",multipartFile.getOriginalFilename(),img_context);
//			String img_stored_file = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(),  img_context);
//			// Service --> DB IMG CRUD
//			res_Img.setImg_original_file(multipartFile.getOriginalFilename());
//			res_Img.setImg_stored_file(img_stored_file);
//			res_Img.setRestaurant_id(resSeq);
//
//			int insertImgResult = sk.insertImg(res_Img);
//			log.info("SkController insertImg insertImgResult->"+ insertImgResult);
//		}
//		
//		if (insertResult > 0) {
//			return "redirect:res";}
//		else {
//			model.addAttribute("msg","입력 실패 확인해 보세요");
//			return "forward:sk/resWriteForm";
//		}
//		
//	}
//
//	private String uploadFile(String originalName,byte[] fileData, String img_context) throws Exception {
//		 UUID uid = UUID.randomUUID();
//		 // requestPath = requestPath + "/resources/image";
//		 log.info("img_context->" + img_context);
//		 
//		// Directory 생성 
//		 File fileDirectory = new File(img_context);
//		 if (!fileDirectory.exists()) {
//			 fileDirectory.mkdirs();
//			log.info("업로드용 폴더 생성" + img_context ); 
//			
//		}
//		 String img_stored_file = uid.toString() + "_" + originalName;
//		 log.info("img_stored_file ->" + img_stored_file);
//		 File target = new File(img_context, img_stored_file);
//		 
//		 FileCopyUtils.copy(fileData,target);
//		
//		
//		return img_stored_file;
//	}
	
	// 맛집 정보글 수정 페이지
	@GetMapping(value = "resUpdateForm")
	public String resUpdateForm(int restaurant_id, Model model) {
		log.info("SkController Start updateForm");
		Res res = sk.detailRes(restaurant_id);
		log.info("SkController updateFormRes res->" + res);			
		model.addAttribute("res", res);	
		return "sk/resUpdateForm";
	}
	
	
	// 맛집 정보글 수정 처리
	@PostMapping(value = "updateRes")
	public String updateNotice(Res res , Model model) {
		log.info("SkController Start update");
		int updateCount = sk.updateRes(res);
		log.info("SkController updateRes updateCount ->" + updateCount);
		
		model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
		model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
		
		return "forward:res";
	}	
	
	// 맛집 정보글 삭제
	@RequestMapping(value = "deleteRes")
	public String deleteRes(int restaurant_id, Model model) {
		log.info("SkController Start delete... r_id :" + restaurant_id);
		int result = sk.deleteRes(restaurant_id);		
		int result2 = sk.deleteResImg(restaurant_id);		
		return "redirect:res";
	}
	
	// 맛집 검색
	@RequestMapping (value = "resSearch")
	public String resSearch(Res res, String currentPage, Model model) {
		log.info("SkController resSearch start");
		
		int totalRes = sk.conditionResCount(res);
		log.info("SkController resSearch totalInquire =>" + totalRes);
		// paging 작업
		Paging page = new Paging(totalRes, currentPage);
		
		res.setStart(page.getStart());
		res.setEnd(page.getEnd());
		
		List<Res>listSearchRes = sk.listSearchRes(res);
		log.info("SkController resSearch listSearchRes.size()=>" + listSearchRes.size());
		
		model.addAttribute("totalRes", totalRes);
		model.addAttribute("resList", listSearchRes);
		model.addAttribute("page", page);
		model.addAttribute("search", res.getKeyword());
		
		return "sk/res";
	}
	
	// 맛집 종류 구분 필터
	@GetMapping(value = "resFilter")
	public String resFilter(@RequestParam(name = "code")
	String code, Res res, String currentPage, Model model) {
		log.info("SkController resCodeFilter start");
		
	// 맛집 코드
		List<CommonCode> commonCode = sk.getCommonCode();
		log.info("boardList data : {}, {}", commonCode.get(0).getCode(),commonCode.get(0).getValue());
		model.addAttribute("boardList", commonCode);
		
		int totalRes = sk.resFilter(code);
		log.info("SkController resCodeFilter totalRes =>" + totalRes);
		
	// 페이징
		Paging page = new Paging(totalRes, currentPage);
		res.setStart(page.getStart());
		res.setEnd(page.getEnd());
		res.setCode(code);
		
		List<Res> optResList = sk.optResList(res);
		log.info("ResController  listFilterRes.size()=>" + optResList.size());
		model.addAttribute("totalRes", totalRes);
		model.addAttribute("optResList", optResList);
		model.addAttribute("page", page);
		model.addAttribute("search", res.getKeyword());
								
		return "sk/res";
	}
	
	
	// 지역 구분 필터
	@GetMapping(value = "resLocCodeFilter")
	public String locCodeFilter(@RequestParam(name = "code") 
	String code, Res res, String currentPage, Model model) {
		log.info("SkController locCodeFilter Start" );
		
		// 지역 코드
		List<CommonCode> commonLocCode = sk.getCommonLocCode();
		log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
		model.addAttribute("boardLocList",commonLocCode);
		
		
		int totalLoc = sk.conditionOptionLocCount(code);
		log.info("SkController locCodeFilter totalLoc =>" + totalLoc);
		
		
		//페이징
		Paging page = new Paging(totalLoc, currentPage);
		res.setStart(page.getStart());
		res.setEnd(page.getEnd());
		res.setCode(code);
		
		List<Res> listFilterLoc = sk.listFilterOptionLoc(res);
		log.info("SkController  listFilterLoc.size()=>" + listFilterLoc.size());
		model.addAttribute("totalLoc", totalLoc);
		model.addAttribute("resList", listFilterLoc);
		model.addAttribute("page", page);
		model.addAttribute("search", res.getKeyword());
		
		return "sk/res";
	}
	
	//===================================맛집 리뷰===================================
	
	// 리뷰 작성 페이지
	@GetMapping(value = "resRevWriteForm")
	public String resRevWriteForm (Res_Rev res_Rev, Model model) {
		log.info("SkController resRevWriteForm Start");
		model.addAttribute("res_Rev", res_Rev);
		return "sk/resRevWriteForm";
	}
	
	//리뷰 작성
	@PostMapping(value = "resRevWriteForm")
	public String resRevWrite(Res_Rev res_Rev, Model model) {
		log.info("SkController resRevWrite Start");
		int insertResult = sk.insertResRev(res_Rev);
		log.info("SkController resRevWrite insertResult->"+insertResult );
		if(insertResult >0) {
			return "redirect:resDetail?rid="+res_Rev.getRestaurant_id();
		}
		else {				
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:sk/resRevWriteForm";
		}
		
	}

	// 리뷰 수정 페이지 이동
	@GetMapping(value = "resRevUpdateForm")
	public String resRevUpdateForm(@RequestParam("review_id") Optional<Integer> review_id,
	                               @RequestParam(value = "res_id", required = true) long res_id,
	                               Model model) {
		log.info("SkController  resRevUpdateForm Start..." );
		
		return "sk/resRevUpdateForm";
	}
	
	// 리뷰 수정 처리
	@PostMapping(value = "updateResRev")
	public String updateResRev(@RequestParam("review_id") int review_id, Res_Rev res_Rev, Model model) {
		log.info("SkController Start update");
		
		int updateCount = sk.updateResRev(res_Rev);
		log.info("Skcontroller updateResRev updateCount ->" + updateCount);
		
		model.addAttribute("uptCnt",updateCount);
		model.addAttribute("kk3","Message Test");
		
		return "forward:res";
	}
	
	// 리뷰 삭제
	@RequestMapping(value = "deleteResRev")
	public String deleteResRev(int review_id, Model model) {
		log.info("SkController Start delete... n_id : " + review_id);
		int result = sk.deleteResRev(review_id);
		return "redirect:res";
	}
}