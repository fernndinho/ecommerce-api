package me.fernndinho.shop.customers;

import me.fernndinho.shop.customers.models.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByEmail(String email);

}
