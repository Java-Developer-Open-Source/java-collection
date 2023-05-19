package com.java.developer.javacollection.apis.controllers;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.developer.javacollection.io.entities.Tutorial;
import com.java.developer.javacollection.repositories.TutorialRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/install")
public class EntityInstallationController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch-size}")
    private int batchSize;

    @PostMapping("/install")
    public ResponseEntity<String> installEntity(@RequestParam(defaultValue = "1000") int valueSize) {
        doSomethingAfterStartup(valueSize);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    public void doSomethingAfterStartup(int valueSize) {

        int totalObjects = 1000 + batchSize + valueSize;

        List<Tutorial> tutorials0 = IntStream.range(0, totalObjects)
                .mapToObj(val -> Tutorial.builder().title("Tutorial: " +
                        UUID.randomUUID())
                        .description(UUID.randomUUID().toString())
                        .published(true)
                        .createDateTime(ZonedDateTime.now().toInstant().atZone(ZoneOffset.UTC))
                        .updateDateTime(ZonedDateTime.now().toInstant().atZone(ZoneOffset.UTC))
                        .build())
                .collect(Collectors.toList());

        log.info("Inserting .......... {}...{}", tutorials0, tutorialRepository);

        for (int i = 0; i < totalObjects; i += batchSize) {
            if (i + batchSize > totalObjects) {
                List<Tutorial> tutorials = tutorials0.subList(i, totalObjects - 1);
                tutorialRepository.saveAll(tutorials);
                break;
            }
            List<Tutorial> tutorial1 = tutorials0.subList(i, i + batchSize);
            tutorialRepository.saveAll(tutorial1);
        }

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        Instant instant = zonedDateTime.toInstant();
        ZonedDateTime utcDateTime = instant.atZone(ZoneOffset.UTC);

        log.info("UTC Date Time: {}", utcDateTime);

        log.info("Finished inserting " + totalObjects + " objects");

    }

}
