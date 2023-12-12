package com.example.transportexchangeserver.dto.mapper;

import com.example.transportexchangeserver.dto.pojo.CarDTO;
import com.example.transportexchangeserver.dto.pojo.CarOfferDTO;
import com.example.transportexchangeserver.model.Car;
import com.example.transportexchangeserver.model.CarOffer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDTO toDTO(Car model);

    Car toModel(CarDTO dto);
}
