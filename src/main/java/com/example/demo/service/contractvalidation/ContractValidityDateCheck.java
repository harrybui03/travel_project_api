package com.example.demo.service.contractvalidation;


import com.example.demo.entity.Contract;
import com.example.demo.entity.PartnerPayment;

import java.time.LocalDate;

public class ContractValidityDateCheck extends ContractCheckHandler {

    @Override
    public void check(Contract contract, PartnerPayment payment) {
        LocalDate today = LocalDate.now();

        if (contract.getExpirationDate() != null && contract.getExpirationDate().isBefore(today)) {
            throw new IllegalStateException("Hợp đồng đã hết hạn");
        }
        super.check(contract, payment);
    }
}

