package com.example.transportexchangeserver.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String weight;

    private String type;

    @Column(columnDefinition="VARCHAR")
    private String image;

    @Column(columnDefinition="VARCHAR")
    private String description;
}
