package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends Member {
    private Integer customerLevel;
    private Integer loyaltyPoint;

    public Customer() {
    }

    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    public Integer getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(Integer loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }
}
