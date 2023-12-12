package com.example.transportexchangeserver.dto.pojo;

import lombok.Data;


import java.util.List;

@Data
public class CompanyDTO {
    private long id;

    private String name;

    private String email;

    private String number;

    private String description;

    private double avgScore;

    private String image;

    private String username;

    private List<ReviewDTO> reviews;
}
