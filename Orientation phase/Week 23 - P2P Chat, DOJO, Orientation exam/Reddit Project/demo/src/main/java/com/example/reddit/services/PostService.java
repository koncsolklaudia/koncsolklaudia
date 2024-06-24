package com.example.reddit.services;

import com.example.reddit.models.Post;
import org.springframework.data.domain.Page;

public interface PostService {
    Page<Post> findAll(int offset, int size);
    void changeScore(Post post, Long scoringValue);
    Post findById(Long id);
    void createNewPost(Post post, Long userId);
}
