package me.fernndinho.shop.payments.models;

import com.google.common.collect.Lists;
import me.fernndinho.shop.customers.models.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ElementCollection
    private List<ProductItem> items = Lists.newArrayList();

    private String coupon;
    private double total;

    private long timestamp;
}
