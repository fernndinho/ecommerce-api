package me.fernndinho.shop.account;

import me.fernndinho.shop.account.payload.AccountLoginRequest;
import me.fernndinho.shop.account.payload.AccountRegisterRequest;
import me.fernndinho.shop.account.payload.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public AccountResponse login(@RequestBody AccountLoginRequest request) {
        return accountService.login(request);
    }

    @PostMapping("/register")
    public AccountResponse register(@RequestBody AccountRegisterRequest request) {
        return accountService.register(request);
    }
}
