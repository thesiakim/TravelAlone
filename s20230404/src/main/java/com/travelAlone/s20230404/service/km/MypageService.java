package com.travelAlone.s20230404.service.km;

import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.MypageResponseDto;
import com.travelAlone.s20230404.model.dto.km.MypageReviewRequestDto;
import com.travelAlone.s20230404.model.dto.km.MypageReviewResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MypageService {

    MypageResponseDto mypageMain(long memberId);

    int memberInfoUpdate(Member member);

    int memberProfileUpdate(List<MultipartFile> pictureFile, Member member) throws Exception;

    int memberProfileReset(Member member);

    int memberWithdrawal(long id);

    List<MypageReviewResponseDto> mypageReviewShow(MypageReviewRequestDto requestDto);

    boolean memberInfoRefresh(long memberId);
}
