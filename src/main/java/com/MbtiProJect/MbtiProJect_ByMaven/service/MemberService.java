package com.MbtiProJect.MbtiProJect_ByMaven.service;


import com.MbtiProJect.MbtiProJect_ByMaven.entity.Member;
import com.MbtiProJect.MbtiProJect_ByMaven.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional//로직을 처리하다가 오류가 날시 그 전으로 롤백시켜줌
@RequiredArgsConstructor //Autowired대신 이걸로 빈 주입을 해줌
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    //중복 회원가입 방지를 위한 예외처리!
    private void validateDuplicateMember(Member member){
        Member findMember=memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    public Member findByUser(String userId){
        return memberRepository.findByEmail(userId);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member= memberRepository.findByEmail(email);
        if (member==null){
            throw new UsernameNotFoundException(email);
        }
        return User.builder()
                .username(member.getEmail()).password(member.getMemberPassword())
                .roles(member.getRole().toString()).build();
    }

}
