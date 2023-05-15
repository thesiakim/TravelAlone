package com.travelAlone.s20230404.domain.km;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 2023-04-14 조경민
 * 설명 : Warning 엔티티의 repository 인터페이스. spring Data JPA 상속받았고 entity와 같은 폴더에 둘 것
 * */
@Repository
public interface WarningRepository extends JpaRepository<WarningJpa, Long> {

    @Query("SELECT count(p) FROM WarningJpa p WHERE p.memberJpa.id = :id")
    Long countByMemberJpa_id(@Param("id") Long id);

}
