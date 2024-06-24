package com.example.reddit.repositories;

import com.example.reddit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user.password FROM User user WHERE user.userName = ?1")
    String findPassword(String name);
}
