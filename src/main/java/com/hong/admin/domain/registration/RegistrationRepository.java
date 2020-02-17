package com.hong.admin.domain.registration;

import com.hong.admin.domain.hashtag.Hashtag;
import com.hong.admin.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {


    Optional<Registration> findByHashtagAndAndPosts(Hashtag hashtag, Posts posts);


    @Query("select count (r) from Registration r where r.hashtag = ?1 ")
    int findRegistrationByHashtag(Hashtag hashtag);


    // JPA Named Query
    int countRegistrationsByHashtag(Hashtag hashtag);



    @Transactional
    @Modifying
    @Query("delete from Registration  r where r.posts = ?1")
    void deleteByPosts(Posts entity);


    //JPA Named Query
    void deleteByPosts(Posts entity);




    @Query("SELECT r.posts from Registration r where r.hashtag = ?1 ")
    List<Posts> findAllPostsByHashtag(Hashtag hashtag);

    List findByPosts_Id(@Param(value = "HASHTAG_ID")int hashtagId);






}

