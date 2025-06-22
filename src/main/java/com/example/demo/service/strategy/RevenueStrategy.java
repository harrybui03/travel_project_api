package com.example.demo.service.strategy;

import java.util.List;
import java.util.Map;

public interface RevenueStrategy {
    List<Map<String, Object>> calculateRevenue(int year);
}