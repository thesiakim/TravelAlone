package com.travelAlone.s20230404.domain.km;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * 2023-04-14 조경민
 * 설명 : Member 엔티티의 repository 인터페이스. spring Data JPA 상속받았고 entity와 같은 폴더에 둘 것
 * */
@Repository
public interface MemberRepository extends JpaRepository<MemberJpa, Long> {

    Boolean existsByEmail(String email);

    Boolean existsByNickname(String nickname);

    Optional<MemberJpa> findByEmail(String email);

}
