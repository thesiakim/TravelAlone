package com.travelAlone.s20230404.dao.sm;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.travelAlone.s20230404.model.Review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class reviewDaoImpl implements reviewDao {

	private final SqlSession session;
	
	
    @Override
    public int totalReview() {
    	int totReviewCount = 0;
    	log.info("reviewDaoImpl Start total...");
    	try {
    		totReviewCount =session.selectOne("smReviewTotal");
    		log.info("reviewDaoImpl totalReview totReviewCount->" + totReviewCount);
		} catch (Exception e) {
			log.info("reviewDaoImpl totalReview Exception " +e.getMessage());
		}						
		return totReviewCount;
	}

    
    @Override
    public List<Review> selectReviewList(Review review) {
		List<Review> reviewList = new ArrayList<Review>();
		
	try {
		log.info("reviewDaoImpl selectReviewList reviewReviewList Start...");					
		reviewList = session.selectList("smReviewList", review);
		log.info("reviewDaoImpl selectReviewList reviewReviewList End...");
	} catch (Exception e) {
		log.info("reviewDaoImpl reviewReviewList Exception " +e.getMessage());
	}
		return reviewList;
	}

  //정보글 자세히보기
  	@Override
  	public Review detailReview(int rid) {
  		log.info("reviewDaoImpl detail start..");
  		Review review = new Review();
  		try {
  			review = session.selectOne("smReviewSelOne", rid);
  			log.info("smDaoImpl detail Review.getR_content()->"+ review.getR_content());
  		} catch (Exception e) {
  			log.info("smDaoImpl smReviewSelOne Exception " +e.getMessage());
  		}				
  		return review;
  	}


	@Override
	public int insertReview(Review review) {
		int result = 0;
		log.info("smDaoImpl insert Start...");
		try {
			result = session.insert("insertReview",review);
		} catch (Exception e) {
			log.info("smDaoImpl insert Exception" + e.getMessage());
		}
		
		return result;
	}


	@Override
	public int updateReview(Review review) {
		log.info("smDaoImpl updateReview  start");
		int updateCount= 0;
		try {
			updateCount = session.update("smReviewUpdate",review);
		} catch (Exception e) {
			log.info("reviewDaoImpl updateReview Exception->"+e.getMessage());
		}		
		
		return updateCount;
	}


	@Override
	public int deleteReview(int review_id) {
		log.info("smDaoImpl delete start..");
		int result = 0;
		log.info("smDaoImpl delete review_id->"+ review_id);
		try {
			result = session.delete("deleteReview",review_id);
			log.info("smDaoImpl delete result->"+ result);
		} catch (Exception e) {
			log.info("smDaoImpl delete Exception->"+ e.getMessage());
		}
		
		return result;
	}

	
	
}
