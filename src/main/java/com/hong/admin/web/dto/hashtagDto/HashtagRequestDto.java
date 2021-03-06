package com.hong.admin.web.dto.hashtagDto;

import com.hong.admin.domain.hashtag.Hashtag;
import com.hong.admin.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HashtagRequestDto  {
    private List<Hashtag> tags;
    private Posts posts;

    @Builder
    HashtagRequestDto(List<Hashtag> tags, Posts posts) {
        this.tags = tags;
        this.posts = posts;
    }


}
