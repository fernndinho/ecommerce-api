package me.fernndinho.shop.payments.payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductItemPayload {
    private String sku;

    private int quantity;
    private long dateLimit;
}
