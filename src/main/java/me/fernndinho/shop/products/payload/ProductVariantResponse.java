package me.fernndinho.shop.products.payload;

import lombok.Data;

import java.util.List;

@Data
public class ProductVariantResponse {
    private String thumbnail;
    private List<String> images, colors;
}
