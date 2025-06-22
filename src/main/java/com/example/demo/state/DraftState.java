package com.example.demo.state;

import com.example.demo.entity.PartnerPayment;
import com.example.demo.entity.enums.EmployeeRole;
import com.example.demo.entity.enums.PaymentAction;
import com.example.demo.entity.enums.PaymentStatus;
import com.example.demo.repository.PartnerPaymentRepository;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Date;

public class DraftState implements PaymentState {

    @Override
    public void handle(PartnerPayment payment, PaymentAction action, Long employeeId, EmployeeRole role, PartnerPaymentRepository repo) {
        switch (action) {
            case APPROVE -> {
                if (role != EmployeeRole.MANAGER) {
                    throw new IllegalStateException("Chỉ quản lý mới có quyền duyệt thanh toán.");
                }
                payment.setStatus(PaymentStatus.APPROVED);
                payment.setApprovedAt(new Date());
                payment.setApprovedByEmployeeId(employeeId);
                repo.save(payment);
            }

            case PAY -> {
                throw new IllegalStateException("Không thể thanh toán khi phiếu chưa được duyệt (đang ở trạng thái DRAFT).");
            }

            default -> throw new IllegalStateException("Hành động không hợp lệ ở trạng thái DRAFT.");
        }
    }
}

