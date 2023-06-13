package com.travelAlone.s20230404.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
import com.travelAlone.s20230404.model.mh.Inq_Img;
import com.travelAlone.s20230404.model.mh.Inquire;
import com.travelAlone.s20230404.model.mh.Not_Img;
import com.travelAlone.s20230404.model.mh.Notice;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.mh.MhService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
public class MhController {
	
	private final MhService mh;
	
		
// ===================공지사항=========================================================

//공지사항 목록보기
@RequestMapping(value = "notice")
public String notice(@LoginUser MemberJpa memberJpa,	Notice notice,String currentPage, Model model) {
	//log.info("NoticeController Start notice");
	int totalNotice = mh.totalNotice();		
	
	//페이징
	Paging page = new Paging(totalNotice, currentPage);
	notice.setStart(page.getStart());
	notice.setEnd(page.getEnd());
	
	List<Notice> listNotice = mh.listNotice(notice);
	//log.info("NoticeController list listNotice.size()=>" + listNotice.size());
	  if (memberJpa != null) {
			// log.info("NoticeController notice memberJpa.getId()는 "+ memberJpa.getId()); 
			 //log.info("NoticeController notice memberJpa.getId()는 "+ memberJpa.getRole()); 
			 model.addAttribute("user_id", memberJpa.getId());
			 model.addAttribute("user_role", memberJpa.getRole());
			 
			  }
	model.addAttribute("totalNotice", totalNotice);
	model.addAttribute("noticeList", listNotice);
	model.addAttribute("page", page);
	return "mh/notice";
}
	

//공지사항   게시글조회
@GetMapping(value = "noticeDetail")
public String noticeDetail(@LoginUser MemberJpa memberJpa, 
		int gid , Model model, Not_Img not_Img ) {
	//log.info("NoticeController Start noticeDetail");
	//log.info("NoticeController noticeDetail g_notice_id->"+ gid );
	
	Notice notice = mh.detailNotice(gid);
	//사진 리스트
	//log.info("Not_Img Start");
	not_Img.setG_notice_id(gid);
	List<Not_Img> listImg = mh.listNot_Img(not_Img);
	//log.info("NoticeController  listImg.size()=>"+ listImg.size());
	
	
	model.addAttribute("imgNotList", listImg);		
	model.addAttribute("notice", notice);
	  if (memberJpa != null) {
	// log.info("NoticeController noticeDetail memberJpa.getId()는 "+ memberJpa.getId()); 
	// log.info("NoticeController noticeDetail memberJpa.getRole()는 "+ memberJpa.getRole()); 
	 model.addAttribute("user_id", memberJpa.getId());
	 model.addAttribute("user_role", memberJpa.getRole());
	 
	  }
		
	
	return "mh/noticeDetail";
}
	
	
	
//공지사항 글작성 페이지 이동
@GetMapping(value = "noticeWriteForm")
public String noticeWriteForm(@LoginUser MemberJpa memberJpa, Notice notice, Model model) {

	  
	if(memberJpa != null) {
	 //log.info("NoticeController  noticeWriteForm Start");	
	 //log.info("NoticeController noticeWriteForm memberJpa.getId()는 "+ memberJpa.getId());
    model.addAttribute("user_id", memberJpa.getId());
	return "mh/noticeWriteForm";
	
	 }	
	else {
   	  return  "km/login";
     }		 	
	
	
}
	

//공지사항 글작성
@PostMapping(value = "noticeWriteForm")
public String noticeWrite(@LoginUser MemberJpa memberJpa,
		Notice notice, Model model, HttpServletRequest request,   List<MultipartFile>  file1
		,Not_Img not_Img) throws Exception {
	//log.info("NoticeController  noticeWrite Start...");
	
	  if (memberJpa == null){
	         throw new Exception("로그인 해주세요!");
	      }
	
	
	//공지사항 시퀀스가져오기
	int noticeSeq = mh.seqNot(notice);
	//log.info("NoticeController noticeWrite noticeSeq->" + noticeSeq );
	
	notice.setG_notice_id(noticeSeq);
	//log.info("NoticeController writeFormBoard memberJpa.getId()는 "+ memberJpa.getId());
	
	
	notice.setMember_id(memberJpa.getId());
	int insertResult = mh.insertNotice(notice);
	//log.info("NoticeController noticeWrite insertResult->"+insertResult );
	
	
	//이미지 삽입
	String img_context = "images"+File.separator+"noticeUpload" + File.separator;
	//log.info("IMG POST Start");
	for(MultipartFile multipartFile : file1) {
		//log.info("originalName: {}, img_context : {}",multipartFile.getOriginalFilename(),img_context);
		String img_stored_file = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(),  img_context);
		
	    // Service --> DB IMG CRUD
		not_Img.setImg_original_file(multipartFile.getOriginalFilename());
		not_Img.setImg_stored_file(img_stored_file);
		not_Img.setG_notice_id(noticeSeq);
	    
			
		int insertImgResult = mh.insertNotImg(not_Img);
		//log.info("NoticeController insertNotImg insertImgResult->"+ insertImgResult);
	}
	
	
	if (insertResult > 0) {
		return "redirect:notice";}
	else {
		model.addAttribute("msg","입력 실패 확인해 보세요");
		return "forward:mh/noticeWriteForm";
	}
	
}
	 
	

	
//공지사항 글수정 페이지이동
@GetMapping(value = "noticeUpdateForm")
//public String noticeUpdateForm(@LoginUser MemberJpa memberJpa,int g_notice_id, Model model, Not_Img not_Img) {
	public String noticeUpdateForm(int g_notice_id, Model model, Not_Img not_Img) {
	//log.info("NoticeController Start updateForm...");
	
//	if(memberJpa != null) {
	//		log.info("NoticeController noticeUpdateForm memberJpa.getId()는 "+ memberJpa.getId());
	  //      model.addAttribute("user_id", memberJpa.getId());
	        
		
			Notice notice = mh.detailNotice(g_notice_id);
			//log.info("NoticeController updateFormNotice house->" + notice);		
			
			//사진리스트
			//log.info("Not_Img Start");
			not_Img.setG_notice_id(g_notice_id);
			List<Not_Img> listImg  = mh.listNot_Img(not_Img);
			//log.info("NoticeController  listImg.size()=>"+ listImg.size());
			model.addAttribute("imgNotList", listImg);
			
			model.addAttribute("notice", notice);	
			return "mh/noticeUpdateForm";
	
	 // }else {
    //	  return  "th/login";
   //   }		 		
		
	}

//공지사항 글수정 처리
@PostMapping(value = "updateNotice")
public String updateNotice(Notice notice , Model model,
		HttpServletRequest request,  List<MultipartFile> file1, Not_Img  not_Img) throws IOException, Exception {
	
	
	//이미지삽입
	String img_context = "images"+File.separator+"noticeUpload" + File.separator;
	//log.info("IMG POST Start");
	
	for (MultipartFile multipartFile : file1){
		//log.info("originalName: {}, img_context : {}",multipartFile.getOriginalFilename(),img_context);
		String img_stored_file = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(),  img_context);
		// Service --> DB IMG CRUD
		not_Img.setImg_original_file(multipartFile.getOriginalFilename());
		not_Img.setImg_stored_file(img_stored_file);


		int insertImgResult = mh.insertNotImg(not_Img);
		//log.info("NoticeController insertNotImg insertImgResult->"+ insertImgResult);
	}
					

	//log.info("NoticeController Start update");
	int updateCount = mh.updateNotice(notice);
	//log.info("NoticeController es.updateNotice updateCount-->"+ updateCount);

	model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
	model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
	
	return "forward:notice";
}
	

	
//공지사항 글 삭제
@RequestMapping(value = "deleteNotice")
public String deleteNotice(int g_notice_id, Model model) {
	//log.info("NoticeController Start delete... n_id :" +g_notice_id);
	int result2 = mh.deleteNotImg(g_notice_id);
	int result = mh.deleteNotice(g_notice_id);
	
	return "redirect:notice";
}
	
	
//공지사항글 수정시 사진만 삭제

@ResponseBody
@RequestMapping(value = "deleteNotImg")
public String deleteNotImg(int g_notice_id, int img_id, Model model) {
	//log.info("NoticeController Start delete g_notice_id :" + g_notice_id);
	//log.info("NoticeController Start delete img_id :" + img_id);
	int result = mh.deleteNotOneImg(g_notice_id,img_id);
	String resultStr = Integer.toString(result);
	return resultStr;
}
	
	
	
	
//공지사항 검색
@RequestMapping(value = "noticeSearch")
public String noticeSearch(Notice notice, String currentPage, Model model) {
	//log.info("NoticeController noticeSearch Start ..." );
	// Notice 전체 Count  
	int totalNotice = mh.conditionNoticeCount(notice);
	//log.info("NoticeController noticeSearch totalNotice =>" + totalNotice);
	// Paging 작업
	Paging page = new Paging(totalNotice, currentPage);
	
	// Parameter emp --> Page만 추가 Setting
	notice.setStart(page.getStart());
	notice.setEnd(page.getEnd());
	List<Notice> listSearchNotice = mh.listSearchNotice(notice);
	//log.info("NoticeController noticeSearch listSearchNotice.size()=>" + 	listSearchNotice.size());
	model.addAttribute("totalNotice", totalNotice);
	model.addAttribute("noticeList", listSearchNotice);
	model.addAttribute("page", page);
	model.addAttribute("search", notice.getKeyword());
	return "mh/notice";
	
}
	
	
// ===================자주믇는 질문=====================================================================================================
	//자주묻는질문 목록보기
	@RequestMapping(value = "faq")
	public String Faq(@LoginUser MemberJpa memberJpa, Notice notice, String currentPage, Model model) {
		//log.info("FaqController Start faq..." );
		int totalNotice = mh.totalNotice();		
		
		//페이징
		Paging page = new Paging(totalNotice, currentPage);
		notice.setStart(page.getStart());
		notice.setEnd(page.getEnd());
		
		List<Notice> listFaq = mh.listFaq(notice);
		//log.info("FaqController list listFaq.size()=>" + listFaq.size());
				
		if (memberJpa != null) {
			// log.info("NoticeController notice memberJpa.getId()는 "+ memberJpa.getId()); 
			 //log.info("NoticeController notice memberJpa.getId()는 "+ memberJpa.getRole()); 
			 model.addAttribute("user_id", memberJpa.getId());
			 model.addAttribute("user_role", memberJpa.getRole());
			 
			  }

		//model.addAttribute("totalNotice", totalNotice);
		model.addAttribute("noticeList", listFaq);
		model.addAttribute("page", page);
		return "mh/faq";
	}
		
	
	
//==========================문의게시판=========================================================================================
//문의게시판 글목록
@RequestMapping(value = "inquire")
public String inquire( Inquire inquire, String currentPage, Model model) {
	
	//log.info("InquireController Start inquire...");
	int totalInquire = mh.totalInquire();
	
	//페이징
	Paging page = new Paging(totalInquire, currentPage);
	inquire.setStart(page.getStart());
	inquire.setEnd(page.getEnd());
	
	
	//여기에 게시판 코드 불러오는 코드 작성
	List<CommonCode> commonCode = mh.getCommonCode();
	//log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
	model.addAttribute("boardList",commonCode);
	
	
	//요구사항 리스트
	List<Inquire> listInquire = mh.listInquire(inquire);
	//log.info("InquireController  listInquire.size()=>" + listInquire.size());
	
	model.addAttribute("totalInquire", totalInquire);
	model.addAttribute("inquireList", listInquire);
	model.addAttribute("page", page);

	
	return "mh/inquire";
}
	




//문의게시판  게시글조회
@GetMapping(value = "inquireDetail")
public String inquireDetail(@LoginUser MemberJpa memberJpa,  
		int gid , Model model, Inq_Img inq_Img ) {
	//log.info("InquireController Start inquireDetail..." );
	//log.info("InquireController Start inquireDetail g_notice_id"+ gid  );
	//문의글  보기
	Inquire inquire = mh.detailInquire(gid);
	
	//사진 리스트
	//log.info("Inq_Img Start");
	inq_Img.setG_writing_id(gid);
	List<Inq_Img> listImg = mh.listInq_Img(inq_Img);
	//log.info("InquireController  listImg.size()=>"+ listImg.size());
	model.addAttribute("imgInqList", listImg);		
	model.addAttribute("inquire", inquire);	
	
	  if (memberJpa != null) {
			// log.info("InquireController inquireDetail memberJpa.getId()는 "+ memberJpa.getId()); 
			// log.info("InquireController inquireDetail memberJpa.getRole()는 "+ memberJpa.getRole()); 
			 model.addAttribute("user_id", memberJpa.getId());
			 model.addAttribute("user_role", memberJpa.getRole());
			  }
	
	

	return "mh/inquireDetail";
}
		
		
		
//문의게시판 글작성 페이지이동
@GetMapping(value = "inquireWriteForm")
public String inquireWriteForm(@LoginUser MemberJpa memberJpa, 
		Inquire inquire,Model model) {
	//log.info("InquireController inquireWriteForm Start... ");
	
	 if(memberJpa != null) {
        // log.info("InquireController writeFormBoard memberJpa.getId()는 "+ memberJpa.getId());
         model.addAttribute("user_id", memberJpa.getId());
		 
		return "mh/inquireWriteForm";

      }else {
    	  return "km/login";
      }
	
	
}
		
	
		
//문의게시판 글작성
@PostMapping(value = "inquireWriteForm")
public String  inquireWrite(
		@LoginUser MemberJpa memberJpa,
		HttpServletRequest request,   List<MultipartFile>  file1, Inq_Img inq_Img,
		Inquire inquire, Model model) throws Exception {

	//log.info("InquireController  inquireWrite Start");
	  if (memberJpa == null){
	         throw new Exception("로그인 해주세요!");
	      }
	
	
	int inquireSeq = mh.seqInq(inquire);
	//log.info("InquireController inqWrite inquireSeq->" + inquireSeq );

	inquire.setG_writing_id(inquireSeq);	
	//log.info("InquireController writeFormBoard memberJpa.getId()는 "+ memberJpa.getId());
	
	inquire.setMember_id(memberJpa.getId());
	int insertResult = mh.insertInquire(inquire);
	//log.info("InquireController  inquireWrite insertResult> " + insertResult);
	
																			
	
	//이미지 삽입
	String img_context = "images"+File.separator+"InquireUpload" + File.separator;
	//log.info("IMG POST Start");
	for(MultipartFile multipartFile : file1) {
		//log.info("originalName: {}, img_context : {}",multipartFile.getOriginalFilename(),img_context);
		String img_stored_file = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(),  img_context);
		
	    // Service --> DB IMG CRUD
		inq_Img.setImg_original_file(multipartFile.getOriginalFilename());
	    inq_Img.setImg_stored_file(img_stored_file);
	    inq_Img.setG_writing_id(inquireSeq);
	    
			
		int insertImgResult = mh.insertInqImg(inq_Img);
		//log.info("mhController insertImg insertImgResult->"+ insertImgResult);
	}						

	if (insertResult>0) {
		return "redirect:inquire";
	}
	else {
		model.addAttribute("msg","입력 실패 확인해 보세요");
		return "forward:mh/inquireWriteForm";
	}
}
		

private String uploadFile(String originalName,byte[] fileData, String img_context) throws Exception {
	 UUID uid = UUID.randomUUID();
	 // requestPath = requestPath + "/resources/image";
	// log.info("img_context->" + img_context);
	 
	// Directory 생성 
	 File fileDirectory = new File(img_context);
	 if (!fileDirectory.exists()) {
		 fileDirectory.mkdirs();
		//log.info("업로드용 폴더 생성" + img_context ); 
		
	 }
	 String img_stored_file = img_context + uid.toString() + "_" + originalName;
	// log.info("img_stored_file ->" + img_stored_file);
	 File target = new File(img_stored_file);
	 
	 FileCopyUtils.copy(fileData,target);
	 return img_stored_file;
}
		
								
		
//문의게시판 글수정 페이지이동
@GetMapping(value = "inquireUpdateForm")
public String inquireUpdateForm(int g_writing_id, Model model, Inq_Img inq_Img) {
	//log.info("InquireController Start updateForm...");
	Inquire inquire = mh.detailInquire(g_writing_id);
	//log.info("InquireController updateFormInquire inquire->"+inquire);			
	
	//사진리스트
	//log.info("Inq_Img Start");
	inq_Img.setG_writing_id(g_writing_id);
	List<Inq_Img> listImg = mh.listInq_Img(inq_Img);
	//log.info("InquireController  listImg.size()=>"+ listImg.size());
	model.addAttribute("imgInqList", listImg);
							
	
	model.addAttribute("inquire", inquire);	
	return "mh/inquireUpdateForm";
}	

		
						
		
		
//문의게시판 글수정 
@PostMapping(value = "updateInquire")
public String updateInquire(Inquire inquire, Model model,
		HttpServletRequest request,  List<MultipartFile> file1, Inq_Img	 inq_Img
		) throws IOException, Exception {
	
	
	//이미지삽입
	String img_context = "images"+File.separator+"inquireUpload" + File.separator;
	//log.info("IMG POST Start");
	
	for (MultipartFile multipartFile : file1){
		//log.info("originalName: {}, img_context : {}",multipartFile.getOriginalFilename(),img_context);
		String img_stored_file = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(),  img_context);
		// Service --> DB IMG CRUD
		inq_Img.setImg_original_file(multipartFile.getOriginalFilename());
		inq_Img.setImg_stored_file(img_stored_file);


		int insertImgResult = mh.insertInqImg(inq_Img);
		//log.info("InquireController insertImg insertImgResult->"+ insertImgResult);
	}
	

	//log.info("InquireController Start update...");
	int updateCount = mh.updateInquire(inquire);
	//log.info("InquireController es.updateNotice updateCount-->"+ updateCount);
	
	model.addAttribute("uptCnt", updateCount);
	model.addAttribute("kk3"," Message Test");
	return "forward:inquire";
}
		
		
		
		
//문의게시판 글 삭제
@RequestMapping(value = "deleteInquire")
public String deleteInquire(int g_writing_id, Model model) {
	//log.info("InquireController Start delete..." + g_writing_id);
	int result2 = mh.deleteInqImg(g_writing_id);
	int result = mh.deleteInquire(g_writing_id);
	
	return "redirect:inquire";
}
		
		
		
//문의게시판글 수정시 사진만 삭제
@ResponseBody
@RequestMapping(value = "deleteInqImg")
public String deleteInqImg(int g_writing_id, int img_id, Model model) {
	//log.info("InquireController Start delete g_writing_id :" + g_writing_id);
	//log.info("InquireController Start delete img_id :" + img_id);
	int result = mh.deleteInqOneImg(g_writing_id,img_id);
	String resultStr = Integer.toString(result);
	return resultStr;
}
		
		

//문의게시판 검색
@RequestMapping(value = "inquireSearch")
public String inquireSearch(Inquire inquire, String currentPage, Model model) {
	//log.info("InquireController inquireSearch Start ..." );
	
	int totalInquire = mh.conditionInquireCount(inquire);
	//log.info("InquireController inquireSearch totalInquire =>" + totalInquire);
	// Paging 작업
	Paging page = new Paging(totalInquire, currentPage);
	
	inquire.setStart(page.getStart());
	inquire.setEnd(page.getEnd());
	
	List<Inquire> listSearchInquire = mh.listSearchInquire(inquire);
	//log.info("InquireController InquireSearch listSearchInquire.size()=>" + listSearchInquire.size());
	
	model.addAttribute("totalInquire", totalInquire);
	model.addAttribute("inquireList", listSearchInquire);
	model.addAttribute("page", page);
	model.addAttribute("search", inquire.getKeyword());
	
	return "mh/inquire";
}
		
//필터
@GetMapping(value="inquireCodeFilter")
public String inquireFilter(@RequestParam(name = "code") String code,Inquire inquire, String currentPage, Model model) {
						//@RequestParam 어노테이션은 code라는 이름의 파라미터를 받으며, 이 값은 String 타입의 code 변수에 할당됩니다.
	//log.info("mhController inquireCodeFilter Start ..." );
	

	List<CommonCode> commonCode = mh.getCommonCode();
	//log.info("boardList data : {}, {}",commonCode.get(0).getCode(),commonCode.get(0).getValue());
	//				boardList data : 				inq100		, 여행지문의
	model.addAttribute("boardList",commonCode);
	//List<CommonCode> 타입의 commonCode 변수에 저장합니다. 이 리스트는 모델 객체에 추가되어 View에서 사용됩니다.
	
	int totalInquire = mh.conditionOptionCount(code);
	//log.info("mhController inquireCodeFilter totalInquire =>" + totalInquire);
	//메소드를 호출하여 totalInquire 변수에 총 조회된 데이터의 개수를 할당
	
	//페이징
	Paging page = new Paging(totalInquire, currentPage);
	inquire.setStart(page.getStart());
	inquire.setEnd(page.getEnd());
	inquire.setCode(code);
	
	
	List<Inquire> listFilterInquire = mh.listFilterOptionInquire(inquire);
	//메소드를 호출하여 조건에 맞는 데이터를 조회합니다. 이 데이터는 모델 객체에 추가되어 View에서 사용
	//log.info("InquireController  listFilterInquire.size()=>" + listFilterInquire.size());
	model.addAttribute("totalInquire", totalInquire);
	model.addAttribute("inquireList", listFilterInquire);
	model.addAttribute("page", page);
	model.addAttribute("search", inquire.getKeyword());
						
	return "mh/inquire";
}
				
		

		
//문의게시판 답변 페이지이동
@GetMapping(value = "inquireReplyForm")
public String inquireReplyForm(int g_writing_id, Model model) {
	//log.info("InquireController inquireReplyForm Start... ");
	Inquire inquire = mh.detailInquire(g_writing_id);
	//log.info("InquireController inquireReplyForm inquire->"+inquire);	
	model.addAttribute("inquire", inquire);	
	return "mh/inquireReplyForm";
	
}

//문의게시판 글답변(업데이트) 
@PostMapping(value = "replyInquire")
public String replyInquire(Inquire inquire, Model model) {
	//log.info("InquireController Start reply...");
	//log.info("InquireController reply" + inquire.getG_writing_id());
	//log.info("InquireController reply" + inquire.getG_content());
	//log.info("InquireController reply" + inquire.getG_reply_content());
	
	int replyCount = mh.replyInquire(inquire);
	//log.info("InquireController es.updateNotice updateCount-->"+ replyCount);
	model.addAttribute("repCnt", replyCount);
	model.addAttribute("kk3"," Message Test");
	return "forward:inquire";
}
		


		
		
		
	
}


