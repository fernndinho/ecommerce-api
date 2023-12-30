package me.fernndinho.shop.customers.models;

import lombok.Getter;
import lombok.Setter;
import me.fernndinho.shop.payments.models.PaymentEntity;

import javax.persistence.*;
import java.util.List;

@Setter @Getter
@Entity
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String lastname;
    private String email;
    //private AccountEntity account;

    private String phone;
    private String areaCode;

    private String country;
    private String state;
    private String city;
    private String zip;

    @OneToMany(mappedBy = "customer")
    private List<PaymentEntity> payments;
}
