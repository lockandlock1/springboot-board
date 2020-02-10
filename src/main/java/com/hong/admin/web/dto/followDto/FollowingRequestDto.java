package com.hong.admin.web.dto.followDto;

import com.hong.admin.domain.follow.Follow;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowingRequestDto {
    private String fromEmail;
    private String toEmail;

    public void setFromEmail(String fromEmail){
        this.fromEmail = fromEmail;
    }

    @Builder
    public FollowingRequestDto(String fromEmail, String toEmail){
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
    }

    public Follow toEntity(Long fromId, Long toId) {
        return Follow.builder()
                     .fromId(fromId)
                     .toId(toId)
                     .build();
    }

}
