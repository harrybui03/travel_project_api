package com.example.demo.service;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    void deleteEmployeeById(Long id);

    Employee addEmployee(Employee employee);

    // TourGuide
    List<Employee> getAllEmployeesByRole(String role);

    Employee updateEmployee(Employee employee);


}
