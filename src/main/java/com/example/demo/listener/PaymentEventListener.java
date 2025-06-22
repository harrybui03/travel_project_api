package com.example.demo.listener;

import com.example.demo.entity.PartnerPayment;
import com.example.demo.entity.enums.PaymentStatus;
import com.example.demo.event.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentEventListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void onPaymentEvent(PaymentEvent event) {
        PartnerPayment payment = event.getPayment();
        PaymentStatus eventType = event.getEventType();

        Long receiverId = null;
        switch (eventType) {
            case DRAFT -> receiverId = 9L;       // Gửi cho quản lý khi kế toán tạo
            case APPROVED -> receiverId = 8L;    // Gửi cho kế toán khi quản lý duyệt
            case PAID -> receiverId = 9L;        // Gửi cho quản lý khi kế toán thanh toán
        }

        if (receiverId != null) {
            String destination = "/topic/notifications/" + receiverId;

            // Chỉ gửi nội dung cần thiết
            Map<String, Object> payload = new HashMap<>();
            payload.put("id", payment.getId());
            payload.put("status", eventType.name());
            payload.put("message", switch (eventType) {
                case DRAFT -> "Kế toán đã tạo yêu cầu thanh toán";
                case APPROVED -> "Quản lý đã duyệt thanh toán";
                case PAID -> "Kế toán đã thanh toán";
            });
            payload.put("timestamp", System.currentTimeMillis());

            messagingTemplate.convertAndSend(destination, payload);
        }
    }
}
