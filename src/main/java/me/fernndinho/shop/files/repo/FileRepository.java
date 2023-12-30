package me.fernndinho.shop.files.repo;

import me.fernndinho.shop.files.models.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    FileEntity findByPath(String path);

    List<FileEntity> findByPathIn(Collection<String> path);
}
