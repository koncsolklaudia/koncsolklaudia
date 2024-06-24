package com.example.foxsanctuary.repositories;

import com.example.foxsanctuary.models.Fox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoxRepository extends JpaRepository<Fox, Long> {
    @Query("SELECT f FROM Fox f WHERE LOWER(f.name) LIKE %?1% OR LOWER(f.species) LIKE %?1%")
    List<Fox> findAllFilterName(String keyword);
}
