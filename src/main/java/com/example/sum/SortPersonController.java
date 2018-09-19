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
public class SortPersonController {

    private AtomicLong atomicLong = new AtomicLong();

    @GetMapping("/people")
    public ResponseEntity getSortPerson(@RequestParam(value = "nameSource") String nameSource) {
        if(nameSource == null || nameSource.equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Person> persons = Arrays.stream(nameSource.split(","))
                .map(String::toUpperCase)
                .map(name -> name.trim().replace(" ", ","))
                .sorted()
                .map(name -> new Person(atomicLong.incrementAndGet(), name))
                .collect(Collectors.toList());
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }
}
