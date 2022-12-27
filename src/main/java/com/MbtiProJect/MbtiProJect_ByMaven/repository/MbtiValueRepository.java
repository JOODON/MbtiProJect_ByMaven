package com.MbtiProJect.MbtiProJect_ByMaven.repository;

import com.MbtiProJect.MbtiProJect_ByMaven.entity.MbtiValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MbtiValueRepository extends JpaRepository<MbtiValueEntity,Long> {
    Long countByMbtiKey(String value);

}
