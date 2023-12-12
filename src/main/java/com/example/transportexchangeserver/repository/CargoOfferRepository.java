package com.example.transportexchangeserver.repository;

import com.example.transportexchangeserver.model.CargoOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoOfferRepository extends JpaRepository<CargoOffer, Long> {
    void deleteByCompany_Id(long id);
}
