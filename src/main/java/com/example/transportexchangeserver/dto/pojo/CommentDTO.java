package com.example.transportexchangeserver.dto.pojo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class CommentDTO {

    private long id;

    private String text;

    private UserDTO user;
}
