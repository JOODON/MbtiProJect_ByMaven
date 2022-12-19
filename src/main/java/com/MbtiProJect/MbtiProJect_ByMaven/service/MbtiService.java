package com.MbtiProJect.MbtiProJect_ByMaven.service;


import com.MbtiProJect.MbtiProJect_ByMaven.entity.MbtiQuestionEntity;
import com.MbtiProJect.MbtiProJect_ByMaven.repository.MbtiQuestionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MbtiService {
    @Autowired
    private MbtiQuestionEntityRepository mbtiQuestionEntityRepository;

    public Page<MbtiQuestionEntity> mbtiList(Pageable pageable){
        return mbtiQuestionEntityRepository.findAll(pageable);
    }
}
