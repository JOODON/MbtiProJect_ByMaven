package com.MbtiProJect.MbtiProJect_ByMaven.repository;

import com.MbtiProJect.MbtiProJect_ByMaven.entity.MbtiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionListRepository extends JpaRepository<MbtiEntity,Long> {
    
}
