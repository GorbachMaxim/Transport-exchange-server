package com.example.transportexchangeserver.dto.mapper;

import com.example.transportexchangeserver.dto.pojo.CarOfferDTO;
import com.example.transportexchangeserver.model.CarOffer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CarOfferMapper.class)
public interface CarOfferListMapper {
    List<CarOffer> toModelList(List<CarOfferDTO> dtos);
    List<CarOfferDTO> toDTOList(List<CarOffer> models);
}
