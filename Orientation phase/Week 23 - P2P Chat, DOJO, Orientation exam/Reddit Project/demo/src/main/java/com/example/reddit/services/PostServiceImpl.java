package com.example.reddit.services;

import com.example.reddit.models.Post;
import com.example.reddit.models.User;
import com.example.reddit.repositories.PostRepository;
import com.example.reddit.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final Sort sort;
    private PageRequest pageRequest;
    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.sort = Sort.by("score").descending();
        this.pageRequest = PageRequest.of(0, 10, sort);
    }

    @Override
    public Page<Post> findAll(int offset, int size) {
        Page<Post> page;
        do{
            page = postRepository.findAll(PageRequest.of(offset,size,sort));
            pageRequest = pageRequest.next();
        } while (page.isEmpty());
        return page;
    }

    @Override
    public void changeScore(Post post, Long sumOfScoring) {
        if(sumOfScoring == null){
            post.setScore(0L);
        }
        post.setScore(sumOfScoring);
        postRepository.save(post);
    }

    @Override
    public Post findById(Long id) {
        if (postRepository.findById(id).isPresent()){
            return postRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void createNewPost(Post post, Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            User user = userRepository.findById(userId).get();
            post.setUser(user);
            postRepository.save(post);
        }
    }
}
