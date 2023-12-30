package me.fernndinho.shop.products.repo;

import me.fernndinho.shop.products.models.ProductVariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepo extends JpaRepository<ProductVariantEntity, Long> {
    ProductVariantEntity findBySku(String sku);

    List<ProductVariantEntity> findBySkuIn(List<String> sku);
}
