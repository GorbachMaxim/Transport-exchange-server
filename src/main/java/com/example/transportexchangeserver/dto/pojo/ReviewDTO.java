package com.example.transportexchangeserver.dto.pojo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ReviewDTO {

    private long id;

    @Min(1)
    @Max(5)
    private int mark;

    private String text;

    private UserDTO user;
}
