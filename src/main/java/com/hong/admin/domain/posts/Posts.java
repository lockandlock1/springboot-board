package com.hong.admin.domain.posts;

import com.hong.admin.domain.BaseTimeEntity;
import com.hong.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @Column(name = "POSTS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String author;

    @Column
    private String email;

    @Builder
    public Posts(String title, String content, String author, String email, User user){
        this.title = title;
        this.content = content;
        this.author = author;
        this.email = email;
        this.user = user;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
