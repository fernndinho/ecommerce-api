package me.fernndinho.shop.payments.repo;

import me.fernndinho.shop.customers.models.CustomerEntity;
import me.fernndinho.shop.payments.models.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    PaymentEntity findByCustomer(CustomerEntity customer);
}
