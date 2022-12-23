package com.MbtiProJect.MbtiProJect_ByMaven.controller;

import com.MbtiProJect.MbtiProJect_ByMaven.entity.MbtiQuestionEntity;
import com.MbtiProJect.MbtiProJect_ByMaven.repository.MbtiQuestionEntityRepository;
import com.MbtiProJect.MbtiProJect_ByMaven.service.MbtiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    MbtiService mbtiService;
    @Autowired
    MbtiQuestionEntityRepository mbtiQuestionEntityRepository;

    @GetMapping(value = "/")
    public String main(){
        return "main";
    }
    @GetMapping(value = "/secondPage")
    public String secondPage(Model model, @PageableDefault (page = 1,sort = "id",size = 1,direction = Sort.Direction.ASC)
    Pageable pageable,String result){
        ArrayList<String> valueList=new ArrayList<>();
        //MBTI 결과값 조회하려고 리스트 만들어줘서 결과값을 넣어줌

        model.addAttribute("list",mbtiService.mbtiList(pageable));

        return "secondMainPage";
    }


}
