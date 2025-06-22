package com.example.demo.service.impl;


import com.example.demo.entity.Partner;
import com.example.demo.repository.PartnerRepository;
import com.example.demo.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    PartnerRepository partnerRepository;


    @Override
    public List<Partner> getAllPartners() {
        List<Partner> partners = partnerRepository.findAll();
        return partners;
    }

    @Override
    public Partner addPartner(Partner partner) {
        return partnerRepository.save(partner);
    }

    @Override
    public void deletePartner(Long id) {
        if(!partnerRepository.existsById(id)) {
            throw new RuntimeException("Partner with id " + id + " does not exist");
        }
        partnerRepository.deleteById(id);
    }

    @Override
    public Partner updatePartner(Partner partner) {
        // tim partner ton tai trong db qua id
        Optional<Partner> partnerOptional = partnerRepository.findById(partner.getId());

        // cap nhat thong tin partner ( chi cap nhat phan non-null
        Partner partnerToUpdate = partnerOptional.orElseThrow(
                () -> new RuntimeException("Partner with id " + partner.getId() + " does not exist"));
        updatePartner(partnerToUpdate, partner);
        return partnerRepository.save(partnerToUpdate);

    }

    @Override
    public Partner getPartnerById(Long id) {
        Partner partner = partnerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Partner with id " + id + " does not exist")
        );
        return partner;
    }

    private void updatePartner(Partner partnerToUpdate, Partner partner) {
        if (partner.getName() != null) {
            partnerToUpdate.setName(partner.getName());
        }
        if (partner.getAddress() != null) {
            partnerToUpdate.setAddress(partner.getAddress());
        }
        if (partner.getEmail() != null) {
            partnerToUpdate.setEmail(partner.getEmail());
        }
        if (partner.getPhoneNumber() != null) {
            partnerToUpdate.setPhoneNumber(partner.getPhoneNumber());
        }
    }
}
