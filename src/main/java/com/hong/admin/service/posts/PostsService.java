package com.hong.admin.service.posts;


import com.hong.admin.domain.posts.Posts;
import com.hong.admin.domain.posts.PostsRepository;
import com.hong.admin.domain.registration.Registration;
import com.hong.admin.service.hashtag.HashtagService;
import com.hong.admin.service.registration.RegistrationService;
import com.hong.admin.web.dto.postsDto.PostsListResponseDto;
import com.hong.admin.web.dto.postsDto.PostsResponseDto;
import com.hong.admin.web.dto.postsDto.PostsSaveRequestDto;
import com.hong.admin.web.dto.postsDto.PostsUpdateRequestDto;
import com.hong.admin.web.dto.registrationDto.RegistrationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    private final RegistrationService registrationService;

    private final HashtagService hashtagService;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        Long postId = postsRepository.save(requestDto.toEntity()).getId();

        Posts entity = postsRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + postId));

        RegistrationSaveRequestDto reg = RegistrationSaveRequestDto.builder()
                .hashtags(requestDto.getTitle())
                .posts(entity)
                .build();

        hashtagService.findByTagName(reg.getHashtags());

        return postId;
    }


    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        postsRepository.delete(entity);
    }


}
