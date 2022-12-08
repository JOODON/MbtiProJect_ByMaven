package com.MbtiProJect.MbtiProJect_ByMaven.controller;

import com.MbtiProJect.MbtiProJect_ByMaven.dto.MemberFromDto;
import com.MbtiProJect.MbtiProJect_ByMaven.entity.Member;
import com.MbtiProJect.MbtiProJect_ByMaven.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(){

        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String memberFrom(@Valid MemberFromDto memberFromDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/memberForm";
            //에러가 있을시 회원가입 페이지로 이동시켜주는 부분!
        }
        try {
            Member member=Member.createMember(memberFromDto,passwordEncoder);
            //비밀번호를 암호화 시켜주고 아이디값을 검증해서 넘겨줌
            memberService.saveMember(member);

        }catch (IllegalStateException e){
            //중복 가입 예외가 발생할시에 메시지를 띄워줌!
            model.addAttribute("message",e.getMessage());

            return "member/memberForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "member/loginForm";
    }
    @GetMapping(value = "/login/error")
    public String loginError(Model model){

        model.addAttribute("message", "아이디 또는 비밀번호를 확인해주세요");
        model.addAttribute("searchUrl", "members/login");

        return "member/loginForm";
    }
}
