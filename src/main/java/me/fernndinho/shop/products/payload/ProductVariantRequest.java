package me.fernndinho.shop.products.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantRequest {
    private MultipartFile thumbnail;
    private List<MultipartFile> images;
    private List<String> colors;
}
