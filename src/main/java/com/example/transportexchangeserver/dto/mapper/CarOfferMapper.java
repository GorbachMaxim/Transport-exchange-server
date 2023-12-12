package com.example.transportexchangeserver.dto.mapper;

import com.example.transportexchangeserver.dto.pojo.CarOfferDTO;
import com.example.transportexchangeserver.dto.pojo.CompanyDTO;
import com.example.transportexchangeserver.model.Car;
import com.example.transportexchangeserver.model.CarOffer;
import com.example.transportexchangeserver.model.CargoOffer;
import com.example.transportexchangeserver.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class,  CarMapper.class})
public interface CarOfferMapper {
    @Mapping(target = "company.reviews", ignore = true)
    @Mapping(target = "company.username", source = "company.user.username")
    CarOfferDTO toDTO(CarOffer model);

    CarOffer toModel(CarOfferDTO dto);
}
