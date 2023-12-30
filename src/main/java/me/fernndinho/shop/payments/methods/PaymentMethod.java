package me.fernndinho.shop.payments.methods;

public interface PaymentMethod {
    void process();

    void receive();
}
