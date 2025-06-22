package com.example.demo.service;

import com.example.demo.entity.Partner;

import java.util.List;

public interface PartnerService {
    List<Partner> getAllPartners();

    Partner addPartner(Partner Partner);
    void deletePartner(Long id);

    Partner updatePartner(Partner Partner);

    Partner getPartnerById(Long id);
}