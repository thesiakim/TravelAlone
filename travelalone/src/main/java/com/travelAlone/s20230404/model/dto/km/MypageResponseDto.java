package com.travelAlone.s20230404.model.dto.km;

import com.travelAlone.s20230404.config.km.SessionUser;
import com.travelAlone.s20230404.domain.km.MemberJpa;
import com.travelAlone.s20230404.model.Interest;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class MypageResponseDto {

    // 멤버 id, 이미지, 이메일, 이름, 닉네임, 성별, 휴대전화
    private long id;
    private String storedImgName;
    private String email;
    private String name;
    private String nickName;
    private String gender;
    private String phone;


    // 내가 작성한 글, 리뷰, 문의내역, 즐겨찾기 갯수
    private int writingCount;
    private int reviewCount;
    private int csCount;
    private int favCount;

    // 관심사
    private List<Interest> interests= new ArrayList<>();

    // 스코어 합계 및 내용
    private List<ScoreCount> scoreCounts = new ArrayList<>();


    /**
     * 2023-04-26 조경민
     * 회원 기본정보를 MemberJpa를 받아 가져온다
     * */
    public void addMemberInfo(SessionUser sessionUser) {
        this.id = sessionUser.getId();
        this.storedImgName = sessionUser.getImgStoredFile();
        this.email = sessionUser.getEmail();
        this.name = sessionUser.getName();
        this.nickName = sessionUser.getNickname();
        this.phone = sessionUser.getPhone();
        this.gender = sessionUser.getGender().equals("0") ? "남자" : "여자";
    }


    /**
     * 2023-04-26 조경민
     * 설명 : 관심사 리스트를 받아서 저장한다
     * */
    public void addInterest(List<Interest> interests){
        this.interests = interests;
    }


    /**
     * 2023-04-26 조경민
     * 설명 : 받은 점수 리스트를 가져온다
     * */
    public void addScoreCount(List<ScoreCount> scoreCounts){
        this.scoreCounts = scoreCounts;
    }


    /**
     * 2023-04-26 조경민
     * 설명 : writingCount 를 받아 리뷰수, 작성글수, 문의글수, 즐겨찾기 수를 가져옴
     * */
    public void addWritingCount(WritingCount writingCount){
        this.writingCount = writingCount.getBoard_count();
        this.reviewCount = writingCount.getRes_review_count() + writingCount.getTra_review_count() + writingCount.getHou_review_count();
        this.csCount = writingCount.getG_writing_count();
        this.favCount = writingCount.getFavorites_count();
    }

}
