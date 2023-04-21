package com.travelAlone.s20230404.service.sm;

import java.util.List;

import com.travelAlone.s20230404.model.Review;

public interface ReviewService {

	int 					totalReview();
	List<Review> 			listReview(Review review);
	Review 					detailReview(int rid);
	int 					insertReview(Review review);
	int			 			updateReview(Review review);
	int 					deleteReview(int review_id);

	
}
