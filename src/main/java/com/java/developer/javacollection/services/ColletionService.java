package com.java.developer.javacollection.services;

import java.util.List;

import com.java.developer.javacollection.io.entities.Tutorial;

public interface ColletionService {

  List<Tutorial> getListTutorials(int page, int size);

  Long countAllTutorials();

}
