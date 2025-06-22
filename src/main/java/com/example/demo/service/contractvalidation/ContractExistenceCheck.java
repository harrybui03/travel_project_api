package com.example.demo.service.contractvalidation;

import com.example.demo.entity.Contract;
import com.example.demo.entity.PartnerPayment;
import com.example.demo.repository.ContractRepository;

public class ContractExistenceCheck extends ContractCheckHandler {
    private final ContractRepository contractRepository;

    public ContractExistenceCheck(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public void check(Contract contract, PartnerPayment payment) {
        if (contract == null || !contractRepository.existsById(payment.getContractId())) {
            throw new IllegalArgumentException("Hợp đồng không tồn tại với ID: " + payment.getContractId());
        }
        super.check(contract, payment);
    }
}
