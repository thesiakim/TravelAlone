package com.travelAlone.s20230404.dao.km;

import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.*;
import com.travelAlone.s20230404.model.Interest;
import com.travelAlone.s20230404.model.mh.Inquire;

import java.util.List;

public interface MypageDao {

    List<Interest> interestList(long memberId);

    List<ScoreCount> totalScore(long memberId);

    WritingCount wirtingCount(long memberId);

    int memberInfoUpdate(Member member);

    int memberProfileUpdate(Member member);

    int memberWithdrawal(long id);

    List<MypageReviewResponseDto> mypageReviewShow(MypageReviewRequestDto requestDto);

    Member memberInfo(long memberId);

    List<MypageFavoriteResponseDto> kmMypageFavoritesHou(Long id, int startNum);

    List<MypageFavoriteResponseDto> kmMypageFavoritesTra(Long id, int startNum);

    List<MypageFavoriteResponseDto> kmMypageFavoritesRes(Long id, int startNum);

    List<Inquire> myPageInquireList(Inquire inquire);

    int myPageInquireListCnt(Long memberId);

    int kmMypageFavoritesCountRes(Long id);

    int kmMypageFavoritesCountHou(Long id);

    int kmMypageFavoritesCountTra(Long id);

    MypageTagResponseDto mypageInterest(Long id);

    int mypageInterestUpdate(MypageInterestUpdateRequestDto requestDto);

    List<ImgDto> memberAllImgSearchForWithdrawal(long id);

    int deleteMemberAllImgForWithdrawal(long id);
}
