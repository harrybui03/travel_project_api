package com.example.demo.state;

import com.example.demo.entity.PartnerPayment;
import com.example.demo.entity.enums.EmployeeRole;
import com.example.demo.entity.enums.PaymentAction;
import com.example.demo.repository.PartnerPaymentRepository;

public class PaidState implements PaymentState {

    @Override
    public void handle(PartnerPayment payment, PaymentAction action, Long employeeId, EmployeeRole role, PartnerPaymentRepository repo) {
        throw new IllegalStateException("Thanh toán đã hoàn tất. Không thể thực hiện hành động nào.");
    }
}