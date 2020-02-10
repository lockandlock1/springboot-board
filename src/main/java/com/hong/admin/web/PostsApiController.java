package com.hong.admin.web;


import com.hong.admin.config.auth.LoginUser;
import com.hong.admin.config.auth.dto.SessionUser;
import com.hong.admin.service.posts.PostsService;
import com.hong.admin.service.user.UserService;
import com.hong.admin.web.dto.postsDto.PostsResponseDto;
import com.hong.admin.web.dto.postsDto.PostsSaveRequestDto;
import com.hong.admin.web.dto.postsDto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    private final UserService userService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto, @LoginUser SessionUser user){

        requestDto.setUser(userService.findByEmail(user.getEmail()));

        return postsService.save(requestDto);

    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {

        return postsService.update(id, requestDto);

    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
