package me.fernndinho.shop.products.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.fernndinho.shop.shared.converters.StringListConverter;

import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductVariantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private String sku;
    private String thumbnail;

    @Convert(converter = StringListConverter.class)
    private List<String> images;

    @ElementCollection
    private List<String> colors;

    public void generateSku() {
        StringBuilder sb = new StringBuilder();
        sb.append(product.getSlug()).append(".");

        sb.append(colors.get(0));

        for(String str : colors) {
            sb.append("-");
            sb.append(str);
        }

        this.sku = sb.toString();
    }
}
