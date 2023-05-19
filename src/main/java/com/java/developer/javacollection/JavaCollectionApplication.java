package com.java.developer.javacollection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JavaCollectionApplication {

  public static void main(String[] args) {
    SpringApplication.run(JavaCollectionApplication.class, args);
    log.info("Startup....");
  }

}
