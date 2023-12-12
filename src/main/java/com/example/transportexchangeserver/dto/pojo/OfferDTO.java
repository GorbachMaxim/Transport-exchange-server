package com.example.transportexchangeserver.dto.pojo;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public abstract class OfferDTO {
    private long id;

    String price;

    String fromCity;

    String toCity;


    private CompanyDTO company;

}
