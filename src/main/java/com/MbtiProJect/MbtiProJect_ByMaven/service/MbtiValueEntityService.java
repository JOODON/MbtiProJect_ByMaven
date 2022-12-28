package com.MbtiProJect.MbtiProJect_ByMaven.service;

import com.MbtiProJect.MbtiProJect_ByMaven.entity.MbtiValueEntity;
import com.MbtiProJect.MbtiProJect_ByMaven.repository.MbtiValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MbtiValueEntityService {
    @Autowired
    private MbtiValueRepository mbtiValueRepository;

    public void mbtiResultAdd(MbtiValueEntity mbtiValue){
        mbtiValueRepository.save(mbtiValue);
    }
    public MbtiValueEntity mbtiValueView(Long id){
        return mbtiValueRepository.findById(id).get();
    }
    public Long mbtiResult(String keyWord){
        return mbtiValueRepository.countByMbtiKey(keyWord);
    }
}
