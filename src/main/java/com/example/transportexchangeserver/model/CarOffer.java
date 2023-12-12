package com.example.transportexchangeserver.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class CarOffer extends Offer{
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;
}
