package com.MbtiProJect.MbtiProJect_ByMaven.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "mbtList")
public class MbtiEntity {
    @Id
    @Column(name = "mbti_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String questionText;

    private String value;

}
