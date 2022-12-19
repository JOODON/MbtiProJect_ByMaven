package com.MbtiProJect.MbtiProJect_ByMaven.Member;

import com.MbtiProJect.MbtiProJect_ByMaven.entity.MbtiEntity;
import com.MbtiProJect.MbtiProJect_ByMaven.service.MbtiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MbtiEntityTest {
    @Autowired
    MbtiService mbtiService;

    @Test
    public void secondPage(Model model, @PageableDefault(page = 1,sort = "id",size = 1,direction = Sort.Direction.ASC)
    Pageable pageable, String result){

        ArrayList<String> ValueList=new ArrayList<>();
        //MBTI 결과값 조회하려고 리스트 만들어줘서 결과값을 넣어줌

        model.addAttribute("list",mbtiService.mbtiList(pageable));

    }
}
