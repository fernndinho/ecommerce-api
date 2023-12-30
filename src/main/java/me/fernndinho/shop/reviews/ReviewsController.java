package me.fernndinho.shop.reviews;

import me.fernndinho.shop.reviews.payload.ReviewCreateRequest;
import me.fernndinho.shop.reviews.payload.ReviewDetailedResponse;
import me.fernndinho.shop.reviews.payload.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews/")
public class ReviewsController {
    @Autowired
    private ReviewsService reviewsService;

    @GetMapping("/customer/{email}") //admin
    public List<ReviewDetailedResponse> getReviewsOfACustomer(@PathVariable("email") String email) {
        return reviewsService.getAllReviewsOfCustomer(email);
    }

    @GetMapping("/products/{slug}") //public
    public List<ReviewResponse> getReviewsOfAProduct(@PathVariable("slug") String product) {
        return reviewsService.getReviewsOfProduct(product);
    }

    @PostMapping("/create") //customer
    public ReviewResponse createReview(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute ReviewCreateRequest request) {
        return reviewsService.createReview(userDetails, request);
    }
}
