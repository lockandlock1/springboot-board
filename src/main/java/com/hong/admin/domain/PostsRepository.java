package com.hong.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository  extends JpaRepository<Posts, Long> {
}
