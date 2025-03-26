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
    private BillType billType; // CUSTOMER hoặc PARTNER

    private String paymentMethod;
    private Double paymentAmount;
    private Date paymentDate;
    private Double tax;
    private Double Discount;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true) // Nullable vì có thể là Partner bill
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false) // Nhân viên kế toán chịu trách nhiệm hóa đơn
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "tour_booking_id", nullable = true) // Chỉ dùng cho hóa đơn khách hàng
    private TourBooking tourBooking;

    public Bill() {
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

    public Employee getEmployee() {
        return employee;
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

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
