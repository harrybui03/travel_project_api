package com.example.demo.entity;

import com.example.demo.entity.enums.PaymentStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PartnerPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long contractId;
    private String paymentMethod;
    private Double paymentAmount;
//    private Double tax;
//    private Double discount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;


    private Long createdByEmployeeId;
    private Long approvedByEmployeeId;
    private Long paidByEmployeeId;

    private Date createdAt;
    private Date approvedAt;
    private Date paidAt;

    public PartnerPayment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Double getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(Double discount) {
//        this.discount = discount;
//    }
//
//    public Double getTax() {
//        return tax;
//    }
//
//    public void setTax(Double tax) {
//        this.tax = tax;
//    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Date getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Date paidAt) {
        this.paidAt = paidAt;
    }

    public Date getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Date approvedAt) {
        this.approvedAt = approvedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getPaidByEmployeeId() {
        return paidByEmployeeId;
    }

    public void setPaidByEmployeeId(Long paidByEmployeeId) {
        this.paidByEmployeeId = paidByEmployeeId;
    }

    public Long getApprovedByEmployeeId() {
        return approvedByEmployeeId;
    }

    public void setApprovedByEmployeeId(Long approvedByEmployeeId) {
        this.approvedByEmployeeId = approvedByEmployeeId;
    }

    public Long getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(Long createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
    }
}
