package com.travelAlone.s20230404.service.km;

import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.*;
import com.travelAlone.s20230404.model.mh.Inquire;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MypageService {

    MypageResponseDto mypageMain(long memberId);

    int memberInfoUpdate(Member member);

    int memberProfileUpdate(List<MultipartFile> pictureFile, Member member) throws Exception;

    int memberProfileReset(Member member);

    int memberWithdrawal(long id, String profileImg);

    List<MypageReviewResponseDto> mypageReviewShow(MypageReviewRequestDto requestDto);

    boolean memberInfoRefresh(long memberId);
    // 누락 푸쉬
    UserPageResponseDto userPage(long member_id);

    int myPageInquireListCnt(Long memberId);

    List<Inquire> myPageInquireList(Inquire inquire);

    List<MypageFavoriteResponseDto> mypageFavorites(Long id, String category, int page);

    int mypageFavoritesPageCount(Long id, String category);

    MypageTagResponseDto mypageInterest(Long id);

    int mypageInterestUpdate(MypageInterestUpdateRequestDto requestDto);
}

