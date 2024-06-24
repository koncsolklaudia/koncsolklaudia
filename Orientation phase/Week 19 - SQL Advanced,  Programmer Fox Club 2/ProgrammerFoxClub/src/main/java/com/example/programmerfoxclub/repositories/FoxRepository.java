package com.example.programmerfoxclub.repositories;

import com.example.programmerfoxclub.models.Fox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoxRepository extends JpaRepository<Fox,Long> {

    @Query("SELECT fox FROM Fox fox WHERE fox.name LIKE %?1%")
    Fox findFoxByNameContainingIgnoreCase(String name);
}
