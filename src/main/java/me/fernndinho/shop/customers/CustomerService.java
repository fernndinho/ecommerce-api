package me.fernndinho.shop.customers;

import me.fernndinho.shop.account.models.AccountEntity;
import me.fernndinho.shop.customers.payload.*;
import me.fernndinho.shop.customers.mapper.CustomerMapper;
import me.fernndinho.shop.customers.models.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repo;
    @Autowired
    private CustomerMapper mapper;

    public CustomerEntity create(UserDetails userDetails, CustomerDetailedRequest request) {
        AccountEntity account = (AccountEntity) userDetails;

        CustomerEntity entity = new CustomerEntity();
        entity.setName(account.getName());
        entity.setLastname(account.getLastname());
        entity.setEmail(account.getEmail());

        entity.setPhone(request.getPhoneNumber());
        entity.setAreaCode(request.getAreaCode());
        entity.setCountry(request.getCountry());
        entity.setState(request.getState());
        entity.setCity(request.getCity());
        entity.setZip(request.getZip());

        CustomerEntity saved = repo.save(entity);

        return saved;
    }

    public List<CustomerCompactResponse> getAllCustomers() {
        return repo.findAll().stream()
                .map(e -> mapper.toCompactDto(e))
                .collect(Collectors.toList());
    }

    public CustomerDetailsResponse getCustomer(Long id) {
        return repo.findById(id)
                .map(e -> mapper.toDetailedDto(e))
                .orElseThrow(() -> new RuntimeException("client not found"));
    }
}


//public ClientLoginResponse login(ClientLogInRequest request) {
        /*Optional<ClientEntity> optionalEntity = repository.findByEmail(request.getEmail());

        if(!optionalEntity.isPresent()) {
            return ClientLoginResponse.builder().message("email or password is not correct").success(false).build();
        }

        ClientEntity client = optionalEntity.get();

        if(!client.getPassword().equals(request.getPassword())) {
            return ClientLoginResponse.builder().message("email or password is not correct").success(false).build();
        }

        //success
        //TODO: DO A JWT
        return ClientLoginResponse.builder().message("correct").success(true).build();*/
//return null;
//}