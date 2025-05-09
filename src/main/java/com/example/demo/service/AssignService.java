package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.TourSchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignService {
    List<TourSchedule> getListTours();
    List<Employee> getListTourGuides();
    boolean assignTour(Long tourScheduleId, Long employeeId);
}
