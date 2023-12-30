package me.fernndinho.shop.customers;

import me.fernndinho.shop.customers.payload.CustomerCompactResponse;
import me.fernndinho.shop.customers.payload.CustomerDetailedRequest;
import me.fernndinho.shop.customers.payload.CustomerDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getall")
    @Secured("ROLE_ADMIN")
    public List<CustomerCompactResponse> getAll() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/get/{id}")
    @Secured("ROLE_ADMIN") //TODO:
    public CustomerDetailsResponse getByID(@PathVariable("id") Long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping("/details")
    public ResponseEntity<?> setDetails(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CustomerDetailedRequest request) {
        customerService.create(userDetails, request);
        return ResponseEntity.ok("details have been successfully saved");
    }

    /*@GetMapping("/clients/get/{id}")
    public ResponseEntity<JSONObject> getByID(@PathVariable("id") String id) {
        Optional<FullClientProfile> optionalClient = customerService.getClientProfile(id);

        if(!optionalClient.isPresent()) {
            return ResponseUtils.notFound("client was not found");
        }

        return ResponseUtils.custom("client found successfully", true, HttpStatus.OK, optionalClient.get());
    }*/
}
