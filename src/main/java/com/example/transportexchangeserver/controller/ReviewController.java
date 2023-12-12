package com.example.transportexchangeserver.controller;

import com.example.transportexchangeserver.dto.MessageResponse;
import com.example.transportexchangeserver.dto.pojo.ReviewDTO;
import com.example.transportexchangeserver.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/review")
@CrossOrigin
public class ReviewController {

    @Autowired
    private ReviewService reviewService;



    @GetMapping("/{id}")
    public List<ReviewDTO> getCompanyReviews(@PathVariable long id){
        List<ReviewDTO> reviewDTOS = reviewService.getReviewsByCompanyId(id);
        return reviewDTOS;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addReview(HttpServletRequest request, @PathVariable long id, @RequestBody @Valid ReviewDTO review) {
        if(reviewService.addReview(request, review, id))
            return ResponseEntity.ok(new MessageResponse("Review Created"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Can't create a review"));

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(HttpServletRequest request, @PathVariable long id){
        reviewService.deleteReviewById(request, id);
        return ResponseEntity.ok(new MessageResponse("Review DELETED"));
    }
}
