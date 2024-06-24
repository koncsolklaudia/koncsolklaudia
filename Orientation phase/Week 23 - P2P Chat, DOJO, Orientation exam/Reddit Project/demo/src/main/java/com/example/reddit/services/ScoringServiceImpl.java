package com.example.reddit.services;

import com.example.reddit.models.Post;
import com.example.reddit.models.Scoring;
import com.example.reddit.models.User;
import com.example.reddit.repositories.ScoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScoringServiceImpl implements ScoringService{

    public final ScoringRepository scoringRepository;
    @Override
    public void addNewScoring(User user, Post post, Long scoringValue) {
        Optional<Scoring> scoring = scoringRepository.findByUserAndPost(user, post);
        if (scoring.isEmpty()) {
            scoringRepository.save(new Scoring(user, post, scoringValue));
        } else {
            Scoring existing = scoring.get();
            if (existing.getScoring().equals(scoringValue)) {
                existing.setScoring(0L);
            } else {
                existing.setScoring(scoringValue);
            }
            scoringRepository.save(existing);
        }
    }

    @Override
    public Long getSumOfScoring(Long postId) {
        return scoringRepository.sumOfScoresWithTheSameUserIdAndPostId(postId);
    }
}
