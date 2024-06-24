package com.example.programmerfoxclub.repositories;

import com.example.programmerfoxclub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user WHERE user.username LIKE %?1%")
    User findUserByNameContainingIgnoreCase(String username);
}
