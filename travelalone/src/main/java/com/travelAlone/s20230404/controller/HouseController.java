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
import com.travelAlone.s20230404.model.Hou_Fav;
import com.travelAlone.s20230404.model.Hou_Img;
import com.travelAlone.s20230404.model.Hou_Rev;
import com.travelAlone.s20230404.model.House;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.mh.HouseService;
import com.travelAlone.s20230404.service.si.SiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HouseController {

private final HouseService mh;
private final SiService SiService;
	
	// ===================숙소===================
	//숙소 메인 보기
	@RequestMapping(value = "hou")
	public String house(@LoginUser MemberJpa memberJpa, House house , String currentPage, Model model) {
		//log.info("HouseController Start house");
		int totalHouse = mh.totalHouse();		
		
		//페이징
		Paging page = new Paging(totalHouse, currentPage);
		house.setStart(page.getStart());
		house.setEnd(page.getEnd());
		
		
		//여기에 게시판 코드 불러오는 코드 작성
		List<CommonCode> commonCode = mh.getCommonCode();
		//log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
		model.addAttribute("boardList",commonCode);
		
		
		//지역 코드도 가져와볼까나
		List<CommonCode> commonLocCode = mh.getCommonLocCode();
		//log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
		model.addAttribute("boardLocList",commonLocCode);
		
		//popularHouse
		List<House> popularHouse = SiService.getPopularHouse();    
		model.addAttribute("popularHouse", popularHouse);
		
		//숙소리스트
		List<House> listHouse = mh.listHouse(house);
		//log.info("HouseController list listHouse.size()=>"+ listHouse.size());
		
		  if (memberJpa != null) {
				 //log.info("HouseController house memberJpa.getId()는 "+ memberJpa.getId()); 
				// log.info("HouseController house memberJpa.getId()는 "+ memberJpa.getRole()); 
				 model.addAttribute("user_id", memberJpa.getId());
				 model.addAttribute("user_role", memberJpa.getRole());
				 
				  }
		
		
		
		model.addAttribute("totalHouse", totalHouse);
		model.addAttribute("houseList", listHouse);
		model.addAttribute("page", page);
		return "mhHou/hou";
	}
	
	//숙소   정보글조회
	@GetMapping(value = "houDetail")
	public String houseDetail(@LoginUser MemberJpa memberJpa,String currentPage,
			int hid , Model model , Hou_Img hou_Img ,Hou_Fav hou_Fav,Hou_Rev hou_Rev) {
		log.info("HouseController Start houseDetail");
		log.info("HouseController houseDetail house_id->"+ hid );
		//숙소정보 서비스
		House house = mh.detailHouse(hid);

		//사진 리스트
		log.info("Hou_Img Start");
		hou_Img.setHouse_id(hid);
		List<Hou_Img> listImg = mh.listHou_Img(hou_Img);
		log.info("HouseController  listImg.size()=>"+ listImg.size());
		model.addAttribute("imgHouList", listImg);
		
		//즐겨찾기테이블 넣기
		log.info("isHou_Fav Start");		
		log.info("isHou_Fav hid"+ hid);		
		
		//변수추가
		int isfavHou = 0;
		int favResult = 0;
		if (memberJpa != null) {
			log.info("isHou_Fav memberJpa.getId()-> " + memberJpa.getId());		
			log.info("isHou_Fav memberJpa.getRole()-> " + memberJpa.getRole());		
			hou_Fav.setHouse_id(hid);
			hou_Fav.setMember_id(memberJpa.getId());
			hou_Fav.setIsfavHou(isfavHou);
			favResult = mh.isHou_Fav(hou_Fav);
			//log.info("HouseController favResult=>{}", favResult);
			 model.addAttribute("user_id", memberJpa.getId());	
			 model.addAttribute("user_role", memberJpa.getRole());
									
		 }
		
		model.addAttribute("isfavHou", favResult);
		
		//리뷰갯수
				int totalHouRev = mh.totalHouRev(hid);	
		//페이징
				Paging page = new Paging(totalHouRev, currentPage);
				hou_Rev.setStart(page.getStart());
				hou_Rev.setEnd(page.getEnd());
				log.info("hou_Rev.getStart()"+ hou_Rev.getStart());
				log.info("hou_Rev.getEnd()"+ hou_Rev.getEnd());
				hou_Rev.setHouse_id(hid);
			
		
		
		//리뷰리스트
		List<Hou_Rev> listHouRev = mh.listHouRev(hou_Rev);
		log.info("HouseController list listHouRev.size()=>"+ listHouRev.size());
		
		
		
	
		model.addAttribute("page", page);
		model.addAttribute("house", house);	
		model.addAttribute("houRevList", listHouRev);

		return "mhHou/houDetail";
	}
	
	//정보글작성  페이지 이동
	@GetMapping(value = "houWriteForm")
	public String houWriteForm(@LoginUser MemberJpa memberJpa,House house, Model model) {
		//log.info("HouseController  houWriteForm Start..." );
		  if(memberJpa != null) {
		         log.info("HouseController houWriteForm memberJpa.getId()는 "+ memberJpa.getId());
		         model.addAttribute("user_id", memberJpa.getId());
		        
		         return "mhHou/houWriteForm";

		      }else {
		    	  return  "km/login";
		      }		 		
	
	}
	
	
	//정보글작성
		@PostMapping(value = "houWriteForm")
		public String houWrite(HttpServletRequest request,  List<MultipartFile> file1, Hou_Img hou_Img,
				House house , Model model) throws Exception  {
			log.info("HouseController  houWrite Start");
			//1. 시퀀스 가져오기 
			//숙소 시퀀스 가져오는 쿼리
			int houseSeq = mh.seqHou(house);
			//log.info("HouseController houWrite houseSeq->" + houseSeq );
			
			//2. 가져온 시퀀스 세팅하여 house에 insert
			house.setHouse_id(houseSeq);
			int insertResult = mh.insertHou(house);
			log.info("HouseController houWrite insertResult->"+insertResult );
			

			
			
			//3. 가져온 시퀀스 세팅하여 img insert

			String img_context = "images"+File.separator+"houseUpload" + File.separator;
			log.info("IMG POST Start");		
			
			for (MultipartFile multipartFile : file1) {
				log.info("originalName: {}, img_context : {}",multipartFile.getOriginalFilename(),img_context);
				String img_stored_file = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(),  img_context);
				
				// Service --> DB IMG CRUD
				hou_Img.setImg_original_file(multipartFile.getOriginalFilename());
				hou_Img.setImg_stored_file(img_stored_file);
				hou_Img.setHouse_id(houseSeq);

				int insertImgResult = mh.insertImg(hou_Img);
				log.info("HouseController insertImg insertImgResult->"+ insertImgResult);
			}
			
			if (insertResult > 0) {
				return "redirect:hou";}
			else {
				model.addAttribute("msg","입력 실패 확인해 보세요");
				return "forward:mhHou/houWriteForm";
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
			 String img_stored_file = img_context + uid.toString() + "_" + originalName;
			 log.info("img_stored_file ->" + img_stored_file);
			 File target = new File(img_stored_file);
			 
			 FileCopyUtils.copy(fileData,target);
			 return img_stored_file;
		}
		
		
		
		//정보글수정 페이지이동
		@GetMapping(value = "houUpdateForm")
		public String houseUpdateForm(int house_id, Model model,Hou_Img hou_Img) {
		//	log.info("HouseController Start updateForm...");
			House house = mh.detailHouse(house_id);			
			//log.info("mhController updateFormHouse house->" + house);	
			
			//사진 리스트
			//log.info("Hou_Img Start");
			hou_Img.setHouse_id(house_id);
			List<Hou_Img> listImg = mh.listHou_Img(hou_Img);
			//log.info("HouseController  listImg.size()=>"+ listImg.size());
			model.addAttribute("imgHouList", listImg);
			
			
			model.addAttribute("house", house);	
			return "mhHou/houUpdateForm";
		}
		
		
		
		
		
		
		
		//정보글수정 처리
		@PostMapping(value = "updateHouse")
		public String updateNotice(House house , Model model,
				HttpServletRequest request,  List<MultipartFile> file1, Hou_Img hou_Img
				) throws Exception {
			
			
			//이미지삽입
			String img_context = "images"+File.separator+"houseUpload" + File.separator;
			//log.info("IMG POST Start");
			
			for (MultipartFile multipartFile : file1) {
			//log.info("originalName: {}, img_context : {}",multipartFile.getOriginalFilename(),img_context);
			String img_stored_file = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(),  img_context);
			// Service --> DB IMG CRUD
			hou_Img.setImg_original_file(multipartFile.getOriginalFilename());
			hou_Img.setImg_stored_file(img_stored_file);


			int insertImgResult = mh.insertImg(hou_Img);
			//log.info("HouseController insertImg insertImgResult->"+ insertImgResult);
		}
			
			
			//log.info("HouseController Start update");
			int updateCount = mh.updateHouse(house);
			//log.info("HouseController updateHouse updateCount ->" + updateCount);
			
			
			
			model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
			model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
			
			return "forward:hou";
		}	
		

		
		
		//정보 글 삭제
		@RequestMapping(value = "deleteHouse")
		public String deleteHouse(int house_id, Model model) {
			//log.info("HouseController Start delete house_id :" +house_id);
			int result2 = mh.deleteHouImg(house_id);
			int result3 = mh.deleteHouRevAll(house_id);
			int result = mh.deleteHouse(house_id);	
			return "redirect:hou";
		}
		
		
		//정보글 수정시 사진만 삭제
		@ResponseBody
		@RequestMapping(value = "deleteHouImg")
		public String deleteHouImg(int house_id, int img_id, Model model) {
			//log.info("HouseController Start delete house_id :" + house_id);
			//log.info("HouseController Start delete img_id :" + img_id);
			int result = mh.deleteHouOneImg(house_id,img_id);
			String resultStr = Integer.toString(result);
			return resultStr;
		}
		
		
		//숙소 검색
		@RequestMapping(value = "houseSearch")
		public String houseSearch(House house, String currentPage, Model model) {
			//log.info("HouseController houseSearch Start ..." );
			
			int totalHouse = mh.conditionHouseCount(house);
			//log.info("HouseController houseSearch totalInquire =>" + totalHouse);
			// Paging 작업
			Paging page = new Paging(totalHouse, currentPage);
			
			house.setStart(page.getStart());
			house.setEnd(page.getEnd());
			
			List<House> listSearchHouse = mh.listSearchHouse(house);
			//log.info("HouseController houseSearch listSearchHouse.size()=>" + listSearchHouse.size());
			
			model.addAttribute("totalHouse", totalHouse);
			model.addAttribute("houseList", listSearchHouse);
			model.addAttribute("page", page);
			model.addAttribute("search", house.getKeyword());
			
			return "mhHou/hou";
		}
		
		//숙소종류 구분 필터
		@GetMapping(value = "houseCodeFilter")
		public String houseCodeFilter(@RequestParam(name = "code") 
		String code, House house, String currentPage, Model model  ) {
			//log.info("HouseController houseCodeFilter Start" );
			
			//숙소코드
			List<CommonCode> commonCode = mh.getCommonCode();
			//log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
			model.addAttribute("boardList",commonCode);
			
			//지역 코드도 가져와볼까나
			List<CommonCode> commonLocCode = mh.getCommonLocCode();
			//log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
			model.addAttribute("boardLocList",commonLocCode);
			
			
			int totalHouse = mh.conditionOptionCount(code);
			//log.info("HouseController houseCodeFilter totalHouse =>" + totalHouse);
			
			
			//페이징
			Paging page = new Paging(totalHouse, currentPage);
			house.setStart(page.getStart());
			house.setEnd(page.getEnd());
			house.setCode(code);
			
			List<House> listFilterHouse = mh.listFilterOptionHouse(house);
			//log.info("HouseController  listFilterHouse.size()=>" + listFilterHouse.size());
			model.addAttribute("totalHouse", totalHouse);
			model.addAttribute("houseList", listFilterHouse);
			model.addAttribute("page", page);
			model.addAttribute("search", house.getKeyword());
									
			return "mhHou/hou";
		}
		
		
		
		//지역 구분 필터
		@GetMapping(value = "houLocCodeFilter")
		public String locCodeFilter(@RequestParam(name = "code") 
		String code, House house, String currentPage, Model model) {
			//log.info("HouseController locCodeFilter Start" );
			
			
			//숙소코드
			List<CommonCode> commonCode = mh.getCommonCode();
			//log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
			model.addAttribute("boardList",commonCode);
			
			//지역 코드도 가져와볼까나
			List<CommonCode> commonLocCode = mh.getCommonLocCode();
			//log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
			model.addAttribute("boardLocList",commonLocCode);
			
			
			int totalLoc = mh.conditionOptionLocCount(code);
			//log.info("HouseController locCodeFilter totalLoc =>" + totalLoc);
			
			
			//페이징
			Paging page = new Paging(totalLoc, currentPage);
			house.setStart(page.getStart());
			house.setEnd(page.getEnd());
			house.setCode(code);
			
			List<House> listFilterLoc = mh.listFilterOptionLoc(house);
			//log.info("HouseController  listFilterLoc.size()=>" + listFilterLoc.size());
			model.addAttribute("totalLoc", totalLoc);
			model.addAttribute("houseList", listFilterLoc);
			model.addAttribute("page", page);
			model.addAttribute("search", house.getKeyword());
			
			return "mhHou/hou";
		}
	//리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰
		
		//정보 리뷰작성  페이지 이동
		@GetMapping(value = "houRevWriteForm")
		public String houRevWriteForm (@LoginUser MemberJpa memberJpa,Hou_Rev hou_Rev, Model model) {
			//log.info("HouseController  houRevWriteForm Start..." );	
		
			
			  if(memberJpa != null) {
			
			//log.info("HouseController houRevWriteForm memberJpa.getId()는 "+ memberJpa.getId());
			// log.info("HouseController houFav hou_Rev.getHouse_id()는 "+ hou_Rev.getHouse_id());
			model.addAttribute("user_id", memberJpa.getId());
			model.addAttribute("hou_rev", hou_Rev);
			
			return "mhHou/houRevWriteForm";
			
			
			  }else {
		    	  return  "km/login";
		      }	
			
			
		}
		
		

		
		
		
		//리뷰작성
		@PostMapping(value = "houRevWriteForm")
		public String houRevWrite(@LoginUser MemberJpa memberJpa,
				Hou_Rev hou_Rev, Model model) throws Exception {
			//log.info("HouseController  houRevWrite Start...");
			
			  if (memberJpa == null){
			         throw new Exception("로그인 해주세요!");
			      }
			
			//  log.info("HouseController writeFormHouRev memberJpa.getId()는 "+ memberJpa.getId());
			  hou_Rev.setMember_id( memberJpa.getId());
			  
			  
			int insertResult = mh.insertHouRev(hou_Rev);
			//log.info("HouseController houRevWrite insertResult->"+insertResult );
			
			
			if(insertResult >0) {
				return "redirect:houDetail?hid="+hou_Rev.getHouse_id();
			}
			else {				
				model.addAttribute("msg","입력 실패 확인해 보세요");
				return "forward:mhHou/houRevWriteForm";
			}
			
		}
		//리뷰글수정 페이지이동
		
		@GetMapping(value = "houRevUpdateForm")
		public String houRevUpdateForm(@RequestParam("review_id") Optional<Integer> review_id,
		                               @RequestParam(value = "house_id", required = true) long house_id,
		                               Model model) {
			//Required request parameter 'review_id' for method parameter type long is not present
			//log.info("HouseController  houRevUpdateForm Start..." );
			
			return "mhHou/houRevUpdateForm";
		}
		
		
		
		
		
		//리뷰글수정 처리
		@PostMapping(value = "updateHouseRev")
		public String updateHouseRev(@RequestParam("review_id") int review_id, Hou_Rev hou_Rev, Model model) {
		   // log.info("HouseController Start update");

			int updateCount = mh.updateHouseRev(hou_Rev);
			//log.info("HouseController updateHouseRev updateCount ->" + updateCount);
			
			model.addAttribute("uptCnt",updateCount);   //
			model.addAttribute("kk3","Message Test");   // 
			
			return "forward:hou";
		}	
		
		//리뷰글 삭제
		@RequestMapping(value = "deleteHouRev")
		public String deleteHouRev(int review_id, Model model) {
			//log.info("HouseController Start delete... n_id :" + review_id);
			int result = mh.deleteHouRev(review_id);
			return "redirect:hou";
		}
		
		
		//즐겨찾기 추가
		@ResponseBody
		@PostMapping("insertHouFav")
		public String houFav(@LoginUser MemberJpa memberJpa,
				Hou_Fav hou_Fav, Model model) throws Exception {
			//log.info("HouseController  houFav Start");
			if (memberJpa == null){
		         throw new Exception("로그인 해주세요!");
		      }
			// log.info("HouseController houFav memberJpa.getId()는 "+ memberJpa.getId());
			// log.info("HouseController houFav hou_Fav.getHouse_id()는 "+ hou_Fav.getHouse_id());
			hou_Fav.setMember_id(memberJpa.getId());
			hou_Fav.setHouse_id(hou_Fav.getHouse_id());
			
			
			int insertResult = mh.insertHouFav(hou_Fav);
			//log.info("HouseController houFav insertResult->"+insertResult );
		
			
			return "mhHou/hou";
		}
		

		//즐겨찾기 해제
		@RequestMapping(value = "deleteHouFav")
		public String deleteHouFav(@LoginUser MemberJpa memberJpa,Hou_Fav hou_Fav, Model model) throws Exception {
			//log.info("HouseController  deleteHouFav Start");
			if (memberJpa == null){
		         throw new Exception("로그인 해주세요!");
		      }
			hou_Fav.setMember_id(memberJpa.getId());
			//log.info("HouseController houFav memberJpa.getId()는 "+ memberJpa.getId());
			//log.info("HouseController Start delete house_id :" + hou_Fav.getHouse_id());
			
			hou_Fav.setMember_id(memberJpa.getId());
			hou_Fav.setHouse_id(hou_Fav.getHouse_id());
			
			int result = mh.deleteHouFav(hou_Fav);
			return  "mhHou/hou";
		}


		
		
		
		
}
