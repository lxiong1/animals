package com.acme.animals.controller;

import com.acme.animals.client.AnimalClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme/v1/animals")
public class AnimalController {
    @Autowired
    AnimalClient animalClient;

    @GetMapping
    public String getInformation(@RequestParam("animal") String animal) throws JsonProcessingException {
        return animalClient.getInformation(animal);
    }
}
