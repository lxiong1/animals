package com.acme.animals.client;

import com.acme.animals.model.AnimalInformation;
import com.acme.animals.model.Animals;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class AnimalClient {
    private static final String ANIMALS_ENDPOINT_URL = "https://mcxlmpfy3k.execute-api.us-east-1.amazonaws.com/dev/animals";
    private static final String ANIMAL_QUERY_ENDPOINT_URL = ANIMALS_ENDPOINT_URL + "?animal=";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public String getInformation(String animal) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<>(headers);

        String availableAnimals = restTemplate.exchange(ANIMALS_ENDPOINT_URL, HttpMethod.GET, entity, String.class).getBody();
        Animals animals = objectMapper.readValue(availableAnimals, Animals.class);
        for (String animalName : animals.getAnimals()) {
            if (animal.equals(animalName)) {
                String animalInformationJson = restTemplate.exchange(ANIMAL_QUERY_ENDPOINT_URL + animal, HttpMethod.GET, entity, String.class).getBody();
                AnimalInformation animalInformation = objectMapper.readValue(animalInformationJson, AnimalInformation.class);

                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(animalInformation);
            }
        }

        return "No animal information found for: " + animal;
    }
}
