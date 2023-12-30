package me.fernndinho.shop.payments.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import me.fernndinho.shop.products.models.ProductVariantEntity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductItem {
    @ManyToOne
    private ProductVariantEntity variant;

    private int quantity;
}
