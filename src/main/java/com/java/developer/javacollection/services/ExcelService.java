package com.java.developer.javacollection.services;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.java.developer.javacollection.io.entities.Tutorial;

public interface ExcelService {

  void save(MultipartFile file);

  List<Tutorial> getAllTutorials(int page, int size);

  InputStream load(int page, int size);

}
