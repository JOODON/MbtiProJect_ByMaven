package com.MbtiProJect.MbtiProJect_ByMaven.controller;

import com.MbtiProJect.MbtiProJect_ByMaven.entity.MbtiQuestionEntity;
import com.MbtiProJect.MbtiProJect_ByMaven.entity.MbtiValueEntity;
import com.MbtiProJect.MbtiProJect_ByMaven.repository.MbtiQuestionEntityRepository;
import com.MbtiProJect.MbtiProJect_ByMaven.repository.MbtiValueRepository;
import com.MbtiProJect.MbtiProJect_ByMaven.service.MbtiService;
import com.MbtiProJect.MbtiProJect_ByMaven.service.MbtiValueEntityService;
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
    @Autowired
    MbtiValueEntityService mbtiValueEntityService;

    @Autowired
    MbtiValueRepository mbtiValueRepository;
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
        int pageNum= pageable.getPageNumber();
        //페이지 번호로 배열 처리
        MbtiQuestionEntity mbtiQuestionId=mbtiService.mbtiView((long) pageable.getPageNumber());
        //페이지 넘버랑 롱이랑 매치시켜줌
        MbtiValueEntity mbtiValue=new MbtiValueEntity();

        if (result==null){
            result= String.valueOf(1);
        }
        else if (result.equals("1")&&mbtiQuestionId.getId()<=6){
            mbtiValue.setId((long) pageNum);
            mbtiValue.setMbtiKey("E");
        }
        else if(result.equals("2")&&mbtiQuestionId.getId()<=6){
            mbtiValue.setId(mbtiValue.getId()-2);
            mbtiValue.setMbtiKey("I");
        }
        //S랑 N값 처리
        else if (result.equals("1")&&mbtiQuestionId.getId()<=11&&mbtiQuestionId.getId()>6){
            mbtiValue.setId(mbtiValue.getId()-2);
            mbtiValue.setMbtiKey("N");
        }
        else if(result.equals("2")&&mbtiQuestionId.getId()<=11&&mbtiQuestionId.getId()>6){
            mbtiValue.setId(mbtiValue.getId()-2);
            mbtiValue.setMbtiKey("S");
        }
        //T랑 F값 처리
        else if (result.equals("1")&&mbtiQuestionId.getId()<=16&&mbtiQuestionId.getId()>11){
            mbtiValue.setId(mbtiValue.getId()-2);
            mbtiValue.setMbtiKey("F");
        }
        else if(result.equals("2")&&mbtiQuestionId.getId()<=16&&mbtiQuestionId.getId()>11){
            mbtiValue.setId(mbtiValue.getId()-2);
            mbtiValue.setMbtiKey("T");
        }
        else if (result.equals("1")&&mbtiQuestionId.getId()<=21&&mbtiQuestionId.getId()>16){
            mbtiValue.setId(mbtiValue.getId()-2);
            mbtiValue.setMbtiKey("J");
        }
        else if(result.equals("2")&&mbtiQuestionId.getId()<=21 && mbtiQuestionId.getId()>16){
            mbtiValue.setId(mbtiValue.getId()-2);
            mbtiValue.setMbtiKey("P");
        }
        else{
            return "redirect:/mbti/resultpage";
        }

        mbtiValueEntityService.mbtiResultAdd(mbtiValue);
        return "secondMainPage";
    }


}
