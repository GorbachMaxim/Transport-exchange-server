package com.example.transportexchangeserver.controller;

import com.example.transportexchangeserver.dto.CompanyStatistic;
import com.example.transportexchangeserver.dto.pojo.CompanyDTO;
import com.example.transportexchangeserver.model.Offer;
import com.example.transportexchangeserver.repository.CommentRepository;
import com.example.transportexchangeserver.repository.OfferRepository;
import com.example.transportexchangeserver.service.CompanyService;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/statistic")
@CrossOrigin
public class StatisticController {

    @Autowired
    private CompanyService companyService;


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private OfferRepository offerRepository;




    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<CompanyStatistic> getBookReviews(){
        List<CompanyStatistic> statistics = new ArrayList<>();
        List<CompanyDTO> companies = companyService.getAllCompanies();

        for (CompanyDTO company : companies) {
            Double i = 0.0;

            List<Offer> offers = offerRepository.findAllByCompany_Id(company.getId());
            for (Offer offer : offers) {
                i += commentRepository.findCommentsByOffer_id(offer.getId()).size();
            }
            if(offers.size() != 0) {
                i /= offers.size();
            }
            i += (company.getReviews().size()*company.getAvgScore());
            statistics.add(new CompanyStatistic(company.getName(), Precision.round(i, 2)));
        }
        statistics.sort((o1, o2) -> (int) (o1.getPoints() - o2.getPoints()));
        return statistics;
    }


}
