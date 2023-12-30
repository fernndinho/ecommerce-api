package me.fernndinho.shop.products.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsResponse {
    private String name;
    private String slug;
    private String description;
    private String details;

    private double price;

    private boolean isNew;

    private List<String> sizes;
    private List<String> categories;
    private List<ProductVariantResponse> variants;
}
