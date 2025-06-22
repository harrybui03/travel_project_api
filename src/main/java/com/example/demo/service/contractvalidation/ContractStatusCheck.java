package com.example.demo.service.contractvalidation;

import com.example.demo.entity.Contract;
import com.example.demo.entity.PartnerPayment;

public class ContractStatusCheck extends ContractCheckHandler {

    @Override
    public void check(Contract contract, PartnerPayment payment) {
        System.out.println(contract.getPaymentStatus());
        if (!"ACTIVE".equalsIgnoreCase(contract.getPaymentStatus())) {
            throw new IllegalStateException("Hợp đồng không còn hiệu lực hoặc chưa được kích hoạt");
        }
        super.check(contract, payment);
    }
}