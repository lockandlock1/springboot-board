package com.hong.admin.service.registration;

import com.hong.admin.domain.hashtag.Hashtag;
import com.hong.admin.domain.posts.Posts;
import com.hong.admin.domain.registration.Registration;
import com.hong.admin.domain.registration.RegistrationRepository;
import com.hong.admin.service.hashtag.HashtagService;
import com.hong.admin.web.dto.postsDto.PostsListResponseDto;
import com.hong.admin.web.dto.postsDto.PostsResponseDto;
import com.hong.admin.web.dto.registrationDto.RegistrationUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllByHashtag(Hashtag hashtag) {
        return registrationRepository.findAllPostsByHashtag(hashtag).stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void save(Hashtag hashtag, Posts posts) {
        Registration entity = Registration.builder()
                                          .hashtag(hashtag)
                                          .posts(posts)
                                          .build();

        registrationRepository.save(entity);
    }

    @Transactional
    public void update(RegistrationUpdateDto registrationUpdateDto){
        Posts posts = registrationUpdateDto.getPosts();
        for(Hashtag hashtag: registrationUpdateDto.getTags()){

            registrationRepository.findByHashtagAndAndPosts(hashtag, posts).orElseGet(() -> registrationRepository.save(Registration.builder()
                                                                                                                                    .posts(posts)
                                                                                                                                    .hashtag(hashtag)
                                                                                                                                    .build()));
        }

    }


    @Transactional
    public void deleteAll(Posts entity) {
        registrationRepository.deleteByPosts(entity);
    }

    @Transactional
    public int findRegistraitonByHashtag(Hashtag hashtag) {
        return registrationRepository.findRegistrationByHashtag(hashtag);
    }
}
