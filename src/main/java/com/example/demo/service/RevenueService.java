package com.example.demo.service;

import com.example.demo.service.strategy.MonthlyRevenueStrategy;
import com.example.demo.service.strategy.QuarterlyRevenueStrategy;
import com.example.demo.service.strategy.RevenueStrategy;
import com.example.demo.service.strategy.YearlyRevenueStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RevenueService {

    @Autowired
    private MonthlyRevenueStrategy monthlyRevenueStrategy;

    @Autowired
    private QuarterlyRevenueStrategy quarterlyRevenueStrategy;

    @Autowired
    private YearlyRevenueStrategy yearlyRevenueStrategy;


    public List<Map<String, Object>> getRevenue(String type, int year) {
        RevenueStrategy strategy;

        switch (type.toLowerCase()) {
            case "monthly":
                strategy = monthlyRevenueStrategy;
                break;
            case "quarterly":
                strategy = quarterlyRevenueStrategy;
                break;
            case "yearly":
                strategy = yearlyRevenueStrategy;
                break;
            default:
                throw new IllegalArgumentException("Invalid revenue type: " + type);
        }

        return strategy.calculateRevenue(year);
    }
}