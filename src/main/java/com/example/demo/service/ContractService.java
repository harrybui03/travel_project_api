package com.example.demo.service;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {


    @Autowired
    private ContractRepository contractRepository;


   public  List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Optional<Contract> getContractById(Long id) {
       return contractRepository.findById(id);
    }

    public List<Contract> getActiveContracts() {
        return contractRepository.findByPaymentStatus("ACTIVE");
    }
}
