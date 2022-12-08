package com.MbtiProJect.MbtiProJect_ByMaven.entity;

import com.MbtiProJect.MbtiProJect_ByMaven.constant.Role;
import com.MbtiProJect.MbtiProJect_ByMaven.dto.MemberFromDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter
@ToString
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(unique = true)
    //중복값 제거
    private String email;

    private String memberPassword;

    private String phoneNumber;

    @Column(name = "gender")
    private String Gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFromDto memberFromDto, PasswordEncoder passwordEncoder){
        Member member=new Member();
        member.setName(memberFromDto.getName());
        member.setEmail(memberFromDto.getEmail());
        member.setGender(memberFromDto.getGender());
        member.setPhoneNumber(memberFromDto.getPhoneNumber());

        String password=passwordEncoder.encode(memberFromDto.getMemberPassword());
        //이쪽으로 넘겨서 비밀번호를 암호화 시켜주는 부분!
        member.setMemberPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }

}
