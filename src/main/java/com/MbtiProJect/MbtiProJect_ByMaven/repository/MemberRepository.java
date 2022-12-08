package com.MbtiProJect.MbtiProJect_ByMaven.repository;

import com.MbtiProJect.MbtiProJect_ByMaven.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
   //이메일 중복값 확인을 위한 설정
    Member findByEmail(String email);

}
