package com.hong.admin.domain.follow;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFromIdAndToId(Long fromId, Long toId);

    int countByFromId(Long fromId);

    int countByToId(Long toId);

    @Query("select p from Follow p where p.toId = ?1")
    List<Follow> findAllFollower(Long targetId);

    @Query("select p from Follow p where p.fromId = ?1")
    List<Follow> findAllFollowing(Long targetId);
}
