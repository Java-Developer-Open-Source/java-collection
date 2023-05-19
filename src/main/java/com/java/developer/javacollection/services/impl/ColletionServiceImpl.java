package com.java.developer.javacollection.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.developer.javacollection.io.entities.Tutorial;
import com.java.developer.javacollection.repositories.TutorialRepository;
import com.java.developer.javacollection.services.ColletionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ColletionServiceImpl implements ColletionService {

  private final TutorialRepository repository;

  @Override
  public List<Tutorial> getListTutorials(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<Tutorial> pageTutorials = repository.findAll(pageable);

    Iterable<Tutorial> ens = repository.findAll();
    log.info("{}", ens);

    List<Tutorial> tutorials = pageTutorials.getContent();

    long totalObjects = tutorials.size();

    tutorials.forEach(x -> {
      x.setPublished(false);
    });

    tutorials = tutorials.stream().filter(
        x -> x.isPublished()).collect(Collectors.toList());

    log.info("Total Objects: {}", totalObjects);
    return tutorials;

  }

  @Override
  public Long countAllTutorials() {

    long total = repository.count();
    // Iterable<Tutorial> iterableTutorials = repository.findAll();
    // List<Tutorial> tutorials = StreamSupport.stream(iterableTutorials.spliterator(), false)
    //     .collect(Collectors.toList());

    Long totalObjects = Long.valueOf(total);

    log.info("Total Objects: {}", totalObjects);

    return totalObjects;

  }

}
