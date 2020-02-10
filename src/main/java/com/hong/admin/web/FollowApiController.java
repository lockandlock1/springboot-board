package com.hong.admin.web;

import com.hong.admin.config.auth.LoginUser;
import com.hong.admin.config.auth.dto.SessionUser;
import com.hong.admin.service.follow.FollowService;
import com.hong.admin.web.dto.followDto.FollowingRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class FollowApiController {
    private final FollowService followService;

    @PostMapping("/api/v1/following")
    public Long following(@RequestBody FollowingRequestDto requestDto, @LoginUser SessionUser user){
        requestDto.setFromEmail(user.getEmail());

        return followService.following(requestDto);
    }

    @DeleteMapping("/api/v1/follow/cancel")
    public Long cancel(@RequestBody FollowingRequestDto requestDto, @LoginUser SessionUser user) {
        requestDto.setFromEmail(user.getEmail());

        return followService.cancel(requestDto);
    }


}
