package com.hong.admin.web.dto.postsDto;

import com.hong.admin.domain.posts.Posts;
import com.hong.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private String email;
    private User user;


    @Builder
    public PostsSaveRequestDto(String title, String content, String email, User user){
        this.title = title;
        this.content = content;
        this.author = user.getName();
        this.email = user.getEmail();
        this.user = user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Posts toEntity(){
        return Posts.builder()
                    .title(title)
                    .content(content)
                    .author(user.getName())
                    .email(user.getEmail())
                    .user(user)
                    .build();
    }
}
