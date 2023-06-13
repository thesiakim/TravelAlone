package com.travelAlone.s20230404.domain.km;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 2023-05-15 조경민
 * 설명 : 회원클래스. BaseTimeEntity를 상속받아 생성일 수정일 자동생성, 시퀀스 Member_ID_SEQ 사용
 * */

@Getter
@NoArgsConstructor
@SequenceGenerator(
        name="W_ID_SEQ", //시퀀스 제너레이터 이름
        sequenceName="W_ID_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
@Entity
@Table(name = "WARNING")
public class WarningJpa {
    @Id
    @Column(name = "w_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="W_ID_SEQ" //식별자 생성기를 설정해놓은  W_ID_SEQ으로 설정
    )
    private Long id;

    // MemberJpa와 관계 설정을 위해 다대일 매핑
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberJpa memberJpa;

    @Column(name = "u_nickname")
    private String nickname;
    @Column(name = "w_common_warning")
    private String wCommonWarning;

}
