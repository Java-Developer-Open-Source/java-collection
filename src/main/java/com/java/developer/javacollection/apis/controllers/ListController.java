package com.java.developer.javacollection.apis.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.developer.javacollection.io.entities.Tutorial;
import com.java.developer.javacollection.services.ColletionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collection")
public class ListController {
    
    private final ColletionService collectionService;
    
    @GetMapping("/get-list-tutorial")
    public ResponseEntity<List<Tutorial>> getListTutorials(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<List<Tutorial>>(collectionService.getListTutorials(page, size), HttpStatus.OK);
    }

    @GetMapping("/count-all-tutorials")
    public ResponseEntity<Long> countAllTutorials() {
        return new ResponseEntity<Long>(collectionService.countAllTutorials(), HttpStatus.OK);
    }

}
