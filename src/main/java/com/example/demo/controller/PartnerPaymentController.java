package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.entity.PartnerPayment;
import com.example.demo.entity.enums.EmployeeRole;
import com.example.demo.entity.enums.PaymentAction;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.PartnerPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partner-payments")
public class PartnerPaymentController {

    @Autowired
    private PartnerPaymentService partnerPaymentService;

    @Autowired
    private EmployeeService employeeService;

    // POST /api/partner-payments/create?employeeId=100
    @PostMapping("/create")
    public ResponseEntity<PartnerPayment> createPayment(
            @RequestBody PartnerPayment payment,
            @RequestParam Long employeeId) {
        Employee e = employeeService.getEmployeeById(employeeId);
        if (e.getRole() != EmployeeRole.ACCOUNTANT) {
            throw new SecurityException("accountant only");
        }
        PartnerPayment created = partnerPaymentService.create(payment, employeeId);
        return ResponseEntity.ok(created);
    }


    // POST /api/partner-payments/{id}/approve?employeeId=200
    @PostMapping("/{id}/approve")
    public ResponseEntity<PartnerPayment> approvePayment(
            @PathVariable Long id,
            @RequestParam Long employeeId) {
        Employee e = employeeService.getEmployeeById(employeeId);
        PartnerPayment updated = partnerPaymentService.processNextStep(id, employeeId, e.getRole(), PaymentAction.APPROVE);
        return ResponseEntity.ok(updated);
    }


    // POST /api/partner-payments/{id}/pay?employeeId=100
    @PostMapping("/{id}/pay")
    public ResponseEntity<PartnerPayment> payToPartner(
            @PathVariable Long id,
            @RequestParam Long employeeId) {
        Employee e = employeeService.getEmployeeById(employeeId);
        PartnerPayment updated = partnerPaymentService.processNextStep(id, employeeId, e.getRole(), PaymentAction.PAY);
        return ResponseEntity.ok(updated);
    }


    // GET /api/partner-payments
    @GetMapping
    public ResponseEntity<List<PartnerPayment>> getAllPayments() {
        return ResponseEntity.ok(partnerPaymentService.getAll());
    }


    // GET /api/partner-payments/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PartnerPayment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(partnerPaymentService.getById(id));
    }
}
