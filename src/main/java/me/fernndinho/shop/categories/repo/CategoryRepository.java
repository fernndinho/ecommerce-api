package me.fernndinho.shop.categories.repo;

import me.fernndinho.shop.categories.models.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findBySlug(String slug);

    List<CategoryEntity> findByFather(CategoryEntity father);

    void deleteBySlug(String slug);

    boolean existsBySlug(String slug);

    List<CategoryEntity> findBySlugIn(List<String> slug);
}
