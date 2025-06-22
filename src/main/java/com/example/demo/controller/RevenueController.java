package com.example.demo.controller;

import com.example.demo.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    /**
     * @param type loại thống kê: monthly, quarterly, yearly
     * @param year năm muốn thống kê
     */
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getRevenue(
            @RequestParam String type,
            @RequestParam int year) {
        List<Map<String, Object>> result = revenueService.getRevenue(type, year);
        return ResponseEntity.ok(result);
    }
}