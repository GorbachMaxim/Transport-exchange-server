package com.example.transportexchangeserver.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String model;

    private String mass;

    private String volume;

    @Column(columnDefinition="VARCHAR")
    private String image;

    @Column(columnDefinition="VARCHAR")
    private String description;
}
