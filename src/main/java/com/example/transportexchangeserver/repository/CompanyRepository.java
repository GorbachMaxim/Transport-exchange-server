package com.example.transportexchangeserver.repository;

import com.example.transportexchangeserver.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> getCompanyByUser_Id(long id);

    List<Company> findByOrderByNameAsc();
}
