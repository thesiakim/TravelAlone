package com.travelAlone.s20230404.dao.km;

import com.travelAlone.s20230404.model.Common;
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

        int mypageMemberInfoUpdate = session.update("mypageMemberInfoUpdate", member);
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
     * 2023-05-13 조경민
     * 설명 :회원 탈퇴를 위한 모든 사진 불러오기
     * */
    @Override
    public List<ImgDto> memberAllImgSearchForWithdrawal(long id) {
        return session.selectList("memberAllImgSearchForWithdrawal", id);
    }

    @Override
    public int deleteMemberAllImgForWithdrawal(long memberId) {
        int result = 0;

        result += session.delete("deleteBodImgByMemberId", memberId);
        result += session.delete("deleteGwrtImgByMemberId", memberId);
        result += session.delete("deleteGnotImgByMemberId", memberId);

        return result;
    }

    /**
     * 2023-05-01 조경민
     * 설명 : 회원탈퇴를 위해 사진 테이블을 제외한 모든 테이블 삭제
     * */

    @Override
    public int memberWithdrawal(long id){

        System.out.println(" 탈퇴 테이블 삭제 "+id);
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

    /**
     * 2023-05-12 조경민
     * kmMypageFavoritesCountRes~Tra
     * 설명 : 마이페이지 내 즐겨찾기 페이징을 위한 총 갯수 불러오기
     * */
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


    /**
     * 2023-05-12 조경민
     * 설명 : 마이페이지 관심사 불러오기
     * */
    @Override
    public MypageTagResponseDto mypageInterest(Long id) {

        List<Common> interestsCommon = session.selectList("mypageInterestAll");

        List<Interest> interestsById = session.selectList("mypageInterestById", id);

        // Common에 보유여부 넣어주기
        for (Common common : interestsCommon){
            for (Interest interest : interestsById){
                // 합한 코드
                String commonCode = common.getBcd() + common.getMcd();

                // 코드가 맞다면 saved 설정
                if (commonCode.equals(interest.getI_common_interest())){
                    common.setSavedChecked(true);
                }

            }
        }

        return new MypageTagResponseDto(interestsCommon, interestsById);
    }

    /**
     * 2023-05-12 조경민
     * 설명 : 마이페이지 관심사 설정
     * */
    @Override
    public int mypageInterestUpdate(MypageInterestUpdateRequestDto requestDto) {
        // 삭제할 리스트 선언
        List<String> deleteList = new ArrayList<>();
        int changeResult=0;

        // 기존에 저장된 데이터를 가져옴
        List<Interest> interestsById = session.selectList("mypageInterestById", requestDto.getId());

        for (Interest interest : interestsById){
            String code = interest.getI_common_interest();

            // 삭제해야될 요소 추가
            if (!requestDto.getSavedTagIds().remove(code)){
                deleteList.add(code);
            }
        }

        if (requestDto.getSavedTagIds().size() >0){
        // 추가할 리스트 쿼리 실행
        changeResult += session.insert("mypageInterestInsert", requestDto);
        }
        if (deleteList.size() >0){
        // 삭제할 리스트 설정 후 쿼리 실행
        requestDto.setSavedTagIds(deleteList);
        changeResult += session.delete("mypageInterestDelete", requestDto);
        }

        return changeResult;
    }



    /**
     * 2023-05-12 조경민
     * kmMypageFavoritesHou~Res
     * 설명 : 마이페이지 내 즐겨찾기 불러오기. 페이징 처리를 위해 RowBounds 사용
     * */
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
