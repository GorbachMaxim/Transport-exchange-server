package com.example.transportexchangeserver.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CargoOffer extends Offer{
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
}
