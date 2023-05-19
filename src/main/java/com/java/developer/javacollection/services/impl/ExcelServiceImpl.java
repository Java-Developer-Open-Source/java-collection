package com.java.developer.javacollection.services.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.developer.javacollection.helper.ExcelHelper;
import com.java.developer.javacollection.io.entities.Tutorial;
import com.java.developer.javacollection.repositories.TutorialRepository;
import com.java.developer.javacollection.services.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService {

  @Autowired
  private TutorialRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Tutorial> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    List<Tutorial> tutorials = repository.findAll(pageable).getContent();

    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
    return in;
  }

  public List<Tutorial> getAllTutorials(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return repository.findAll(pageable).getContent();
  }

}
