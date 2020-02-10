package com.hong.admin.service.follow;

import com.hong.admin.domain.follow.Follow;
import com.hong.admin.domain.follow.FollowRepository;
import com.hong.admin.domain.user.User;
import com.hong.admin.domain.user.UserRepository;
import com.hong.admin.web.dto.followDto.FollowingListResponseDto;
import com.hong.admin.web.dto.followDto.FollowingRequestDto;
import com.hong.admin.web.dto.userDto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowService {
    private final FollowRepository followRepository;

    private final UserRepository userRepository;


    @Transactional
    public Long following(FollowingRequestDto requestDto) {
        String fromEmail = requestDto.getFromEmail();
        String toEmail = requestDto.getToEmail();

        User from = userRepository.findByEmail(fromEmail).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. " + fromEmail));
        User to = userRepository.findByEmail(toEmail).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다." + toEmail));

        Long fromId = from.getId();
        Long toId = to.getId();

        return followRepository.save(requestDto.toEntity(fromId, toId)).getId();
    }

    @Transactional
    public Long cancel(FollowingRequestDto requestDto) {
        String fromEmail = requestDto.getFromEmail();
        String toEmail = requestDto.getToEmail();

        User from = userRepository.findByEmail(fromEmail).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. " + fromEmail));
        User to = userRepository.findByEmail(toEmail).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다." + toEmail));

        Long fromId = from.getId();
        Long toId = to.getId();

        Follow entity = followRepository.findByFromIdAndToId(fromId, toId).orElseThrow(() -> new IllegalArgumentException("해당 Follow는 존재하지 않습니다."));

        Long id = entity.getId();

        followRepository.delete(entity);

        return id;
    }

    @Transactional
    public List<Follow> findAllFollowings(Long targetId){
        return followRepository.findAllFollowing(targetId);
    }

    @Transactional
    public List<Follow> findAllFollowers(Long targetId){
        return followRepository.findAllFollower(targetId);
    }
}
