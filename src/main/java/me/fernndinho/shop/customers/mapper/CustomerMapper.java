package me.fernndinho.shop.customers.mapper;

import me.fernndinho.shop.customers.payload.CustomerCompactResponse;
import me.fernndinho.shop.customers.payload.CustomerDetailsResponse;
import me.fernndinho.shop.customers.models.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerCompactResponse toCompactDto(CustomerEntity entity) {
        CustomerCompactResponse dto = new CustomerCompactResponse();

        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setLastname(entity.getLastname());

        return dto;
    }

    public CustomerDetailsResponse toDetailedDto(CustomerEntity entity) {
        CustomerDetailsResponse dto = new CustomerDetailsResponse();

        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setLastname(entity.getLastname());
        dto.setPhoneNumber(entity.getPhone());
        dto.setAreaCode(entity.getAreaCode());
        dto.setCountry(entity.getCountry());
        dto.setState(entity.getState());
        dto.setCity(entity.getCity());
        dto.setZip(entity.getZip());

        return dto;
    }

    public CustomerEntity toEntity(CustomerCompactResponse dto) {
        CustomerEntity entity = new CustomerEntity();

        entity.setName(dto.getName());
        entity.setLastname(dto.getLastname());
        entity.setEmail(dto.getEmail());

        return entity;
    }

}
