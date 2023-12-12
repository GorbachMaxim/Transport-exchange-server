package com.example.transportexchangeserver.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "offer")
public abstract class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String price;

    String fromCity;

    String toCity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

//    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Comment> comments;
}
