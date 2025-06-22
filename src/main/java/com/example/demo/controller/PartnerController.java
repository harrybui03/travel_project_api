package com.example.demo.controller;

import com.example.demo.entity.Partner;
import com.example.demo.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {
    @Autowired
    PartnerService partnerService;

    @GetMapping("")
    public ResponseEntity<List<Partner>> getAllPartners() {
        List<Partner> partnerDTOList = new ArrayList<>();
        partnerDTOList = partnerService.getAllPartners();
        return new ResponseEntity<>(partnerDTOList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Partner> getPartnerById(@PathVariable("id") Long id) {
        Partner partnerDTO = partnerService.getPartnerById(id);
        return new ResponseEntity<>(partnerDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Partner> addPartner(@RequestBody Partner partner) {
        Partner partnerDTOSaved = partnerService.addPartner(partner);
        return new ResponseEntity<>(partnerDTOSaved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePartner(@PathVariable("id") Long id) {
        partnerService.deletePartner(id);
        return new ResponseEntity<>("Partner successfully deleted",HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Partner> updatePartner(@PathVariable("id") Long id, @RequestBody Partner partner) {
        partner.setId(id);
        Partner updatedPartner = partnerService.updatePartner(partner);
        return new ResponseEntity<>(updatedPartner, HttpStatus.OK);
    }
}
