package me.fernndinho.shop.products.mapper;

import me.fernndinho.shop.products.payload.ProductCreateRequest;
import me.fernndinho.shop.products.payload.ProductDetailsResponse;
import me.fernndinho.shop.products.payload.ProductVariantRequest;
import me.fernndinho.shop.products.models.ProductEntity;
import me.fernndinho.shop.products.models.ProductVariantEntity;
import me.fernndinho.shop.products.payload.ProductVariantResponse;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapper {

    ProductEntity toEntity(ProductCreateRequest dto);

    ProductDetailsResponse toDto(ProductEntity entity);

    ProductVariantEntity toEntity(ProductVariantResponse dto);

    ProductVariantResponse toDto(ProductVariantEntity entity);
}
