package me.fernndinho.shop.account.payload;

import lombok.Data;

@Data
public class AccountRegisterRequest {
    private String name;
    private String lastname;
    private String email;
    private String password;
}
