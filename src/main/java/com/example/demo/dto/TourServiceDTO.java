package com.example.demo.dto;

import com.example.demo.entity.Partner;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class TourServiceDTO {
    private Long id;
    private String name;
    private String type;
    private Double price;
    private PartnerDTO partner;

    public TourServiceDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PartnerDTO getPartner() {
        return partner;
    }

    public void setPartner(PartnerDTO partner) {
        this.partner = partner;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
