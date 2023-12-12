package com.example.transportexchangeserver.dto.mapper;


import com.example.transportexchangeserver.dto.pojo.CommentDTO;
import com.example.transportexchangeserver.dto.pojo.ReviewDTO;
import com.example.transportexchangeserver.model.Comment;
import com.example.transportexchangeserver.model.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface CommentListMapper {
    List<Comment> toModelList(List<CommentDTO> dtos);
    List<CommentDTO> toDTOList(List<Comment> models);
}
