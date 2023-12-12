package com.example.transportexchangeserver.controller;

import com.example.transportexchangeserver.dto.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin
@AllArgsConstructor
public class CityController {


    static List<City> cities;

    static {
        Resource resource = new ClassPathResource("cities.json");
        try {

            FileReader reader = new FileReader(resource.getFile());
            StringBuilder str = new StringBuilder();
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){

                str.append((char)c);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            cities = (List<City>) objectMapper.readValue(str.toString(), List.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping()
    public List<City> getCities(){
        return cities;
    }


}
