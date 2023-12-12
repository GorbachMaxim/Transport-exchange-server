package com.example.transportexchangeserver.repository;

import com.example.transportexchangeserver.model.CarOffer;
import com.example.transportexchangeserver.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllByCompany_Id(long id);
}