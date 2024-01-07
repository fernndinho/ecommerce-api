package me.fernndinho.shop.account;

import me.fernndinho.shop.account.payload.AccountLoginRequest;
import me.fernndinho.shop.account.payload.AccountRegisterRequest;
import me.fernndinho.shop.account.models.AccountEntity;
import me.fernndinho.shop.account.models.AccountType;
import me.fernndinho.shop.account.payload.AccountResponse;
import me.fernndinho.shop.account.repo.AccountRepository;
import me.fernndinho.shop.shared.error.exceptions.ConflictException;
import me.fernndinho.shop.shared.error.exceptions.NotAuthorizedException;
import me.fernndinho.shop.shared.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwt;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("the user does not exist"));
    }

    public AccountResponse register(AccountRegisterRequest request) {
        if(accountRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("that email its already registered");
        }

        String email = request.getEmail();
        String encodedPass = passwordEncoder.encode(request.getPassword());

        //TODO: send a confirmation email
        AccountEntity entity = new AccountEntity(null,request.getName(), request.getLastname(), email, encodedPass, AccountType.CUSTOMER);
        AccountEntity saved = accountRepository.save(entity);

        String token = jwt.generateToken(saved);

        return new AccountResponse(token, saved.getType());
    }

    public AccountResponse login(AccountLoginRequest request) {
        if(!accountRepository.existsByEmail(request.getEmail())) {
            throw new NotAuthorizedException("the password or email are invalid");
        }

        AccountEntity entity = accountRepository.findByEmail(request.getEmail()).get();

        if(!passwordEncoder.matches(request.getPassword(), entity.getPassword())) {
            throw new NotAuthorizedException("the password or email are invalid");
        }

        String token = jwt.generateToken(entity);

        return new AccountResponse(token, entity.getType());
    }
}
