package me.fernndinho.shop.products;

import me.fernndinho.shop.products.payload.ProductCreateRequest;
import me.fernndinho.shop.products.payload.ProductDetailsResponse;
import me.fernndinho.shop.products.payload.ProductQueryRequest;
import me.fernndinho.shop.shared.utils.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getall")
    public PageableResponse<ProductDetailsResponse> getAll(ProductQueryRequest query) {
        return productService.getPaginated(query);
    }

    @GetMapping("/get/{slug}")
    public ProductDetailsResponse get(@PathVariable("slug") String slug) {
        return productService.getProduct(slug);
    }

    @PostMapping("/create") //TODO: this should also accept json requests
    public ProductDetailsResponse create(@ModelAttribute ProductCreateRequest request) {
        return productService.create(request);
    }
}
