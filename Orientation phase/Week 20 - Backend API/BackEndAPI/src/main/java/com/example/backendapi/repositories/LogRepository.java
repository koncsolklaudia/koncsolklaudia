package com.example.backendapi.repositories;

import com.example.backendapi.models.Log;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface LogRepository extends CrudRepository<Log,Long>{
}
