package com.hong.admin.web.dto.followDto;

import com.hong.admin.domain.user.User;
import lombok.Getter;

@Getter
public class FollowingListResponseDto {
    private Long followingId;

    public FollowingListResponseDto(User entity) {
        this.followingId = entity.getId();
    }
}
