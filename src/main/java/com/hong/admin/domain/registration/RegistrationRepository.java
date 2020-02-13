package com.hong.admin.domain.registration;

import com.hong.admin.domain.hashtag.Hashtag;
import com.hong.admin.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("SELECT r.posts from Registration r where r.hashtag = ?1 ")
    List<Posts> findAllPostsByHashtag(Hashtag hashtag);


//    @Query("SELECT p FROM Posts p ORDER BY p.id DESC ")
//    List<Posts> findAllDesc();
}
