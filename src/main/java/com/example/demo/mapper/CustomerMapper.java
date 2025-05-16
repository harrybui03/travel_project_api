package com.example.demo.mapper;

import com.example.demo.response.Customer;

public class CustomerMapper {

    public static Customer toDTO(com.example.demo.entity.Customer customer) {
        Customer dto = new Customer();
        dto.setId(customer.getId());
        dto.setFullname(customer.getFullname());
        dto.setUsername(customer.getUsername());
        dto.setDateofbirth(customer.getDateofbirth());
        dto.setGender(customer.getGender());
        dto.setAddress(customer.getAddress());
        dto.setEmail(customer.getEmail());
        dto.setPhonenumber(customer.getPhonenumber());
        dto.setNote(customer.getNote());
        dto.setCustomerLevel(customer.getCustomerLevel());
        dto.setLoyaltyPoint(customer.getLoyaltyPoint());
        return dto;
    }

    public static com.example.demo.entity.Customer toEntity(Customer dto) {
        com.example.demo.entity.Customer customer = new com.example.demo.entity.Customer();
        customer.setId(dto.getId());
        customer.setFullname(dto.getFullname());
        customer.setUsername(dto.getUsername());
        customer.setDateofbirth(dto.getDateofbirth());
        customer.setGender(dto.getGender());
        customer.setAddress(dto.getAddress());
        customer.setEmail(dto.getEmail());
        customer.setPhonenumber(dto.getPhonenumber());
        customer.setNote(dto.getNote());
        customer.setCustomerLevel(dto.getCustomerLevel());
        customer.setLoyaltyPoint(dto.getLoyaltyPoint());
        return customer;
    }
}