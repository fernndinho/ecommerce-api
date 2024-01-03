package me.fernndinho.shop.payments;

import com.google.common.collect.Maps;
import me.fernndinho.shop.customers.CustomerRepository;
import me.fernndinho.shop.customers.models.CustomerEntity;
import me.fernndinho.shop.payments.models.PaymentEntity;
import me.fernndinho.shop.payments.models.ProductItem;
import me.fernndinho.shop.payments.payload.PaymentRequest;
import me.fernndinho.shop.payments.payload.ProductItemPayload;
import me.fernndinho.shop.payments.repo.PaymentRepository;
import me.fernndinho.shop.products.models.ProductVariantEntity;
import me.fernndinho.shop.products.repo.ProductRepository;
import me.fernndinho.shop.products.repo.ProductVariantRepo;
import me.fernndinho.shop.shared.error.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SimplePaymentService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ProductVariantRepo variantRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private PaymentRepository paymentRepo;

    public boolean doPayment(UserDetails user, PaymentRequest request) {
        CustomerEntity customer = customerRepo.findByEmail(user.getUsername())
                .orElseThrow(() -> new BadRequestException("you must be a customer to complete the payment"));

        PaymentEntity payment = new PaymentEntity();

        Map<String, ProductVariantEntity> variants = getVariants(request.getItems());

        for(ProductItemPayload payload : request.getItems()) {
            ProductItem item = new ProductItem();

            item.setVariant(variants.get(payload.getSku()));
            payment.getItems().add(item);
        }

        payment.setCustomer(customer);
        payment.setTotal(1000);
        payment.setCoupon("cupon");
        payment.setTimestamp(100000L);

        paymentRepo.save(payment);

        return true;
    }

    private Map<String, ProductVariantEntity> getVariants(List<ProductItemPayload> items) {
        Map<String, ProductVariantEntity> map = Maps.newHashMap();

        List<String> skuList = items.stream()
                .map(ProductItemPayload::getSku)
                .collect(Collectors.toList());

        List<ProductVariantEntity> variants = variantRepo.findBySkuIn(skuList);

        for(ProductVariantEntity variant : variants) {
            map.put(variant.getSku(), variant);
        }

        return map;
    }
}
