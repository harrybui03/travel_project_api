package com.example.demo.service.impl;

import com.example.demo.response.Customer;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customerDTO) {
        com.example.demo.entity.Customer customer = CustomerMapper.toEntity(customerDTO);

        com.example.demo.entity.Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toDTO(savedCustomer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        com.example.demo.entity.Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        return CustomerMapper.toDTO(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<com.example.demo.entity.Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        com.example.demo.entity.Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setFullname(customer.getFullname());
        existingCustomer.setUsername(customer.getUsername());
        existingCustomer.setDateofbirth(customer.getDateofbirth());
        existingCustomer.setGender(customer.getGender());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhonenumber(customer.getPhonenumber());
        existingCustomer.setNote(customer.getNote());
        existingCustomer.setCustomerLevel(customer.getCustomerLevel());
        existingCustomer.setLoyaltyPoint(customer.getLoyaltyPoint());

        com.example.demo.entity.Customer updatedCustomer = customerRepository.save(existingCustomer);

        return CustomerMapper.toDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }

        customerRepository.deleteById(id);
    }
}