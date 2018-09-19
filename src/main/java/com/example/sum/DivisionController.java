package com.example.sum;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DivisionController {

    @GetMapping("/division")
    public ResponseEntity getDivision(@RequestParam(value = "divider") Double divider, @RequestParam(value = "dividend") Double dividend) {
        double result = divider / dividend;

        if (Double.isInfinite(result) || Double.isNaN(result)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
