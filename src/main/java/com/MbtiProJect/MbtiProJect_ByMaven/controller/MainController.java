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

        MbtiValueEntity mbtiValue=mbtiValueEntityService.mbtiValueView((long) pageable.getPageNumber());
        //Value 테이블이랑 View부분이랑 매치해서 결과값 내기
        if (result==null){
            result= String.valueOf(1);
        }
        else if (result.equals("1")&&mbtiQuestionId.getId()<=6){
            mbtiValue.setMbtiKey("E");
        }
        else if(result.equals("2")&&mbtiQuestionId.getId()<=6){
            mbtiValue.setMbtiKey("I");
        }
        //S랑 N값 처리
        else if (result.equals("1")&&mbtiQuestionId.getId()<=11&&mbtiQuestionId.getId()>6){
            mbtiValue.setMbtiKey("N");
        }
        else if(result.equals("2")&&mbtiQuestionId.getId()<=11&&mbtiQuestionId.getId()>6){
            mbtiValue.setMbtiKey("S");
        }
        //T랑 F값 처리
        else if (result.equals("1")&&mbtiQuestionId.getId()<=16&&mbtiQuestionId.getId()>11){
            mbtiValue.setMbtiKey("F");
        }
        else if(result.equals("2")&&mbtiQuestionId.getId()<=16&&mbtiQuestionId.getId()>11){
            mbtiValue.setMbtiKey("T");
        }
        else if (result.equals("1")&&mbtiQuestionId.getId()<=21&&mbtiQuestionId.getId()>16){
            mbtiValue.setMbtiKey("J");
        }
        else if(result.equals("2")&&mbtiQuestionId.getId()<=21 && mbtiQuestionId.getId()>16){
            mbtiValue.setMbtiKey("P");
        }
        else{
            return "redirect:/resultPage";
        }

        mbtiValueEntityService.mbtiResultAdd(mbtiValue);
        return "mainPage/secondMainPage";
    }
    @GetMapping(value = "resultPage")
    public String mbtiResult(Model model){
        String mbtiResulArray[]=new String[4];

        Long eNum=mbtiValueEntityService.mbtiResult("E");
        Long iNum=mbtiValueEntityService.mbtiResult("I");
        if (eNum >iNum) {
            mbtiResulArray[0] = "E";
        }
        else {
            mbtiResulArray[0] = "I";
        }
        Long nNum=mbtiValueEntityService.mbtiResult("N");
        Long sNum=mbtiValueEntityService.mbtiResult("S");
        if (nNum>sNum) {
            mbtiResulArray[1] = "N";
        }
        else {
            mbtiResulArray[1] = "S";
        }
        Long tNum=mbtiValueEntityService.mbtiResult("T");
        Long fNum=mbtiValueEntityService.mbtiResult("F");
        if (tNum >fNum) {
            mbtiResulArray[2] = "T";
        }
        else {
            mbtiResulArray[2] = "F";
        }
        Long pNum=mbtiValueEntityService.mbtiResult("P");
        Long jNum=mbtiValueEntityService.mbtiResult("J");
        if (pNum >jNum) {
            mbtiResulArray[3] = "P";
        }
        else {
            mbtiResulArray[3] = "J";
        }
        String mbtiValue=mbtiResulArray[0]+mbtiResulArray[1]+mbtiResulArray[2]+mbtiResulArray[3];
        model.addAttribute("mbtivalue",mbtiValue);
        return "mainPage/resultPage";

    }
}
