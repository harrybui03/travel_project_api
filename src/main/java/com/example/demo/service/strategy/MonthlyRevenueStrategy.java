package com.example.demo.service.strategy;

import com.example.demo.repository.CustomerPaymentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonthlyRevenueStrategy implements RevenueStrategy {

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private CustomerPaymentRepository repository;

    @Override
    public List<Map<String, Object>> calculateRevenue(int year) {
        List<Object[]> result = repository.getDetailedRevenueByMonth(year);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : result) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("month", ((Number) row[0]).intValue());
            map.put("transactionCount", ((Number) row[1]).longValue());
            map.put("customerCount", ((Number) row[2]).longValue());

            try {
                String jsonTransactions = row[3].toString();

                // Parse JSON string thành List<Map<String, Object>>
                List<Map<String, Object>> transactionsList = mapper.readValue(
                        jsonTransactions,
                        new TypeReference<List<Map<String, Object>>>() {
                        }
                );

                map.put("transactions", transactionsList);
            } catch (Exception e) {
                e.printStackTrace();
                map.put("transactions", Collections.emptyList());
            }

            // Lấy totalRevenue (trường thứ 5)
            if (row.length > 4 && row[4] != null) {
                map.put("totalRevenue", ((Number) row[4]).doubleValue());
            } else {
                map.put("totalRevenue", 0);
            }

            response.add(map);
        }

        return response;
    }
}