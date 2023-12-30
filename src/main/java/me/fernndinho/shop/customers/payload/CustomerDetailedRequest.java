package me.fernndinho.shop.customers.payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerDetailedRequest {
    private String areaCode;
    private String phoneNumber;

    
    private String country;
    private String state;
    private String city;
    private String zip;
}
