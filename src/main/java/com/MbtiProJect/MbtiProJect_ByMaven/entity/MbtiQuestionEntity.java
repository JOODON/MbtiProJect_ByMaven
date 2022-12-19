package com.MbtiProJect.MbtiProJect_ByMaven.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "mbtiquestionentity")
public class MbtiQuestionEntity {
    @Id
    @Column(name = "mbti_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "questiontext")
    private String questionText;

    @Column(name = "value")
    private String value;

}
