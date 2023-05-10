package com.travelAlone.s20230404.dao.km;

import com.travelAlone.s20230404.model.Interest;
import com.travelAlone.s20230404.model.Member;
import com.travelAlone.s20230404.model.dto.km.*;

import com.travelAlone.s20230404.model.mh.Inquire;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
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

        System.out.println("member = " + member);
        int mypageMemberInfoUpdate = session.update("mypageMemberInfoUpdate", member);
        System.out.println("mypageMemberInfoUpdate = " + mypageMemberInfoUpdate);
        return mypageMemberInfoUpdate;
    }


    /**
     * 2023-05-01 조경민
     * 설명 : 마이페이지 안에서 회원 프로필사진 수정
     * */
    @Override
    public int memberProfileUpdate(Member member) {

        return session.update("mypageMemberProfileUpdate", member);
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 회원탈퇴
     * */

    @Override
    public int memberWithdrawal(long id){

        return session.delete("mypageMemberWithdrawal", id);
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 마이페이지 내 리뷰 보여주기
     * */
    @Override
    public List<MypageReviewResponseDto> mypageReviewShow(MypageReviewRequestDto requestDto) {

        return session.selectList("mypageReviewShow"+requestDto.getCategory(), requestDto);

    }


    /**
     * 2023-05-03 조경민
     * 설명 : 멤버 정보 불러오기
     * */
    @Override
    public Member memberInfo(long memberId) {
        return session.selectOne("memberInfo", memberId);
    }


    //마이페이지 문의내역 리스트 개수 및 페이지확인
    @Override
    public int myPageInquireListCnt(Long memberId) {
        int myPageInquireListCnt = 0;
        log.info("mhDaoImpl myPageInquireListCnt start");

        try {
            myPageInquireListCnt = session.selectOne("mhMyInqListCnt", memberId);
            log.info("mhDaoImpl myPageInquireListCnt -> "+ myPageInquireListCnt);
        } catch (Exception e) {
            log.info("mhDaoImpl myPageInquireListCnt Exception -> "+ e.getMessage());
        }
        return myPageInquireListCnt;
    }

    @Override
    public int kmMypageFavoritesCountRes(Long id) {
        return session.selectOne("kmMypageFavoritesCountRes",id);
    }

    @Override
    public int kmMypageFavoritesCountHou(Long id) {
        return session.selectOne("kmMypageFavoritesCountHou",id);
    }

    @Override
    public int kmMypageFavoritesCountTra(Long id) {
        return session.selectOne("kmMypageFavoritesCountTra",id);
    }

    @Override
    public List<MypageFavoriteResponseDto> kmMypageFavoritesHou(Long id,int startNum) {


        return session.selectList("kmMypageFavoritesHou", id, new RowBounds(startNum, 10));
    }
    @Override
    public List<MypageFavoriteResponseDto> kmMypageFavoritesTra(Long id, int startNum) {

        return session.selectList("kmMypageFavoritesTra", id, new RowBounds(startNum, 10));
    }
    @Override
    public List<MypageFavoriteResponseDto> kmMypageFavoritesRes(Long id, int startNum) {


        return session.selectList("kmMypageFavoritesRes", id, new RowBounds(startNum, 10));
    }



    //마이페이지 문의내역 리스트
    @Override
    public List<Inquire> myPageInquireList(Inquire inquire) {
        List<Inquire> myPageInquireList = null;
        log.info("mhDaoImpl myPageInquireList start");

        try {
            myPageInquireList = session.selectList("mhMyInqList", inquire);
            log.info("mhDaoImpl myPageInquireList size -> "+ myPageInquireList.size());
        } catch (Exception e) {
            log.info("mhDaoImpl myPageInquireList e.getMessage() -> "+ e.getMessage());
        }

        return myPageInquireList;
    }


}
