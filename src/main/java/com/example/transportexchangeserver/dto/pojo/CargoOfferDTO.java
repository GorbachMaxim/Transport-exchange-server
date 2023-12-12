package com.example.transportexchangeserver.dto.pojo;

import com.example.transportexchangeserver.model.Car;
import lombok.Data;

@Data
public class CargoOfferDTO extends OfferDTO{
    private CargoDTO cargo;
}
