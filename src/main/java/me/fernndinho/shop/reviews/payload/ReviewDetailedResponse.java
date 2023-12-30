package me.fernndinho.shop.reviews.payload;


import lombok.Data;

@Data
public class ReviewDetailedResponse extends ReviewResponse {
    private String email;
}
