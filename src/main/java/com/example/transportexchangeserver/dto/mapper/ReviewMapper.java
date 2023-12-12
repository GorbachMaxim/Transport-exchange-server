package com.example.transportexchangeserver.dto.mapper;

import com.example.transportexchangeserver.dto.pojo.CompanyDTO;
import com.example.transportexchangeserver.dto.pojo.ReviewDTO;
import com.example.transportexchangeserver.model.Company;
import com.example.transportexchangeserver.model.Review;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(target = "user.roles", ignore = true)
    ReviewDTO toDTO(Review model);

    @Mapping(target = "user.roles", ignore = true)
    Review toModel(ReviewDTO dto);
}
