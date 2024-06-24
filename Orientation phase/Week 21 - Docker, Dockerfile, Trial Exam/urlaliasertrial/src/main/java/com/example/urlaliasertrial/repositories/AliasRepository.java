package com.example.urlaliasertrial.repositories;

import com.example.urlaliasertrial.models.Alias;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface AliasRepository extends JpaRepository<Alias, Integer> {
    boolean existAliasByAlias(String alias);
    Optional<Alias> findAliasByAlias(String alias);

    @Modifying
    @Transactional
    @Query("UPDATE Alias SET hitCount = hitCount + 1 WHERE id = :id")
    void incrementHitCount(Integer id);
}
