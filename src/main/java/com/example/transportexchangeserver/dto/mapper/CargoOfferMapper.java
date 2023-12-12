package com.example.transportexchangeserver.dto.mapper;

import com.example.transportexchangeserver.dto.pojo.CargoOfferDTO;
import com.example.transportexchangeserver.model.CargoOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class,  CargoMapper.class})
public interface CargoOfferMapper {
    @Mapping(target = "company.reviews", ignore = true)
    @Mapping(target = "company.username", source = "company.user.username")
    CargoOfferDTO toDTO(CargoOffer model);

    CargoOffer toModel(CargoOfferDTO dto);
}
