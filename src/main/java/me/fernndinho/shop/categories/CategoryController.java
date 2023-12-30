package me.fernndinho.shop.categories;

import me.fernndinho.shop.categories.payload.CategoryResponse;
import me.fernndinho.shop.categories.payload.CategoryCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getall")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/get/{slug}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable("slug") String slug) {
        CategoryResponse category = categoryService.getCategoryBySlug(slug);

        return ResponseEntity.ok(category);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryCreateRequest request) {
        CategoryResponse savedCategory = categoryService.createCategory(request);

        return ResponseEntity.status(201).body(savedCategory);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("slug") String slug, @Valid @RequestBody CategoryCreateRequest request) { //
        CategoryResponse updatedCategory = categoryService.updateCategory(slug, request);

        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/delete/{slug}")
    public ResponseEntity<Void> removeCategory(@PathVariable("slug") String slug) {
        categoryService.deleteBySlug(slug);

        return ResponseEntity.noContent().build();
    }
}
