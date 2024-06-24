package com.example.reddit.repositories;

import com.example.reddit.models.Post;
import com.example.reddit.models.Scoring;
import com.example.reddit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoringRepository extends JpaRepository<Scoring, Long> {
    Optional<Scoring> findByUserAndPost(User user, Post post);

    @Query("SELECT SUM(scoring.scoring) FROM Scoring scoring WHERE scoring.post.id = ?1")
    Long sumOfScoresWithTheSameUserIdAndPostId(Long postId);
}
