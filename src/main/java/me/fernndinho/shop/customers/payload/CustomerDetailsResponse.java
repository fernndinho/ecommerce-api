package me.fernndinho.shop.customers.payload;

import lombok.Data;

@Data
public class CustomerDetailsResponse {
    private String name;
    private String lastname;
    private String email;

    private String phoneNumber;
    private String areaCode;

    private String country;
    private String state;
    private String city;
    private String zip;
}
