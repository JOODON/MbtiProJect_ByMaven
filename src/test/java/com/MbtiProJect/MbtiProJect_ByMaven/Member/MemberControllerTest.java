package com.MbtiProJect.MbtiProJect_ByMaven.Member;


import com.MbtiProJect.MbtiProJect_ByMaven.dto.MemberFromDto;
import com.MbtiProJect.MbtiProJect_ByMaven.entity.Member;
import com.MbtiProJect.MbtiProJect_ByMaven.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberControllerTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(String email, String password){
        MemberFromDto memberFromDto=new MemberFromDto();
        memberFromDto.setEmail("test@email.com");
        memberFromDto.setName("주동호");
        memberFromDto.setMemberPassword("1234");
        Member member=Member.createMember(memberFromDto,passwordEncoder);
        return memberService.saveMember(member);
    }
    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception{
        String email="test@email.com";
        String password="1234";
        this.createMember(email,password);

        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().userParameter("email")
                .loginProcessingUrl("/members/login").user(email).password(password)).andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void loginFailTest() throws Exception{
        String email="test@email.com";
        String password="1234";
        this.createMember(email,password);

        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().userParameter("email")
                .loginProcessingUrl("/members/login").user(email).password("12345")).andExpect(SecurityMockMvcResultMatchers.authenticated());
    }
}
