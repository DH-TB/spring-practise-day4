package com.example.sum;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final AtomicLong atomicLong = new AtomicLong();

    @GetMapping("/persons")
    public ResponseEntity getSortPerson(@RequestParam(name = "name") String names) {
        if(names == null || names.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Person> sortedPersons = Arrays.stream(names.split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .map(name -> name.replace(" ", ","))
                .sorted()
                .map(name -> new Person(atomicLong.incrementAndGet(), name))
                .collect(Collectors.toList());

        return new ResponseEntity<>(sortedPersons, HttpStatus.OK);
    }
}
