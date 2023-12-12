package com.example.transportexchangeserver.repository;

import com.example.transportexchangeserver.model.CarOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOfferRepository extends JpaRepository<CarOffer, Long> {
    void deleteByCompany_Id(long id);
}
