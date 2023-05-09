package com.travelAlone.s20230404.model.dto.km;

import java.util.ArrayList;
import java.util.List;

import com.travelAlone.s20230404.model.Interest;
import com.travelAlone.s20230404.model.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPageResponseDto {
	 // 멤버 id, 이미지, 이메일, 이름, 닉네임, 성별, 휴대전화
    private long id;
    private String storedImgName;
    private String email;
    private String nickName;
    private String gender;


    // 관심사
    private List<Interest> interests= new ArrayList<>();

    // 스코어 합계 및 내용
    private List<ScoreCount> scoreCounts = new ArrayList<>();


    /**
     * 2023-04-26 조경민
     * 회원 기본정보를 Member를 받아 가져온다
     * */
    public void addMemberInfo(Member member) {
        this.id = member.getMember_id();
        this.storedImgName = member.getM_img_stored_file();
        this.email = member.getM_email();
        this.nickName = member.getM_nickname();
        
        switch (member.getM_common_gender()) {
		case "0":
			this.gender= "남자";
			break;

		default:
			this.gender = "여자";
			break;
		}
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

}