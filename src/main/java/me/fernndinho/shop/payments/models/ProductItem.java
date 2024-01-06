package me.fernndinho.shop.payments.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import me.fernndinho.shop.products.models.ProductVariantEntity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductItem {
    @ManyToOne
    private ProductVariantEntity variant;

    private int quantity;
}
