package me.fernndinho.shop.products;

import me.fernndinho.shop.account.models.AccountType;
import me.fernndinho.shop.products.payload.ProductCreateRequest;
import me.fernndinho.shop.products.payload.ProductDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/getall")
    public List<ProductDetailsResponse> getAll() {
        return productService.getAll();
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
