package com.mentor.gfa.repositories;

import com.mentor.gfa.models.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findByClassroomClassName(String className);

    Mentor findByName(String name);
}
