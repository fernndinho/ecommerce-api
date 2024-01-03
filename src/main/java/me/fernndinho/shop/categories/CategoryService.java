package me.fernndinho.shop.categories;

import com.google.common.collect.Lists;
import me.fernndinho.shop.categories.models.CategoryEntity;
import me.fernndinho.shop.categories.repo.CategoryRepository;
import me.fernndinho.shop.categories.payload.CategoryResponse;
import me.fernndinho.shop.categories.payload.CategoryCreateRequest;
import me.fernndinho.shop.products.models.ProductEntity;
import me.fernndinho.shop.products.repo.ProductRepository;
import me.fernndinho.shop.shared.error.exceptions.BadRequestException;
import me.fernndinho.shop.shared.error.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ProductRepository productRepo;

    public List<CategoryResponse> getAllCategories() {
        return categoryRepo.findAll().stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryBySlug(String slug) {
        return categoryRepo.findBySlug(slug)
                .map(CategoryResponse::new)
                .orElseThrow(() -> new NotFoundException("category not found"));
    }

    public CategoryResponse createCategory(CategoryCreateRequest categoryPayload) {
        CategoryEntity entity = new CategoryEntity();

        entity.setName(categoryPayload.getName());
        entity.setSlug(categoryPayload.getSlug());
        entity.setDescription(categoryPayload.getDescription());
        entity.setHidden(false);

        CategoryEntity categoryCreated = categoryRepo.save(entity);

        if(categoryPayload.getFather() != null) {
            CategoryEntity father = categoryRepo.findBySlug(categoryPayload.getFather()).get();
            father.getChilds().add(entity);
            categoryRepo.save(father);

            categoryCreated.setFather(father);
        }

        CategoryEntity finalCategory = categoryRepo.save(categoryCreated);

        return new CategoryResponse(finalCategory);
    }

    public CategoryResponse updateCategory(String id, CategoryCreateRequest categoryResponse) { //TODO: implement this
        throw new UnsupportedOperationException();
    }

    public void deleteBySlug(String slug, boolean removeChilds) {
        CategoryEntity category = categoryRepo.findBySlug(slug)
                .orElseThrow(() -> new BadRequestException("category can not be deleted if does not exist"));

        if(category.hasFather()) {
            category.getFather().getChilds().remove(category);

            categoryRepo.save(category.getFather());
        }

        if(category.hasChilds()) {
            if(!removeChilds) {
                List<CategoryEntity> childs = category.getChilds();

                if(category.hasFather()) {
                    childs.forEach(child -> category.getFather().getChilds().addAll(childs));

                    categoryRepo.saveAll(childs);
                    categoryRepo.save(category.getFather());
                } else {
                    childs.forEach(child -> category.setFather(null));
                }
            } else {
                categoryRepo.deleteAll(category.getChilds());
            }
        }

        List<ProductEntity> products = productRepo.findByCategoriesIn(Lists.newArrayList(category));

        products.forEach(productEntity -> {
            productEntity.getCategories().remove(category);
        });
        
        productRepo.saveAll(products);
        
        categoryRepo.delete(category);
    }
}
