package com.java.developer.javacollection.repositories;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import com.java.developer.javacollection.io.entities.Tutorial;

public interface TutorialRepository extends CrudRepository<Tutorial, UUID> {

    Page<Tutorial> findAll(Pageable pageable);
    
}
