package me.fernndinho.shop.reviews;

import me.fernndinho.shop.customers.models.CustomerEntity;
import me.fernndinho.shop.customers.CustomerRepository;
import me.fernndinho.shop.files.payload.FileResponse;
import me.fernndinho.shop.files.services.LocalFileService;
import me.fernndinho.shop.products.models.ProductEntity;
import me.fernndinho.shop.products.repo.ProductRepository;
import me.fernndinho.shop.reviews.mapper.ReviewMapper;
import me.fernndinho.shop.reviews.models.ReviewEntity;
import me.fernndinho.shop.reviews.payload.ReviewCreateRequest;
import me.fernndinho.shop.reviews.payload.ReviewDetailedResponse;
import me.fernndinho.shop.reviews.payload.ReviewResponse;
import me.fernndinho.shop.shared.error.exceptions.BadRequestException;
import me.fernndinho.shop.shared.error.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewsService {
    @Autowired private ReviewsRepo reviewsRepo;
    @Autowired private CustomerRepository clientRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private LocalFileService fileService; //TODO: replace with FileService
    @Autowired private ReviewMapper mapper;

    public List<ReviewResponse> getReviewsOfProduct(String productSlug) { //TODO: if the request is from an admin should also return the email
        ProductEntity productEntity = productRepo.findBySlug(productSlug)
                .orElseThrow(() -> new BadRequestException("product provided does not exist"));

        List<ReviewEntity> reviews = reviewsRepo.findAllByProduct(productEntity);

        return reviews.stream()
                .map(review -> mapper.toDto(review))
                .collect(Collectors.toList());
    }

    public List<ReviewDetailedResponse> getAllReviewsOfCustomer(String email) {
        CustomerEntity customer = clientRepo.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("customer provided does not exist"));

        List<ReviewEntity> reviews = reviewsRepo.findAllByCustomer(customer);

        return reviews.stream()
                .map(review -> mapper.toDetailedDto(review))
                .collect(Collectors.toList());
    }

    public ReviewResponse createReview(UserDetails details, ReviewCreateRequest request) {
        CustomerEntity customer = clientRepo.findByEmail(details.getUsername())
                .orElseThrow(() -> new BadRequestException("you must be a customer to make a review"));

        ProductEntity productEntity = productRepo.findBySlug(request.getProduct())
                .orElseThrow(() -> new BadRequestException("the product provided does not exist"));

        //TODO: check if the customer has purchased the product and if already exist a review

        ReviewEntity review = new ReviewEntity();

        review.setQualification(request.getQualification());
        review.setComment(request.getComment());
        review.setProduct(productEntity);
        review.setCustomer(customer);

        List<String> urls = fileService.uploadFiles(request.getMedia(), "reviews").stream()
                .map(FileResponse::getUrl)
                .collect(Collectors.toList());

        review.setMedia(urls);

        ReviewEntity saved = reviewsRepo.save(review);

        return new ReviewResponse(customer.getName(), customer.getLastname(),
                saved.getComment(), saved.getQualification(), urls
        );
    }
}
