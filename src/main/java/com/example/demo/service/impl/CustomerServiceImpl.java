package com.example.demo.service.impl;


import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {

        Customer savedCustomer = customerRepository.save(customer);

        return savedCustomer;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setFullname(customerDTO.getFullname());
        existingCustomer.setUsername(customerDTO.getUsername());
        existingCustomer.setDateofbirth(customerDTO.getDateofbirth());
        existingCustomer.setGender(customerDTO.getGender());
        existingCustomer.setAddress(customerDTO.getAddress());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setPhonenumber(customerDTO.getPhonenumber());
        existingCustomer.setNote(customerDTO.getNote());
        existingCustomer.setCustomerLevel(customerDTO.getCustomerLevel());
        existingCustomer.setLoyaltyPoint(customerDTO.getLoyaltyPoint());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return updatedCustomer;
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }

        customerRepository.deleteById(id);
    }
}