package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/contracts")
public class ContractController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private ContractRepository contractRepository;


    @GetMapping("")
    public ResponseEntity<List<Contract>> getContractList() {
        List<Contract> contracts = new ArrayList<>();
        contracts = contractService.getAllContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Long id) {
        Contract contract = contractService.getContractById(id).get();
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Contract>> getActiveContracts() {
        List<Contract> activeContracts = contractService.getActiveContracts();
        return ResponseEntity.ok(activeContracts);
    }
}
