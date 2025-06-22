package com.example.demo.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendPaymentCreatedNotification(Long paymentId, String contractId) {
        // gửi đến topic "/topic/manager-notifications"
        NotificationMessage message = new NotificationMessage("Payment mới được tạo: " + paymentId + " hợp đồng: " + contractId);
        messagingTemplate.convertAndSend("/topic/manager-notifications", message);
    }

    // class thông báo đơn giản
    public static class NotificationMessage {
        private String content;

        public NotificationMessage(String content) {
            this.content = content;
        }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }
}
