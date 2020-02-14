package com.hong.admin.service.hashtag;

import com.hong.admin.domain.hashtag.Hashtag;
import com.hong.admin.domain.hashtag.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;



    @Transactional
    public List<Hashtag> save(List<String> hashtags) {
        List<Hashtag> list = new ArrayList<>();

        for(String hashtag : hashtags){
           if(!hashtagRepository.existsByTagName(hashtag)){
               hashtagRepository.save(
                       Hashtag.builder()
                               .tagName(hashtag)
                               .build());
           }

           list.add(hashtagRepository.findByTagName(hashtag).orElseThrow(()-> new IllegalArgumentException("존재 하지 않는 태그 입니다.")));
        }

        return list;
    }


    @Transactional
    public Hashtag findByTagName(String tag) {
        return hashtagRepository.findByTagName(tag).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 태그 입니다."));
    }

    @Transactional
    public void deleteAll(List<Hashtag> list) {
       hashtagRepository.deleteAll(list);
    }

}
