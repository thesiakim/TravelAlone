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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.travelAlone.s20230404.model.CommonCode;
import com.travelAlone.s20230404.model.Hou_Img;
import com.travelAlone.s20230404.model.Hou_Rev;
import com.travelAlone.s20230404.model.House;

import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.mh.HouseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HouseController {

	private final HouseService mh;
	
	// ===================숙소===================
	//숙소 메인 보기
	@RequestMapping(value = "hou")
	public String notice(House house , String currentPage, Model model) {
		log.info("mhController2 Start house...");
		int totalHouse = mh.totalHouse();		
		
		//페이징
		Paging page = new Paging(totalHouse, currentPage);
		house.setStart(page.getStart());
		house.setEnd(page.getEnd());
		
		
		//여기에 게시판 코드 불러오는 코드 작성
		List<CommonCode> commonCode = mh.getCommonCode();
		log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
		model.addAttribute("boardList",commonCode);
		
		
		//지역 코드도 가져와볼까나
		List<CommonCode> commonLocCode = mh.getCommonLocCode();
		log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
		model.addAttribute("boardLocList",commonLocCode);
		
		
		
		//숙소리스트
		List<House> listHouse = mh.listHouse(house);
		log.info("mhController2 list listHouse.size()=>"+ listHouse.size());
				
		model.addAttribute("totalHouse", totalHouse);
		model.addAttribute("houseList", listHouse);
		model.addAttribute("page", page);
		return "mhHou/hou";
	}
	
	//숙소   정보글조회
	@GetMapping(value = "houDetail")
	public String houseDetail(int hid , Model model , Hou_Img hou_Img ) {
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
		
		//리뷰리스트
		List<Hou_Rev> listHouRev = mh.listHouRev(hid);
		log.info("HouseController list listHouRev.size()=>"+ listHouRev.size());
		
		model.addAttribute("house", house);	
		model.addAttribute("houRevList", listHouRev);

		return "mhHou/houDetail";
	}
	
	//정보글작성  페이지 이동
	@GetMapping(value = "houWriteForm")
	public String houWriteForm(House house, Model model) {
		log.info("HouseController  houWriteForm Start..." );
		return "mhHou/houWriteForm";
	}
	
	
	//정보글작성
		@PostMapping(value = "houWriteForm")
		public String houWrite(HttpServletRequest request,  List<MultipartFile> file1, Hou_Img hou_Img,
				House house , Model model) throws Exception  {
			log.info("HouseController  houWrite Start...");
			//1. 시퀀스 가져오기 
			//숙소 시퀀스 가져오는 쿼리
			int houseSeq = mh.seqHou(house);
			log.info("HouseController houWrite houseSeq->" + houseSeq );
			
			//2. 가져온 시퀀스 세팅하여 house에 insert
			house.setHouse_id(houseSeq);
			int insertResult = mh.insertHou(house);
			log.info("HouseController houWrite insertResult->"+insertResult );
			

			
			
			//3. 가져온 시퀀스 세팅하여 img insert
			//이미지를 넣어보자 ㅠㅠ	
			String img_context = request.getSession().getServletContext().getRealPath("/upload/");
			log.info("IMG POST Start");
			//이렇게 for문으로 돌려서 여러번 넣어주면 됨 아니면 list 형태로 한번에 넣는방법도 있긴한데 그건 좀 귀찮고 찾아봐야댐 
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
			 String img_stored_file = uid.toString() + "_" + originalName;
			 log.info("img_stored_file ->" + img_stored_file);
			 File target = new File(img_context, img_stored_file);
			 
			 FileCopyUtils.copy(fileData,target);
			
			
			return img_stored_file;
		}
		
		
		
		//정보글수정 페이지이동
		@GetMapping(value = "houUpdateForm")
		public String houseUpdateForm(int house_id, Model model) {
			log.info("HouseController Start updateForm...");
			House house = mh.detailHouse(house_id);			
			log.info("mhController updateFormHouse house->" + house);			
			model.addAttribute("house", house);	
			return "mhHou/houUpdateForm";
		}
		
		
		//정보글수정 처리
		@PostMapping(value = "updateHouse")
		public String updateNotice(House house , Model model) {
			log.info("HouseController Start update");
			int updateCount = mh.updateHouse(house);
			log.info("HouseController updateHouse updateCount ->" + updateCount);
			
			model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
			model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
			
			return "forward:hou";
		}	
		
		//정보 글 삭제
		@RequestMapping(value = "deleteHouse")
		public String deleteHouse(int house_id, Model model) {
			log.info("HouseController Start delete house_id :" +house_id);
			int result2 = mh.deleteHouImg(house_id);
			int result = mh.deleteHouse(house_id);	
			return "redirect:hou";
		}
		
		//숙소 검색
		@RequestMapping(value = "houseSearch")
		public String houseSearch(House house, String currentPage, Model model) {
			log.info("HouseController houseSearch Start ..." );
			
			int totalHouse = mh.conditionHouseCount(house);
			log.info("HouseController houseSearch totalInquire =>" + totalHouse);
			// Paging 작업
			Paging page = new Paging(totalHouse, currentPage);
			
			house.setStart(page.getStart());
			house.setEnd(page.getEnd());
			
			List<House> listSearchHouse = mh.listSearchHouse(house);
			log.info("HouseController houseSearch listSearchHouse.size()=>" + 
					listSearchHouse.size());
			
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
			log.info("HouseController houseCodeFilter Start" );
			
			//숙소코드
			List<CommonCode> commonCode = mh.getCommonCode();
			log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
			model.addAttribute("boardList",commonCode);
			
			
			
			
			int totalHouse = mh.conditionOptionCount(code);
			log.info("HouseController houseCodeFilter totalHouse =>" + totalHouse);
			
			
			//페이징
			Paging page = new Paging(totalHouse, currentPage);
			house.setStart(page.getStart());
			house.setEnd(page.getEnd());
			house.setCode(code);
			
			List<House> listFilterHouse = mh.listFilterOptionHouse(house);
			log.info("HouseController  listFilterHouse.size()=>" + listFilterHouse.size());
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
			log.info("HouseController locCodeFilter Start" );
			
			//지역 코드도 가져와볼까나
			List<CommonCode> commonLocCode = mh.getCommonLocCode();
			log.info("boardLocList data : {}, {}",commonLocCode.get(0).getCode(),commonLocCode.get(0).getValue());
			model.addAttribute("boardLocList",commonLocCode);
			
			
			int totalLoc = mh.conditionOptionLocCount(code);
			log.info("HouseController locCodeFilter totalLoc =>" + totalLoc);
			
			
			//페이징
			Paging page = new Paging(totalLoc, currentPage);
			house.setStart(page.getStart());
			house.setEnd(page.getEnd());
			house.setCode(code);
			
			List<House> listFilterLoc = mh.listFilterOptionLoc(house);
			log.info("HouseController  listFilterLoc.size()=>" + listFilterLoc.size());
			model.addAttribute("totalLoc", totalLoc);
			model.addAttribute("houseList", listFilterLoc);
			model.addAttribute("page", page);
			model.addAttribute("search", house.getKeyword());
			
			return "mhHou/hou";
		}
	//리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰
		
		//정보 리뷰작성  페이지 이동
		@GetMapping(value = "houRevWriteForm")
		public String houRevWriteForm (Hou_Rev hou_Rev, Model model) {
			log.info("HouseController  houRevWriteForm Start..." );	
			model.addAttribute("hou_rev", hou_Rev);
			return "mhHou/houRevWriteForm";
		}
		
		//리뷰작성
		@PostMapping(value = "houRevWriteForm")
		public String houRevWrite(Hou_Rev hou_Rev, Model model) {
			log.info("HouseController  houRevWrite Start...");
			int insertResult = mh.insertHouRev(hou_Rev);
			log.info("HouseController houRevWrite insertResult->"+insertResult );
			if(insertResult >0) {
				return "redirect:houDetail?hid="+hou_Rev.getHouse_id();
			}
			else {				
				model.addAttribute("msg","입력 실패 확인해 보세요");
				return "forward:mhHou/houRevWriteForm";
			}
			
		}
		//리뷰글수정 페이지이동
//		@GetMapping(value = "houRevUpdateForm")
//		public String houRevUpdateForm(@RequestParam("review_id") Optional<Integer>  review_id, Model model) {
//			log.info("HouseController  houRevUpdateForm Start..." );
//				
//			return "mhHou/houRevUpdateForm";
//		}
		
		@GetMapping(value = "houRevUpdateForm")
		public String houRevUpdateForm(@RequestParam("review_id") Optional<Integer> review_id,
		                               @RequestParam(value = "house_id", required = true) long house_id,
		                               Model model) {
			//Required request parameter 'review_id' for method parameter type long is not present
			log.info("HouseController  houRevUpdateForm Start..." );
			
			return "mhHou/houRevUpdateForm";
		}
		
		
		
		
		
		//리뷰글수정 처리
		@PostMapping(value = "updateHouseRev")
		public String updateHouseRev(@RequestParam("review_id") int review_id, Hou_Rev hou_Rev, Model model) {
		    log.info("HouseController Start update");

			int updateCount = mh.updateHouseRev(hou_Rev);
			log.info("HouseController updateHouseRev updateCount ->" + updateCount);
			
			model.addAttribute("uptCnt",updateCount);   //
			model.addAttribute("kk3","Message Test");   // 
			
			return "forward:hou";
		}	
		
		//리뷰글 삭제
		@RequestMapping(value = "deleteHouRev")
		public String deleteHouRev(int review_id, Model model) {
			log.info("HouseController Start delete... n_id :" + review_id);
			int result = mh.deleteHouRev(review_id);
			return "redirect:hou";
		}
		
		
		

		
		
		
}
