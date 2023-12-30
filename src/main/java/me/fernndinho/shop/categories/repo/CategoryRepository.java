package me.fernndinho.shop.categories.repo;

import me.fernndinho.shop.categories.models.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findBySlug(String slug);

    List<CategoryEntity> findAll();

    void deleteBySlug(String slug);

    boolean existsBySlug(String slug);

    List<CategoryEntity> findBySlugIn(List<String> slug);
}
