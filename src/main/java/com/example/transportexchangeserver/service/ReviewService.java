package com.example.transportexchangeserver.service;

import com.example.transportexchangeserver.dto.mapper.ReviewListMapper;
import com.example.transportexchangeserver.dto.mapper.ReviewMapper;
import com.example.transportexchangeserver.dto.pojo.ReviewDTO;
import com.example.transportexchangeserver.model.*;
import com.example.transportexchangeserver.repository.CompanyRepository;
import com.example.transportexchangeserver.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ReviewListMapper reviewListMapper;

    public boolean addReview(HttpServletRequest request, ReviewDTO reviewDTO, long id)  {
        Optional<Company> company = companyRepository.findById(id);
        if(!company.isPresent()){
            return false;
        }

        User user = userService.getUserFromJWT(request);

        if(!user.isVerified()){
            return false;
        }

        Review review = reviewMapper.toModel(reviewDTO);
        review.setCompany(company.get());
        review.setUser(user);
        reviewRepository.save(review);
        return true;
    }

    public List<ReviewDTO> getReviewsByCompanyId(long id){
        return reviewListMapper.toDTOList(reviewRepository.findReviewsByCompany_id(id));
    }


    public boolean deleteReviewById(HttpServletRequest request, long id){
        User user1 = userService.getUserFromJWT(request);

        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if(!reviewOptional.isPresent()){
            return false;
        }
        Review review = reviewOptional.get();

        User user2 = review.getUser();

        Optional<Company> company = companyRepository.findById(review.getCompany().getId());
        if(!company.isPresent()){
            return false;
        }

        if(Objects.equals(user1.getId(), user2.getId())){
            company.get().getReviews().remove(review);
            companyRepository.save(company.get());
            reviewRepository.deleteById(review.getId());
            return true;
        }


        for (Role role : user1.getRoles()) {
            if (role.getName() == ERole.ROLE_ADMIN) {
                company.get().getReviews().remove(review);
                companyRepository.save(company.get());
                reviewRepository.deleteById(review.getId());
                return true;
            }
        }
        return false;
    }
}
