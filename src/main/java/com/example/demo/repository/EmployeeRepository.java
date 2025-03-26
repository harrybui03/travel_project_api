package com.example.demo.repository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.enums.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByRole(EmployeeRole role);
}
