package com.example.transportexchangeserver.dto.pojo;

import lombok.Data;

@Data
public class CarDTO {
    private long id;

    private String model;

    private String mass;

    private String volume;

    private String description;

    private String image;

}
