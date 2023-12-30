package me.fernndinho.shop.payments;

import me.fernndinho.shop.payments.payload.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payments/")
public class PaymentController {
    @Autowired
    private SimplePaymentService service;

    @PostMapping("/pay/")
    public boolean pay(@RequestBody PaymentRequest request)  {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return service.doPayment(details, request);
    }

    @PostMapping("/receive")
    public ResponseEntity<?> receive(@RequestParam Map<String,String> allRequestParams) {
        return ResponseEntity.ok("success");
    }
}
