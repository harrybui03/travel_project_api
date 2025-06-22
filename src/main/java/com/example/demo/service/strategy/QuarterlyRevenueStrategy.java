package com.example.demo.service.strategy;

import com.example.demo.repository.CustomerPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuarterlyRevenueStrategy implements RevenueStrategy {

    @Autowired
    private CustomerPaymentRepository repository;

    @Override
    public List<Map<String, Object>> calculateRevenue(int year) {
        List<Object[]> results = repository.getRevenueByQuarter(year);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("quarter", row[0]);
            map.put("totalRevenue", row[1]);
            list.add(map);
        }
        return list;
    }
}