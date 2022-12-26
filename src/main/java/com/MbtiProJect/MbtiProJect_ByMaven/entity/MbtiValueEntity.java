package com.MbtiProJect.MbtiProJect_ByMaven.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "mbtivalue")
public class MbtiValueEntity {
    @Id
    @Column(name = "mbti_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "mbti_key")
    private String mbtiKey;

}
