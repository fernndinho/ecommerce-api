package me.fernndinho.shop.reviews.models;

import me.fernndinho.shop.customers.models.CustomerEntity;
import lombok.Data;
import me.fernndinho.shop.products.models.ProductEntity;
import me.fernndinho.shop.shared.converters.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private Integer qualification;

    @Convert(converter = StringListConverter.class)
    private List<String> media;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product; //should be a variant??
}
