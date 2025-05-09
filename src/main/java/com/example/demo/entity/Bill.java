package com.example.demo.entity;

import com.example.demo.entity.enums.BillType;
import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillType billType;

    private String paymentMethod;
    private Double paymentAmount;
    private Date paymentDate;
    private Double tax;
    private Double Discount;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "tour_booking_id", nullable = true)
    private TourBooking tourBooking;

    public Bill() {
    }

    public Bill(BillType billType, String paymentMethod, Double paymentAmount, Date paymentDate, Customer customer, TourBooking tourBooking) {
        this.billType = billType;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.customer = customer;
        this.tourBooking = tourBooking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TourBooking getTourBooking() {
        return tourBooking;
    }

    public void setTourBooking(TourBooking tourBooking) {
        this.tourBooking = tourBooking;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

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
}
