package com.travelAlone.s20230404.service.sm;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travelAlone.s20230404.dao.sm.ReviewDao;
import com.travelAlone.s20230404.model.Review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
	public class ReviewServiceImpl implements ReviewService {
		private final ReviewDao sm;

		@Override
		public int totalReview() {
			log.info("reviewServiceImpl start totalReview ");
			int totReviewCnt = sm.totalReview();
			log.info("reviewServiceImpl  totalReview totReviewCnt->" + totReviewCnt );		
			return totReviewCnt;
		}

		@Override
		public List<Review> listReview(Review review) {
			log.debug("reviewServiceImpl Start listReview...");
			List<Review> reviewList = sm.selectReviewList(review);
			log.debug("reviewServiceImpl End listReview...");
			return reviewList;
		}

		@Override
		public Review detailReview(int rid) {
			log.info("reviewServiceImpl detail");
			Review review = null;
			review = sm.detailReview(rid);		
			return review;
		}

		@Override
		public int insertReview(Review review) {
			int result = 0;
			log.info("reviewServiceImpl insert Start...");
			result = sm.insertReview(review);
			return result;
		}

		@Override
		public int updateReview(Review review) {
			log.info("reviewServiceImpl update");
			int updateCount = 0;
			updateCount = sm.updateReview(review);		
			return updateCount;
		}

		@Override
		public int deleteReview(int review_id) {
			int result = 0;
			log.info("reviewServiceImpl delete Start");
			result = sm.deleteReview(review_id);		
			return result;
		}
			
	}

	
