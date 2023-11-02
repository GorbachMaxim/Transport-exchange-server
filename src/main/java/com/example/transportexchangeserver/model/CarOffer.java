package com.example.transportexchangeserver.model;

import lombok.Data;

import javax.persistence.*;


//@Data
//@Entity
//@Table(name = "car_offer")
public class CarOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

    private String price;

    private String from;

    private String to;
}
