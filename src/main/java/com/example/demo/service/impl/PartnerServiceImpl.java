package com.example.demo.service.impl;

import com.example.demo.response.Partner;
import com.example.demo.mapper.PartnerMapper;
import com.example.demo.repository.PartnerRepository;
import com.example.demo.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    PartnerRepository partnerRepository;


    @Override
    public List<Partner> getAllPartners() {
        List<com.example.demo.entity.Partner> partners = partnerRepository.findAll();
        // duyet qua cac phan tu cua partners
        // map partner -> partnerDTO
        // return list<PartnerDTO>
        return partners.stream().map(PartnerMapper::mapToPartnerDTO).collect(Collectors.toList());
    }

    @Override
    public Partner addPartner(Partner partnerDTO) {
        com.example.demo.entity.Partner partner = PartnerMapper.mapToPartnerEntity(partnerDTO);
        partner = partnerRepository.save(partner);
        return PartnerMapper.mapToPartnerDTO(partner);
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
        Optional<com.example.demo.entity.Partner> partnerOptional = partnerRepository.findById(partner.getId());

        // cap nhat thong tin partner ( chi cap nhat phan non-null
        com.example.demo.entity.Partner partnerToUpdate = partnerOptional.orElseThrow(
                () -> new RuntimeException("Partner with id " + partner.getId() + " does not exist"));

        com.example.demo.entity.Partner savedPartner = partnerRepository.save(partnerToUpdate);
        return PartnerMapper.mapToPartnerDTO(savedPartner);

    }

    @Override
    public Partner getPartnerById(Long id) {
        com.example.demo.entity.Partner partner = partnerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Partner with id " + id + " does not exist")
        );
        return PartnerMapper.mapToPartnerDTO(partner);
    }

    private void updatePartnerFromDTO(com.example.demo.entity.Partner partnerToUpdate, Partner partner) {
        if (partner.getName()!=null) {
            partnerToUpdate.setName(partner.getName());
        }
        if (partner.getAddress()!=null) {
            partnerToUpdate.setAddress(partner.getAddress());
        }
        if (partner.getEmail()!=null) {
            partnerToUpdate.setEmail(partner.getEmail());
        }
        if(partner.getPhoneNumber()!=null) {
            partnerToUpdate.setPhoneNumber(partner.getPhoneNumber());
        }
    }
}
