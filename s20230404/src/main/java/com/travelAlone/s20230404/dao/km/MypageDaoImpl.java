package com.travelAlone.s20230404.dao.km;

import com.travelAlone.s20230404.model.Interest;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.MypageMemberInfoUpdateRequestDto;
import com.travelAlone.s20230404.model.dto.km.ScoreCount;
import com.travelAlone.s20230404.model.dto.km.WritingCount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MypageDaoImpl implements MypageDao{

    private final SqlSession session;

    /**
     * 2023-04-26 조경민
     * 설명 : 관심사 리스트를 가져온다
     * */
    @Override
    public List<Interest> interestList(long memberId) {
        List<Interest> interestList = new ArrayList<>();
        try {
            interestList = session.selectList("interestFindById", memberId);
        } catch (Exception e){
            System.out.println("interestList error = " + e.getMessage());
        }


        return interestList;
    }


    /**
     * 2023-04-26 조경민
     * 설명 : 회원이 받은 리뷰의 갯수를 가져온다
     * */
    @Override
    public List<ScoreCount> totalScore(long memberId) {
        return session.selectList("scoreCountById",memberId);
    }


    /**
     * 2023-04-26 조경민
     * 설명 : 회원이 작성한 글의 갯수를 가져온다
     * */
    @Override
    public WritingCount wirtingCount(long memberId) {
        return session.selectOne("writingCountById", memberId);
    }


    /**
     * 2023-04-26 조경민
     * 설명 : 마이페이지 안에서 회원정보 수정
     * */
    @Override
    public int memberInfoUpdate(Member member) {
        return session.update("mypageMemberInfoUpdate", member);
    }
}
