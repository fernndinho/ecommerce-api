package me.fernndinho.shop.account.repo;

import me.fernndinho.shop.account.models.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
