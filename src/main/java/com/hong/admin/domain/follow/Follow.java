package com.hong.admin.domain.follow;

import com.hong.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Follow extends BaseTimeEntity {

    @Id
    @Column(name = "FOLLOW_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long fromId;

    @Column
    private Long toId;

    @Builder
    public Follow(Long fromId, Long toId){
        this.fromId = fromId;
        this.toId = toId;
    }

}
