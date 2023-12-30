package me.fernndinho.shop.products.models;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.fernndinho.shop.categories.models.CategoryEntity;
import me.fernndinho.shop.shared.converters.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String slug;

    private String description;
    private String details;

    private double price;

    private boolean isNew;

    @Convert(converter = StringListConverter.class)
    private List<String> sizes = Lists.newArrayList();

    @ManyToMany()
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories = Lists.newArrayList();

    @OneToMany(mappedBy = "product"/*, cascade = CascadeType.ALL, orphanRemoval = true*/)
    private List<ProductVariantEntity> variants = Lists.newArrayList();

    //private List<String> tags = Lists.newArrayList();

    private long createdAt;
    private long updatedAt;
}
