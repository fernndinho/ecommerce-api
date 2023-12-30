package me.fernndinho.shop.customers.payload;

import lombok.Data;

@Data
public class CustomerCompactResponse {
    private String name;
    private String lastname;
    private String email;
}
