package com.example.demo.state;

import com.example.demo.entity.PartnerPayment;
import com.example.demo.entity.enums.EmployeeRole;
import com.example.demo.entity.enums.PaymentAction;
import com.example.demo.entity.enums.PaymentStatus;
import com.example.demo.exception.InvalidRoleException;
import com.example.demo.repository.PartnerPaymentRepository;

import java.util.Date;

public class ApprovedState implements PaymentState {

    @Override
    public void handle(PartnerPayment payment, PaymentAction action, Long employeeId, EmployeeRole role, PartnerPaymentRepository repo) {
        switch (action) {
            case PAY -> {
                if (role != EmployeeRole.ACCOUNTANT) {
                    throw new IllegalStateException("Chỉ kế toán mới có quyền thanh toán.");
                }
                payment.setStatus(PaymentStatus.PAID);
                payment.setPaidAt(new Date());
                payment.setPaidByEmployeeId(employeeId);
                repo.save(payment);
            }

            case APPROVE -> {
                throw new IllegalStateException("Phiếu đã được duyệt, không thể duyệt lại.");
            }

            default -> throw new IllegalStateException("Hành động không hợp lệ ở trạng thái APPROVED.");
        }
    }
}
