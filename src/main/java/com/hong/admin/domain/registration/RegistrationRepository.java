package com.hong.admin.domain.registration;

import com.hong.admin.domain.hashtag.Hashtag;
import com.hong.admin.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("SELECT r.posts from Registration r where r.hashtag = ?1 ")
    List<Posts> findAllPostsByHashtag(Hashtag hashtag);


    Optional<Registration> findByHashtagAndAndPosts(Hashtag hashtag, Posts posts);

    @Query("select count (r) from Registration r where r.hashtag = ?1 ")
    int findRegistrationByHashtag(Hashtag hashtag);

    @Transactional
    @Modifying
    @Query("delete from Registration  r where r.posts = ?1")
    void deleteByPosts(Posts entity);
}

