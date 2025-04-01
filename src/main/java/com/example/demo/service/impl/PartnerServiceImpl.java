package com.example.demo.service.impl;

import com.example.demo.dto.PartnerDTO;
import com.example.demo.entity.Partner;
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
    public List<PartnerDTO> getAllPartners() {
        List<Partner> partners = partnerRepository.findAll();
        // duyet qua cac phan tu cua partners
        // map partner -> partnerDTO
        // return list<PartnerDTO>
        return partners.stream().map(PartnerMapper::mapToPartnerDTO).collect(Collectors.toList());
    }

    @Override
    public PartnerDTO addPartner(PartnerDTO partnerDTO) {
        Partner partner = PartnerMapper.mapToPartnerEntity(partnerDTO);
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
    public PartnerDTO updatePartner(PartnerDTO partnerDTO) {
        // tim partner ton tai trong db qua id
        Optional<Partner> partnerOptional = partnerRepository.findById(partnerDTO.getId());

        // cap nhat thong tin partner ( chi cap nhat phan non-null
        Partner partnerToUpdate = partnerOptional.orElseThrow(
                () -> new RuntimeException("Partner with id " + partnerDTO.getId() + " does not exist"));

        Partner savedPartner = partnerRepository.save(partnerToUpdate);
        return PartnerMapper.mapToPartnerDTO(savedPartner);

    }

    @Override
    public PartnerDTO getPartnerById(Long id) {
        Partner partner = partnerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Partner with id " + id + " does not exist")
        );
        return PartnerMapper.mapToPartnerDTO(partner);
    }

    private void updatePartnerFromDTO(Partner partnerToUpdate, PartnerDTO partnerDTO) {
        if (partnerDTO.getName()!=null) {
            partnerToUpdate.setName(partnerDTO.getName());
        }
        if (partnerDTO.getAddress()!=null) {
            partnerToUpdate.setAddress(partnerDTO.getAddress());
        }
        if (partnerDTO.getEmail()!=null) {
            partnerToUpdate.setEmail(partnerDTO.getEmail());
        }
        if(partnerDTO.getPhoneNumber()!=null) {
            partnerToUpdate.setPhoneNumber(partnerDTO.getPhoneNumber());
        }
    }
}
