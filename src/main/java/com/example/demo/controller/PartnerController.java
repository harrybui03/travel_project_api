package com.example.demo.controller;

import com.example.demo.dto.PartnerDTO;
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
    public ResponseEntity<List<PartnerDTO>> getAllPartners() {
        List<PartnerDTO> partnerDTOList = new ArrayList<>();
        partnerDTOList = partnerService.getAllPartners();
        return new ResponseEntity<>(partnerDTOList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PartnerDTO> getPartnerById(@PathVariable("id") Long id) {
        PartnerDTO partnerDTO = partnerService.getPartnerById(id);
        return new ResponseEntity<>(partnerDTO, HttpStatus.OK);
    }

    @PostMapping("/addPartner")
    public ResponseEntity<PartnerDTO> addPartner(@RequestBody PartnerDTO partnerDTO) {
        PartnerDTO partnerDTOSaved = partnerService.addPartner(partnerDTO);
        return new ResponseEntity<>(partnerDTOSaved, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletePartner/{id}")
    public ResponseEntity<String> deletePartner(@PathVariable("id") Long id) {
        partnerService.deletePartner(id);
        return new ResponseEntity<>("Partner successfully deleted",HttpStatus.OK);
    }

    @PatchMapping("updateBook/{id}")
    public ResponseEntity<PartnerDTO> updatePartner(@PathVariable("id") Long id, @RequestBody PartnerDTO partnerDTO) {
        partnerDTO.setId(id);
        PartnerDTO partnerDTOSaved = partnerService.updatePartner(partnerDTO);
        return new ResponseEntity<>(partnerDTOSaved, HttpStatus.OK);
    }
}
