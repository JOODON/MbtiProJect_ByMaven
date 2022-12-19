package com.MbtiProJect.MbtiProJect_ByMaven.service;

import com.MbtiProJect.MbtiProJect_ByMaven.entity.MbtiEntity;
import com.MbtiProJect.MbtiProJect_ByMaven.repository.QuestionListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MbtiService {
    @Autowired
    private QuestionListRepository questionListRepository;

    public Page<MbtiEntity> mbtiList(Pageable pageable){
        return questionListRepository.findAll(pageable);
    }
}
