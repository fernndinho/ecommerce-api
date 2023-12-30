package me.fernndinho.shop.reviews.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class ReviewResponse {
    private String name;
    private String lastname;
    private String comment;
    private Integer qualification;
    private List<String> images;
}
