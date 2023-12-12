package com.example.transportexchangeserver.dto.mapper;

import com.example.transportexchangeserver.dto.pojo.CommentDTO;
import com.example.transportexchangeserver.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "user.roles", ignore = true)
    CommentDTO toDTO(Comment model);

    @Mapping(target = "user.roles", ignore = true)
    Comment toModel(CommentDTO dto);
}
