package me.fernndinho.shop.categories.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryCreateRequest {
    private String name;
    private String slug;
    private String description;
}
