package com.MbtiProJect.MbtiProJect_ByMaven.config;


import com.MbtiProJect.MbtiProJect_ByMaven.entity.Member;
import com.MbtiProJect.MbtiProJect_ByMaven.repository.MemberRepository;
import com.MbtiProJect.MbtiProJect_ByMaven.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.lang.constant.Constable;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin().loginPage("/members/login")//로그인 페이지 URL을 설정합니다.
                .defaultSuccessUrl("/")//로그인 성공 시 이동할 URL을 설정
                .usernameParameter("email").passwordParameter("memberPassword")//로그인 시 사용할 파라미터 이름으로 email 을 지정합니다.
                .failureUrl("/members/login/error")//로그인 실패시 이동할 URL을 설정
                .and().logout().
                logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))//로그아웃 URL 을 설정
                .logoutSuccessUrl("/");//로그아웃 성공시 이동할 URL 을 설정

        http.authorizeRequests()//시큐리티 처리에 HttpServlet을 이용한다는걸 의미함
                .mvcMatchers("/","/members/**","/item/**","/images/**").permitAll()
                //모든 사용자가 로그인 없이 경롱입장 가능
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                //어드민으로 시작하는 경로는 해당개정이 ADMIN ROLE일 경우에만 접근 가능
                .anyRequest().authenticated();//위와 관련되지 않은 모든것들은 전부다 인증처리!

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        //인증되지 않은 사용자가 리소스에 접근하였을때 수행되는 핸들러를 등록함
    }
        @Override
        public void configure(WebSecurity web) throws Exception{
            web.ignoring().antMatchers("/css/**","/js/**","/img/**");
            //static디렉터리 하위파일은 인증을 무시하도록 설정함
        }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public Constable securitySession(){
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member=(Member) principal;

        String userName=((Member) principal).getEmail();
        String userPassword=((Member) principal).getMemberPassword();

        return userName;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //AuthenticationManager를 통해서 인증을 이루어짐 그걸 생성 userDetailService를 구현하고있는 객체로
        //MemberService를 지정해줌 암호화도 시켜주기!
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

}
