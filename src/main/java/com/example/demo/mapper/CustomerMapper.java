package com.example.demo.mapper;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
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

    public static Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
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