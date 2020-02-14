package com.hong.admin.service.follow;

import com.hong.admin.domain.follow.Follow;
import com.hong.admin.domain.follow.FollowRepository;
import com.hong.admin.domain.user.User;
import com.hong.admin.domain.user.UserRepository;
import com.hong.admin.web.dto.followDto.FollowingRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


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
    public List<Follow> findAllFollowings(Long fromId){

        return followRepository.findAllFollowing(fromId);
    }

    @Transactional
    public List<Follow> findAllFollowers(Long toId){

        return followRepository.findAllFollower(toId);
    }

    @Transactional
    public int countFollower(Long toId){

        return followRepository.countByToId(toId);
    }

    @Transactional
    public int coutFollowing(Long fromId){

        return followRepository.countByFromId(fromId);
    }
}
