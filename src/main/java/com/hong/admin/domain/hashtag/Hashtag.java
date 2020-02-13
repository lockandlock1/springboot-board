package com.hong.admin.domain.hashtag;

import com.hong.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Hashtag extends BaseTimeEntity {
    @Id
    @Column(name = "HASHTAG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    String tagName;

    @Builder
    public Hashtag(String tagName){
        this.tagName = tagName;
    }
}
