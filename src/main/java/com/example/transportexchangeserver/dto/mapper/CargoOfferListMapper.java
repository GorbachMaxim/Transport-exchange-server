package com.example.transportexchangeserver.dto.mapper;

import com.example.transportexchangeserver.dto.pojo.CargoOfferDTO;
import com.example.transportexchangeserver.model.CargoOffer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CargoOfferMapper.class)
public interface CargoOfferListMapper {
    List<CargoOffer> toModelList(List<CargoOfferDTO> dtos);
    List<CargoOfferDTO> toDTOList(List<CargoOffer> models);
}
