package com.example.demo.state;

import com.example.demo.entity.PartnerPayment;
import com.example.demo.entity.enums.EmployeeRole;
import com.example.demo.entity.enums.PaymentAction;
import com.example.demo.entity.enums.PaymentStatus;
import com.example.demo.repository.PartnerPaymentRepository;

public class PaymentStateContext {
    private final PaymentState state;

    public PaymentStateContext(PaymentStatus status) {
        switch (status) {
            case DRAFT -> this.state = new DraftState();
            case APPROVED -> this.state = new ApprovedState();
            case PAID -> this.state = new PaidState();
            default -> throw new IllegalArgumentException("Trạng thái không hợp lệ: " + status);
        }
    }

    public void process(PartnerPayment payment, PaymentAction action, Long employeeId, EmployeeRole role, PartnerPaymentRepository repo) {
        state.handle(payment, action,  employeeId, role, repo);
    }
}
