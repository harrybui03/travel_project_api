package com.example.demo.service;

import com.example.demo.response.Partner;

import java.util.List;

public interface PartnerService {
    List<Partner> getAllPartners();
    Partner addPartner(Partner partner);
    void deletePartner(Long id);
    Partner updatePartner(Partner partner);
    Partner getPartnerById(Long id);
}