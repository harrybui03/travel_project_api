package com.example.demo.service;

import com.example.demo.entity.Contract;
import com.example.demo.entity.PartnerPayment;
import com.example.demo.entity.enums.EmployeeRole;
import com.example.demo.entity.enums.PaymentAction;
import com.example.demo.entity.enums.PaymentStatus;
import com.example.demo.event.PaymentEvent;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PartnerPaymentRepository;
import com.example.demo.service.contractvalidation.*;
import com.example.demo.state.PaymentStateContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PartnerPaymentService {
    @Autowired
    private PartnerPaymentRepository partnerPaymentRepository;
    @Autowired
    private ContractRepository contractRepository;
//    @Autowired
//    private ApplicationEventPublisher eventPublisher;





    public void validateContractBeforePayment(Contract contract, PartnerPayment payment) {
        ContractCheckHandler chain = new ContractExistenceCheck(contractRepository);
        chain.setNext(new ContractStatusCheck())
                .setNext(new ContractAmountCheck(partnerPaymentRepository))
                .setNext(new ContractValidityDateCheck());

        chain.check(contract, payment);
    }

    public PartnerPayment processNextStep(Long paymentId, Long employeeId, EmployeeRole role, PaymentAction action) {
        PartnerPayment payment = partnerPaymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thanh toán với ID: " + paymentId));
        PaymentStateContext context = new PaymentStateContext(payment.getStatus());
        context.process(payment, action, employeeId, role, partnerPaymentRepository);
//        eventPublisher.publishEvent(new PaymentEvent(payment, payment.getStatus()));

        return payment;  // <-- trả về payment sau khi process xong
    }


    public PartnerPayment create(PartnerPayment payment, Long createdByEmployeeId) {
        // Lấy hợp đồng liên quan
        Contract contract = contractRepository.findById(payment.getContractId())
                .orElse(null);

        // chuỗi kiểm tra hợp đồng
        validateContractBeforePayment(contract, payment);


        payment.setStatus(PaymentStatus.DRAFT);
        payment.setCreatedByEmployeeId(createdByEmployeeId);
        payment.setCreatedAt(new Date());// set ngay tạo
//
//        eventPublisher.publishEvent(new PaymentEvent(payment, payment.getStatus()));
        return partnerPaymentRepository.save(payment);

    }

    public List<PartnerPayment> getAll() {
        return partnerPaymentRepository.findAll();
    }

    public PartnerPayment getById(Long id) {
        return partnerPaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy PartnerPayment với id = " + id));
    }
}
