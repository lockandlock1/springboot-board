package com.hong.admin.service.hashtag;

import com.hong.admin.domain.hashtag.Hashtag;
import com.hong.admin.domain.hashtag.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    @Transactional
    public List<Hashtag> findByTagName(List<String> hashtags) {
        
    }
}
