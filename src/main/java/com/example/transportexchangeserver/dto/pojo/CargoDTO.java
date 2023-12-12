package com.example.transportexchangeserver.dto.pojo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CargoDTO {
    private long id;

    private String weight;

    private String type;

    private String description;

    private String image;

}
