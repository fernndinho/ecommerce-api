package me.fernndinho.shop.categories;

import me.fernndinho.shop.categories.payload.CategoryResponse;
import me.fernndinho.shop.categories.payload.CategoryCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CategoryResponse> createCategory(@Validated @RequestBody CategoryCreateRequest request) {
        CategoryResponse savedCategory = categoryService.createCategory(request);

        return ResponseEntity.status(201).body(savedCategory);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("slug") String slug, @Validated @RequestBody CategoryCreateRequest request) { //
        CategoryResponse updatedCategory = categoryService.updateCategory(slug, request);

        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/delete/{slug}")
    public ResponseEntity<Void> removeCategory(@PathVariable(value = "slug") String slug,
                                               @PathVariable(value = "childs", required = false) boolean removeChilds
    ) {
        categoryService.deleteBySlug(slug, removeChilds);

        return ResponseEntity.noContent().build();
    }
}
