package com.hong.admin.service.posts;


import com.hong.admin.domain.hashtag.Hashtag;
import com.hong.admin.domain.posts.Posts;
import com.hong.admin.domain.posts.PostsRepository;
import com.hong.admin.service.hashtag.HashtagService;
import com.hong.admin.service.registration.RegistrationService;
import com.hong.admin.web.dto.postsDto.PostsListResponseDto;
import com.hong.admin.web.dto.postsDto.PostsResponseDto;
import com.hong.admin.web.dto.postsDto.PostsSaveRequestDto;
import com.hong.admin.web.dto.postsDto.PostsUpdateRequestDto;
import com.hong.admin.web.dto.registrationDto.RegistrationSaveRequestDto;
import com.hong.admin.web.dto.registrationDto.RegistrationUpdateDto;
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

        List<Hashtag> list = hashtagService.save(reg.getHashtags());


        for(Hashtag hashtag : list){
            registrationService.save(hashtag, entity);
        }


        return postId;
    }


    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));

        // Registration Table 수정, Tagetable 확인
        posts.update(requestDto.getTitle(), requestDto.getContent());

        RegistrationSaveRequestDto reg = RegistrationSaveRequestDto.builder()
                .hashtags(requestDto.getTitle())
                .posts(posts)
                .build();

        List<Hashtag> list = hashtagService.save(reg.getHashtags());

        RegistrationUpdateDto registrationUpdateDto = RegistrationUpdateDto.builder()
                                                                            .hashtags(list)
                                                                            .posts(posts)
                                                                            .build();
        registrationService.update(registrationUpdateDto);

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllByOrderByIdDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
//        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        // Registraetion삭제, 태그는 쓰이는 곳이 없다면 삭제
        RegistrationSaveRequestDto reg = RegistrationSaveRequestDto.builder()
                .hashtags(entity.getTitle())
                .posts(entity)
                .build();

        List<Hashtag> hashtaglist = hashtagService.save(reg.getHashtags());


        registrationService.deleteAll(entity);

        List<Hashtag> deleteHashtaglist = new ArrayList<>();
        for(Hashtag hashtag : hashtaglist) {
            if(registrationService.findRegistraitonByHashtag(hashtag) < 1){
                deleteHashtaglist.add(hashtag);
            }
        }

        hashtagService.deleteAll(deleteHashtaglist);

        postsRepository.delete(entity);
    }


}
