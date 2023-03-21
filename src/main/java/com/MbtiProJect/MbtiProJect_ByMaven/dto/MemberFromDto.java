package com.MbtiProJect.MbtiProJect_ByMaven.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberFromDto {

    @NotBlank(message = "이름은 필수 입력 값 입니다")//0 또는 "" 문자열인지 검사
    private String name;

    private String email;

    private String memberPassword;

    private String phoneNumber;

    private String Gender;

}
