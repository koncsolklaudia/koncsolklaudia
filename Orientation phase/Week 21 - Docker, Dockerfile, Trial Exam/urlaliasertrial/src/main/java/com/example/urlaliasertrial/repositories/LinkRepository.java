package com.example.urlaliasertrial.repositories;

import com.example.urlaliasertrial.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {
    Optional<Link> findLinkByUrl(String url);
}
