package me.fernndinho.shop.reviews;

import me.fernndinho.shop.customers.models.CustomerEntity;
import me.fernndinho.shop.products.models.ProductEntity;
import me.fernndinho.shop.reviews.models.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepo extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByCustomer(CustomerEntity customer);

    List<ReviewEntity> findAllByProduct(ProductEntity productEntity);
}
