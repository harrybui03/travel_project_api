package com.example.demo.service.observer;

import com.example.demo.entity.PartnerPayment;
import com.example.demo.entity.enums.PaymentStatus;

public interface PaymentStatusObserver {
    void onStatusChanged(PartnerPayment payment, PaymentStatus oldStatus, PaymentStatus newStatus);
}
