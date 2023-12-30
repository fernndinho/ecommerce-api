package me.fernndinho.shop.payments.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PaymentRequest {
    private List<ProductItemPayload> items;
    private String coupon;
}
