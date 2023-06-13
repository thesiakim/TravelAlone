package com.travelAlone.s20230404.controller;

import com.travelAlone.s20230404.config.km.Login2User;
import com.travelAlone.s20230404.config.km.LoginUser;
import com.travelAlone.s20230404.config.km.SessionUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.Hou_Fav;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.Res_Fav;
import com.travelAlone.s20230404.model.Tra_Fav;
import com.travelAlone.s20230404.model.dto.km.MypageInterestUpdateRequestDto;
import com.travelAlone.s20230404.model.dto.km.MypageMemberWithdrawalRequestDto;
import com.travelAlone.s20230404.service.km.MypageService;
import com.travelAlone.s20230404.service.mh.HouseService;
import com.travelAlone.s20230404.service.si.SiService;
import com.travelAlone.s20230404.service.sk.SkService;
import com.travelAlone.s20230404.service.sm.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * 2023-05-12 조경민
 * 설명 : 마이페이지 내 API 컨트롤러 (POST, PATCH, DELETE)
 * */
@RequiredArgsConstructor
@RestController
public class MypageApiController {

    private final PasswordEncoder passwordEncoder;
    private final MypageService mypageService;
    private final HouseService houseService;
    private final SkService skService;
    private final TravelService travelService;

    //마이페이지 Controller (MyBatis 사용)---------------------------------------------------------------

    /**
     * 2023-04-26 조경민
     * 설명 : 마이페이지 회원정보 변경
     * */
    @PostMapping("/api/v1/mypage/info")
    public String mypageMemberInfoUpdate(@RequestBody Member member, @LoginUser MemberJpa memberJpa){

        member.setMember_id(memberJpa.getId());

        mypageService.memberInfoUpdate(member);

        return "성공";
    }

    /**
     * 2023-04-26 조경민
     * 설명 : 마이페이지 프로필 사진 수정
     * */
    @PostMapping("/api/v1/mypage/profile")
    public String mypageMemberProfileUpdate(@RequestBody List<MultipartFile> file, @LoginUser MemberJpa memberJpa) throws Exception {

        Member member = new Member();
        member.of(memberJpa);

        mypageService.memberProfileUpdate(file, member);

        return "성공";
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 마이페이지 프로필 사진 기본으로 변경
     * */
    @PostMapping("/api/v1/mypage/profile/normal")
    public String mypageMemberProfileReset(@LoginUser MemberJpa memberJpa){

        Member member = new Member();
        member.of(memberJpa);

        mypageService.memberProfileReset(member);
        return "성공";
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 회원 탈퇴
     * */
    @DeleteMapping("/api/v1/mypage/withdrawal")
    @ResponseBody
    public String mypageMemberWithdrawal(@RequestBody MypageMemberWithdrawalRequestDto requestDto, @Login2User SessionUser memberJpa, Model model){

        if (passwordEncoder.matches(requestDto.getPassword(),memberJpa.getPassword()) &&
                requestDto.getMemberEmail().equals(memberJpa.getEmail())){

        	mypageService.memberWithdrawal(memberJpa.getId(), memberJpa.getImgStoredFile());

            System.out.println(" 완료 ");
            return memberJpa.getEmail();
        }else {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            throw new IllegalArgumentException("회원정보가 일치하지 않습니다.");
        }

    }

    /**
     * 2023-05-10 조경민
     * 설명 : 마이페이지 즐겨찾기 취소 혹은 재등록
     * Dto를 만들지 않고 받기 위해 Map 사용
     * */
    @PatchMapping("/api/v1/mypage/favorite")
    public String mypageFavoritesUpdate(@RequestBody Map<String, Object> info,
                                        @Login2User SessionUser sessionUser){
        Long id = ((Integer) info.get("id")).longValue();
        String category =(String) info.get("category");
        boolean checked = (boolean) info.get("checked");


        int result=0;
        switch (category){
            case "hou":
                Hou_Fav hou_Fav = new Hou_Fav();
                hou_Fav.setMember_id(sessionUser.getId());
                hou_Fav.setHouse_id(id);

                result = checked ? houseService.insertHouFav(hou_Fav) : houseService.deleteHouFav(hou_Fav);
                break;
            case "tra":
                Tra_Fav tra_Fav = new Tra_Fav();
                tra_Fav.setMember_id(sessionUser.getId());
                tra_Fav.setTravel_id(id);

                result = checked ? travelService.insertTraFav(tra_Fav) : travelService.deleteTraFav(tra_Fav);
                break;
            case "res":
                Res_Fav res_Fav = new Res_Fav();
                res_Fav.setMember_id(sessionUser.getId());
                res_Fav.setRestaurant_id(id);

                result = checked ? skService.insertResFav(res_Fav) : skService.deleteResFav(res_Fav);
        }

        if (result==0){
            throw new IllegalArgumentException("즐겨찾기 수정 실패");
        }else {
            return "성공";
        }

    }

    /**
     * 2023-05-12 조경민
     * 설명 : 마이페이지에서 관심사를 변경한다
     * */
    @PatchMapping("/api/v1/mypage/interest")
    public String mypageInterest(@RequestBody MypageInterestUpdateRequestDto requestDto,
                                 @Login2User SessionUser sessionUser,
                                 Model model){
        // 접속 id 저장
        requestDto.setId(sessionUser.getId());

        // 변경 갯수 출력
        int insertResult = mypageService.mypageInterestUpdate(requestDto);

        return String.valueOf(insertResult);
    }
}
