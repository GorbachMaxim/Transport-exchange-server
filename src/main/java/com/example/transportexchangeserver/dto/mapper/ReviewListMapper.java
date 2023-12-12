package com.example.transportexchangeserver.dto.mapper;


import com.example.transportexchangeserver.dto.pojo.ReviewDTO;
import com.example.transportexchangeserver.model.Review;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ReviewMapper.class)
public interface ReviewListMapper {
    List<Review> toModelList(List<ReviewDTO> dtos);
    List<ReviewDTO> toDTOList(List<Review> models);
}
