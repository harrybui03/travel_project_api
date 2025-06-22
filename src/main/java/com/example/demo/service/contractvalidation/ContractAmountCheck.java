package com.example.demo.service.contractvalidation;

import com.example.demo.entity.Contract;
import com.example.demo.entity.PartnerPayment;
import com.example.demo.entity.enums.PaymentStatus;
import com.example.demo.repository.PartnerPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;



public class ContractAmountCheck extends ContractCheckHandler {

    private final PartnerPaymentRepository partnerPaymentRepository;

    public ContractAmountCheck(PartnerPaymentRepository partnerPaymentRepository) {
        this.partnerPaymentRepository = partnerPaymentRepository;
    }

    @Override
    public void check(Contract contract, PartnerPayment payment) {
        Long contractId = payment.getContractId();

        // Lấy tất cả các thanh toán liên quan đến hợp đồng
        List<PartnerPayment> payments = partnerPaymentRepository.findByContractId(contractId);

        double totalPaid = payments.stream()
                .filter(p -> p.getStatus() == PaymentStatus.PAID)
                .mapToDouble(PartnerPayment::getPaymentAmount)
                .sum();

        double totalPending = payments.stream()
                .filter(p -> p.getStatus() == PaymentStatus.DRAFT || p.getStatus() == PaymentStatus.APPROVED)
                .mapToDouble(PartnerPayment::getPaymentAmount)
                .sum();

        double newPaymentAmount = payment.getPaymentAmount();
        double remaining = contract.getAmount() - totalPaid - totalPending;

        if (newPaymentAmount > remaining) {
            throw new IllegalStateException("số tiền vượt quá số lượng cần thanh toán");
        }

        super.check(contract, payment);
    }
}