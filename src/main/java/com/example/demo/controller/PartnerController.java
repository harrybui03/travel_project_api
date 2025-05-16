package com.example.demo.controller;

import com.example.demo.response.Partner;
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

    @GetMapping("/listAll")
    public ResponseEntity<List<Partner>> getAllPartners() {
        List<Partner> partnerList = new ArrayList<>();
        partnerList = partnerService.getAllPartners();
        return new ResponseEntity<>(partnerList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Partner> getPartnerById(@PathVariable("id") Long id) {
        Partner partner = partnerService.getPartnerById(id);
        return new ResponseEntity<>(partner, HttpStatus.OK);
    }

    @PostMapping("/addPartner")
    public ResponseEntity<Partner> addPartner(@RequestBody Partner partner) {
        Partner partnerSaved = partnerService.addPartner(partner);
        return new ResponseEntity<>(partnerSaved, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletePartner/{id}")
    public ResponseEntity<String> deletePartner(@PathVariable("id") Long id) {
        partnerService.deletePartner(id);
        return new ResponseEntity<>("Partner successfully deleted",HttpStatus.OK);
    }

    @PatchMapping("updateBook/{id}")
    public ResponseEntity<Partner> updatePartner(@PathVariable("id") Long id, @RequestBody Partner partner) {
        partner.setId(id);
        Partner partnerSaved = partnerService.updatePartner(partner);
        return new ResponseEntity<>(partnerSaved, HttpStatus.OK);
    }
}
