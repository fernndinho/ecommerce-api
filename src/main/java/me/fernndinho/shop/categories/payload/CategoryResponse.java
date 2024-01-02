package me.fernndinho.shop.categories.payload;

import me.fernndinho.shop.categories.models.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class CategoryResponse {
    private String name;
    private String slug;
    private String description;

    private String father;
    private List<String> chids;

    public CategoryResponse(CategoryEntity entity) {
        name = entity.getName();
        slug = entity.getSlug();
        description = entity.getDescription();
    }
}
