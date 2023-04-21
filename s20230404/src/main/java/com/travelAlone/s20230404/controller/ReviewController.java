package com.travelAlone.s20230404.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travelAlone.s20230404.model.Review;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.sm.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService sm;
	//리뷰 메인 보기
	@RequestMapping(value = "rev")
	public String review(Review review , String currentPage, Model model) {
		log.info("smController Start review...");
		int totalReview = sm.totalReview();		
		
		//페이징
		Paging page = new Paging(totalReview, currentPage);
		review.setStart(page.getStart());
		review.setEnd(page.getEnd());
		

		List<Review> listReview = sm.listReview(review);
		log.info("smController list listReview.size()=>"+ listReview.size());
				
		model.addAttribute("totalReview", totalReview);
		model.addAttribute("reviewList", listReview);
		model.addAttribute("page", page);
		return "smRev/rev";
	}
	
	//여행지   정보글조회
	@GetMapping(value = "revDetail")
	public String reviewDetail(int rid , Model model ) {
		log.info("smController Start reviewDetail");
		log.info("smController reviewDetail review_id->"+ rid );
		Review review = sm.detailReview(rid);		
		model.addAttribute("review", review);		
		return "smRev/revDetail";
	}
	
	//정보글 작성  페이지 이동
	@GetMapping(value = "revWriteForm")
	public String ReviewWriteForm(Review review, Model model) {
		log.info("smController  ReviewWriteForm Start..." );
		return "smRev/revWriteForm";
	}
	
	
	//정보글 작성
		@PostMapping(value = "revWriteForm")
		public String ReviewWrite(Review Review , Model model) {
			log.info("smController  revWrite Start...");
			
			int insertResult = sm.insertReview(Review);
			log.info("smController revWrite insertResult->"+insertResult );
			
			if (insertResult > 0) {
				return "redirect:rev";}
			else {
				model.addAttribute("msg","입력 실패 확인해 보세요");
				return "forward:smRev/revWriteForm";
			}
			
		}
		

		//정보글 수정 페이지 이동
		@GetMapping(value = "revUpdateForm")
		public String reviewUpdateForm(int review_id, Model model) {
			log.info("ReviewController Start updateForm...");
			Review review = sm.detailReview(review_id);			
			log.info("smController updateFormReview review->" + review);			
			model.addAttribute("review", review);	
			return "smRev/revUpdateForm";
		}	
		
		//정보글 수정 처리
		@PostMapping(value = "updateReview")
		public String updateReview(Review review , Model model) {
			log.info("smController Start update");
			int updateCount = sm.updateReview(review);
			log.info("smController updateReview updateCount ->" + updateCount);
			
			model.addAttribute("uptCnt",updateCount);   // Test Controller간 Data 전달
			model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
			
			return "forward:rev";
		}	
		
		//정보 글 삭제
		@RequestMapping(value = "deleteReview")
		public String deleteReview(int review_id, Model model) {
			log.info("smController Start delete... t_id :" +review_id);
			int result = sm.deleteReview(review_id);			
			return "redirect:rev";
		}

	
}
