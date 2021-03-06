package com.hong.admin.domain.registration;

import com.hong.admin.domain.BaseTimeEntity;
import com.hong.admin.domain.hashtag.Hashtag;
import com.hong.admin.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Registration extends BaseTimeEntity {

    @Id
    @Column(name = "REGISTRATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "HASHTAG_ID")
    Hashtag hashtag;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    Posts posts;


    @Builder
    public Registration(Hashtag hashtag, Posts posts){
        this.hashtag = hashtag;
        this.posts = posts;
    }

    void setPosts(Posts posts){
        this.posts = posts;
    }

}
