package com.example.demo.service.contractvalidation;

import com.example.demo.entity.Contract;
import com.example.demo.entity.PartnerPayment;

public abstract class ContractCheckHandler {
    protected ContractCheckHandler next;

    public ContractCheckHandler setNext(ContractCheckHandler next) {
        this.next = next;
        return next;
    }

    public void check(Contract contract, PartnerPayment payment) {
        if (next != null) {
            next.check(contract, payment);
        }
    }
}
