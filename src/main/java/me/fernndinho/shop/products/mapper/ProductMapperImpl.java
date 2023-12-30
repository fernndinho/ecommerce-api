package me.fernndinho.shop.products.mapper;

import me.fernndinho.shop.categories.models.CategoryEntity;
import me.fernndinho.shop.products.payload.ProductCreateRequest;
import me.fernndinho.shop.products.payload.ProductDetailsResponse;
import me.fernndinho.shop.products.models.ProductEntity;
import me.fernndinho.shop.products.models.ProductVariantEntity;
import me.fernndinho.shop.products.payload.ProductVariantResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductEntity toEntity(ProductCreateRequest dto) {
        ProductEntity entity = new ProductEntity();

        entity.setName(dto.getName());
        entity.setSlug(dto.getSlug());
        entity.setDescription(dto.getDescription());
        entity.setDetails(dto.getDetails());
        entity.setPrice(dto.getPrice());
        entity.setNew(dto.isNew());
        entity.setSizes(dto.getSizes());

        return entity;
    }

    @Override
    public ProductDetailsResponse toDto(ProductEntity entity) {
        ProductDetailsResponse dto = new ProductDetailsResponse();

        dto.setName(entity.getName());
        dto.setSlug(entity.getSlug());
        dto.setDescription(entity.getDescription());
        dto.setDetails(entity.getDetails());
        dto.setPrice(entity.getPrice());
        dto.setNew(entity.isNew());
        dto.setSizes(entity.getSizes());

        List<String> categories = entity.getCategories().stream()
                .map(CategoryEntity::getSlug)
                .collect(Collectors.toList());

        List<ProductVariantResponse> variants = entity.getVariants().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        dto.setCategories(categories);
        dto.setVariants(variants);

        return dto;
    }

    @Override
    public ProductVariantEntity toEntity(ProductVariantResponse dto) {
        ProductVariantEntity entity = new ProductVariantEntity();

        entity.setThumbnail(dto.getThumbnail());
        entity.setColors(dto.getColors());
        entity.setImages(dto.getImages());

        return entity;
    }

    @Override
    public ProductVariantResponse toDto(ProductVariantEntity entity) {
        ProductVariantResponse dto = new ProductVariantResponse();

        //dto.setProduct(entity.getProduct().getSlug());
        dto.setThumbnail(entity.getThumbnail());
        dto.setImages(entity.getImages());
        dto.setColors(entity.getColors());

        return dto;
    }
}
