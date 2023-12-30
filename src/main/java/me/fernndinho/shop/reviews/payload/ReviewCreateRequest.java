package me.fernndinho.shop.reviews.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ReviewCreateRequest {
    private String product;

    private String comment;
    private Integer qualification;
    private List<MultipartFile> media; //TODO: check if works
}
