package com.hong.admin.web.dto.registrationDto;

import com.hong.admin.domain.hashtag.Hashtag;
import com.hong.admin.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegistrationUpdateDto {
    private List<Hashtag> tags;
    private Posts posts;

    @Builder
    RegistrationUpdateDto(List<Hashtag> hashtags, Posts posts){
        this.tags = hashtags;
        this.posts = posts;
    }

}