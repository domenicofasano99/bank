package com.bok.bank.integration.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface MainController {

    @GetMapping("/db-populator")
    String populateDB();

    @PostMapping("/clean-database")
    String cleanDB();


}