package com.example.reddit.services;

import com.example.reddit.models.Post;
import com.example.reddit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoringService {
    void addNewScoring(User user, Post post, Long scoring);
    Long getSumOfScoring(Long postId);
}
