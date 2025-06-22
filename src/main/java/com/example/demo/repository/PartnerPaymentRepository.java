package com.example.demo.repository;

import com.example.demo.entity.PartnerPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerPaymentRepository extends JpaRepository<PartnerPayment, Long> {
    List<PartnerPayment> findByContractId(Long contractId);
}
