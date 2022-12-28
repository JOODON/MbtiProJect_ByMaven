package com.MbtiProJect.MbtiProJect_ByMaven.controller;

import com.MbtiProJect.MbtiProJect_ByMaven.constant.Role;
import com.MbtiProJect.MbtiProJect_ByMaven.entity.Member;
import com.MbtiProJect.MbtiProJect_ByMaven.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserPageController {
    @Autowired
    MemberService memberService;
    @GetMapping(value = "myPage")
    public String userMyPage(Principal principal, Model model){
       //UserID
        Long userId=memberService.findByUser(principal.getName()).getId();
        String userPassword=memberService.findByUser(principal.getName()).getMemberPassword();
        String userGender=memberService.findByUser(principal.getName()).getGender();
        String userMyMbti=memberService.findByUser(principal.getName()).getMyMbti();
        String userName=memberService.findByUser(principal.getName()).getName();
        String phoneNumber=memberService.findByUser(principal.getName()).getPhoneNumber();
        Role role=memberService.findByUser(principal.getName()).getRole();

        model.addAttribute("userEmail",principal.getName());
        model.addAttribute("userGender",userGender);
        model.addAttribute("userMbti",userMyMbti);
        model.addAttribute("userName",userName);
        model.addAttribute("userPhoneNumber",phoneNumber);
        model.addAttribute("userRole",role);

        return "userPage/myPage";
    }
}
