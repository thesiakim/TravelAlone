package com.travelAlone.s20230404.dao.sm;

import java.util.List;

import com.travelAlone.s20230404.model.Review;

public interface reviewDao {
    int						totalReview();
    List<Review> 			selectReviewList(Review review);
    Review					detailReview(int rid);
    int						insertReview(Review review);
    int 					updateReview(Review review);
	int 					deleteReview(int review_id);
	
}

	

