package com.example.transportexchangeserver.dto.mapper;

import com.example.transportexchangeserver.dto.pojo.CargoDTO;
import com.example.transportexchangeserver.model.Cargo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CargoMapper {
    CargoDTO toDTO(Cargo model);

    Cargo toModel(CargoDTO dto);
}
