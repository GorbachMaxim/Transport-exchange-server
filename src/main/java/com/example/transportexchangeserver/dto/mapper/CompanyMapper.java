package com.example.transportexchangeserver.dto.mapper;

import com.example.transportexchangeserver.dto.pojo.CompanyDTO;
import com.example.transportexchangeserver.model.Company;
import com.example.transportexchangeserver.model.Review;
import org.apache.commons.math3.util.Precision;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = ReviewListMapper.class)
public interface CompanyMapper {



    @Named("avgScoreCount")
    static double avgScoreCount(List<Review> reviews) {
        double avgScore = 0;
        if (reviews.size()>0){
            for (Review review : reviews) {
                avgScore += review.getMark();
            }
            Double averageMark = Precision.round(avgScore / reviews.size(), 2);
            avgScore = averageMark.doubleValue();
        }
        return avgScore;
    }

    @Mapping(source = "reviews", target = "avgScore", qualifiedByName = "avgScoreCount")
    @Mapping(target = "username", source = "user.username")
    CompanyDTO toDTO(Company model);

    Company toModel(CompanyDTO dto);
}
