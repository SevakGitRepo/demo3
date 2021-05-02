package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@RestController
public class FlagController {
    @GetMapping("/{Country}")
    public String returnFlag(@PathVariable String Country){
        RestTemplate restTemplate = new RestTemplate();
        String URL = "https://restcountries.eu/rest/v2/name/"+Country;

        String forNow = "";
        try {
            forNow= Objects.requireNonNull(restTemplate.getForObject(URL, Object.class)).toString();
        }catch (Exception e){}


        String result = Arrays.stream(forNow.split(", "))
                .filter(s->s.startsWith("flag=")).findFirst().orElse("     Country name is incorrect");


        return result.substring(5);

    }
}