package com.hong.admin.web.dto.postsDto;

import com.hong.admin.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String email;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.email = entity.getEmail();
        this.modifiedDate = entity.getModifiedDate();
    }
}
