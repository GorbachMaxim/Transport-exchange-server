package com.example.transportexchangeserver.repository;

import com.example.transportexchangeserver.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findReviewsByCompany_id(long company_id);

}
