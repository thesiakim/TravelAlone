package com.travelAlone.s20230404.controller;

import com.travelAlone.s20230404.config.km.Login2User;
import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.config.km.SessionUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.dto.km.*;
import com.travelAlone.s20230404.model.mh.Inquire;
import com.travelAlone.s20230404.service.Paging;
import com.travelAlone.s20230404.service.km.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 2023-05-12 조경민
 * 설명 : 마이페이지 내 화면 출력 컨트롤러(GET)
 * */
@RequiredArgsConstructor
@Controller
public class MypageController {

    private final MypageService mypageService;

    //마이페이지 Controller (MyBatis 사용)---------------------------------------------------------------

    /**
     * 2023-04-24 조경민
     * 설명 : 마이페이지 정보 불러오기
     * */
    @GetMapping("/mypage")
    public String mypageMain(@Login2User SessionUser sessionUser, Model model){

        MypageResponseDto responseDto = mypageService.mypageMain(sessionUser.getId());

        responseDto.addMemberInfo(sessionUser);

        model.addAttribute("response", responseDto);


        return "km/mypage";
    }

    /**
     * 2023-04-26 조경민
     * 설명 : 마이페이지 회원정보 수정창 띄우기
     * */
    @GetMapping("/mypage/member-info")
    public String mypageMemberInfo(@LoginUser MemberJpa memberJpa, Model model){
        model.addAttribute("email", memberJpa.getEmail());
        model.addAttribute("name", memberJpa.getName());
        model.addAttribute("nickName", memberJpa.getNickname());
        model.addAttribute("phone", memberJpa.getPhone());

        return "km/mypage-member-info";
    }


    /**
     * 2023-05-04 조경민
     * 설명 : 마이페이지 프로필 변경창 이동
     * */
    @GetMapping("/mypage/profile")
    public String mypageProfileProfile(@Login2User SessionUser sessionUser, Model model){

        model.addAttribute("storedImgName", sessionUser.getImgStoredFile());

        return "km/mypage-member-profile";
    }

    /**
     * 2023-05-03 조경민
     * 설명 : 회원 탈퇴 창 이동
     * */
    @GetMapping("/mypage/withdrawal")
    public String mypageMemberWithdrawal(@LoginUser MemberJpa memberJpa, Model model){
        model.addAttribute("memberId", memberJpa.getEmail());

        return "km/mypage-member-withdrawal";
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 작성 리뷰(종류별) 가져오기
     * category는 주소창으로, page는 쿼리스트링으로
     * category : {travel, house, restaurant}
     * */
    @GetMapping("/mypage/review")
    public String mypageReview(@RequestParam(required = false) String category,
                               @RequestParam(required = false) Integer page,
                               @LoginUser MemberJpa memberJpa,
                               Model model){
        if (category == null){
            category = "travel";
        }

        if (page == null){
            page = 1;
        }
        List<MypageReviewResponseDto> responseDtos =
                mypageService.mypageReviewShow(new MypageReviewRequestDto(memberJpa.getId(), category, page));

        model.addAttribute("category", category);
        model.addAttribute("page", page);
        model.addAttribute("responseDtos", responseDtos);

        return "km/mypage-review";
    }

    // 마이페이지 문의내역 리스트
    @GetMapping("/mypage/inquire")
    public String myPageInquireList(@LoginUser MemberJpa memberJpa, Inquire inquire, String currentPage, Model model) {

        int myPageInquireListCnt = mypageService.myPageInquireListCnt(memberJpa.getId());

        // Paging 작업
        Paging page = new Paging(myPageInquireListCnt, currentPage);

        // inquire에 추가 setting
        inquire.setStart(page.getStart());
        inquire.setEnd(page.getEnd());
        inquire.setMember_id(memberJpa.getId());

        List<Inquire> myPageInquireList = mypageService.myPageInquireList(inquire);

        model.addAttribute("myPageInquireListCnt", myPageInquireListCnt);
        model.addAttribute("myPageInquireList", myPageInquireList);
        model.addAttribute("page", page);
        model.addAttribute("user_id", memberJpa.getId());

        return "km/mypage-inquire";

    }

    /**
     * 2023-05-09 조경민
     * 설명 : 마이페이지 내 즐겨찾기 모음 화면 이동
     * */
    @GetMapping("/mypage/favorite")
    public String mypageFavorites(@RequestParam(defaultValue = "tra") String category,
                                  @RequestParam(defaultValue = "1") int page,
                                  @Login2User SessionUser sessionUser,
                                  Model model){
        // 해당 카테고리 갯수를 가져오고 페이징 번호처리
        int countFavorite = mypageService.mypageFavoritesPageCount(sessionUser.getId(), category);
        model.addAttribute("totalPage", (countFavorite / 10) +1 );

        // 즐겨찾기 가져오기
        List<MypageFavoriteResponseDto> mypageFavoriteResponseDtos = mypageService.mypageFavorites(sessionUser.getId(), category, page);
        model.addAttribute("favorites",mypageFavoriteResponseDtos);

        return "km/mypage-favorites";
    }


    /**
     * 2023-05-12 조경민
     * 설명 : 마이페이지 태그 변경 화면 출력
     * */
    @GetMapping("/mypage/tag")
    public String mypageTag(@Login2User SessionUser sessionUser,
                            Model model){
        // 내 관심사 태그 현황 불러오기
        MypageTagResponseDto mypageTagResponseDto = mypageService.mypageInterest(sessionUser.getId());
        model.addAttribute("interests", mypageTagResponseDto);

        // 화면 출력
        return "km/mypage-tag";
    }



}
