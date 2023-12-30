package me.fernndinho.shop.account.payload;

import lombok.Data;

@Data
public class AccountLoginRequest {
    private String email;
    private String password;
}
