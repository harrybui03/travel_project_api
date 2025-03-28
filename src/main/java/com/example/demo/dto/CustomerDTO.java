package com.example.demo.dto;

import java.util.Date;

public class CustomerDTO {
    private Long id;
    private String fullname;
    private String username;
    private Date dateofbirth;
    private String gender;
    private String address;
    private String email;
    private String phonenumber;
    private String note;
    private Integer customerLevel;
    private Integer loyaltyPoint;
    public CustomerDTO() {
    }

    // Parameterized constructor (optional, for convenience)
    public CustomerDTO(Long id, String fullname, String username, Date dateofbirth, String gender, String address, String email, String phonenumber, String note, Integer customerLevel, Integer loyaltyPoint) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phonenumber = phonenumber;
        this.note = note;
        this.customerLevel = customerLevel;
        this.loyaltyPoint = loyaltyPoint;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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