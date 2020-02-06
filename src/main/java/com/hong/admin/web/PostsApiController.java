package com.hong.admin.web;

import com.hong.admin.PerfLogging;
import com.hong.admin.service.posts.PostsService;
import com.hong.admin.web.dto.PostsResponseDto;
import com.hong.admin.web.dto.PostsSaveRequestDto;
import com.hong.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        Long id = postsService.save(requestDto);
        return id;
    }

    @PutMapping("/api/v1/posts/{id}")
    public String update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        Long idd = postsService.update(id, requestDto);
//        return postsService.update(id, requestDto);

        return "SUCk";
    }

    @PerfLogging
    @GetMapping("api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
