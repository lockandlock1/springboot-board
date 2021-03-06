package com.hong.admin.domain.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {


    Optional<Hashtag> findByTagName(String str);

    boolean existsByTagName(String tagName);
}
