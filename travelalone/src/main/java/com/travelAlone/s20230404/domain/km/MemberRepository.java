package com.travelAlone.s20230404.domain.km;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Query("SELECT p FROM MemberJpa p WHERE p.name = :name AND p.phone = :phone")
    List<MemberJpa> findEmailByNameAndPhone(@Param("name") String name, @Param("phone") String phone);

    @Query("SELECT p FROM MemberJpa p WHERE p.name = :name AND p.email = :email AND p.phone = :phone")
    Optional<MemberJpa> findMemberIdByEmailNamePhone(@Param("name") String name,@Param("email") String email,@Param("phone") String phone);

    @Query("SELECT p FROM MemberJpa p ORDER BY p.createdDate DESC")
    Page<MemberJpa> findAllDesc(Pageable pageable);

    @Query("SELECT p FROM MemberJpa p WHERE p.email LIKE %:search% ORDER BY p.createdDate DESC")
    Page<MemberJpa> findSearchAndAllDesc(@Param("search") String search, Pageable pageable);


}