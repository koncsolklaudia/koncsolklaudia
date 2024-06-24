package com.mentor.gfa.repositories;

import com.mentor.gfa.models.TheClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<TheClass, Long> {
}
