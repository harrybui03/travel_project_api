package com.example.demo.service;

import com.example.demo.dto.PartnerDTO;
import com.example.demo.entity.Partner;
import jakarta.servlet.http.Part;

import java.util.List;

public interface PartnerService {
    List<PartnerDTO> getAllPartners();
    PartnerDTO addPartner(PartnerDTO partnerDTO);
    void deletePartner(Long id);
    PartnerDTO updatePartner(PartnerDTO partnerDTO);
    PartnerDTO getPartnerById(Long id);
}