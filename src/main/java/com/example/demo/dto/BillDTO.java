package com.example.demo.dto;

import com.example.demo.entity.enums.BillType;

import java.util.Date;

public class BillDTO {
    private String paymentMethod;
    private Double paymentAmount;
    private Date paymentDate;
    private BillType type;
    private Long userId;


    public BillDTO(String paymentMethod, Double paymentAmount, Date paymentDate, BillType type , Long userId) {
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.type = type;
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BillType getType() {
        return type;
    }

    public void setType(BillType type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
