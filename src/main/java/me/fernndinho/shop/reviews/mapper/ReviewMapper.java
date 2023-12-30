package me.fernndinho.shop.reviews.mapper;

import com.google.common.collect.Lists;
import me.fernndinho.shop.reviews.models.ReviewEntity;
import me.fernndinho.shop.reviews.payload.ReviewDetailedResponse;
import me.fernndinho.shop.reviews.payload.ReviewResponse;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewDetailedResponse toDetailedDto(ReviewEntity entity) {
        ReviewDetailedResponse dto = new ReviewDetailedResponse();

        dto.setComment(entity.getComment());
        dto.setName(entity.getCustomer().getName());
        dto.setLastname(entity.getCustomer().getLastname());
        dto.setEmail(entity.getCustomer().getEmail());
        dto.setQualification(entity.getQualification());
        dto.setImages(Lists.newArrayList()); //TODO: fix this

        return dto;
    }

    public ReviewResponse toDto(ReviewEntity entity) {
        ReviewResponse dto = new ReviewResponse();

        dto.setComment(entity.getComment());
        dto.setName(entity.getCustomer().getName());
        dto.setLastname(entity.getCustomer().getLastname());
        dto.setQualification(entity.getQualification());
        dto.setImages(Lists.newArrayList());

        return toDetailedDto(entity);
    }
}
