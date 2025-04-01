package com.example.demo.service.impl;


import com.example.demo.entity.Employee;
import com.example.demo.entity.enums.EmployeeRole;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAllEmployeesByRole(String role) {
        return employeeRepository.findByRole(EmployeeRole.valueOf(role));
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        // tim employee
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        Employee employeeToUpdate = employeeOptional.orElseThrow(
                () -> new RuntimeException("Employee not found with id: " + employee.getId()));

       updateEmployeeEntity(employeeToUpdate, employee);
        Employee savedEmployee = employeeRepository.save(employeeToUpdate);
        return savedEmployee;
    }


    private void updateEmployeeEntity(Employee employee1, Employee employee2) {
        if(employee2.getFullname()!=null) {
            employee1.setFullname(employee2.getFullname());
        }
        if(employee2.getUsername()!=null) {
            employee1.setUsername(employee2.getUsername());
        }
        if(employee2.getDateofbirth()!=null) {
            employee1.setDateofbirth(employee2.getDateofbirth());
        }
        if(employee2.getGender()!=null) {
            employee1.setGender(employee2.getGender());
        }
        if(employee2.getEmail()!=null) {
            employee1.setEmail(employee2.getEmail());
        }
        if(employee2.getRole()!=null) {
            employee1.setRole(employee2.getRole());
        }
        if(employee2.getPhonenumber()!=null) {
            employee1.setPhonenumber(employee2.getPhonenumber());
        }
        if(employee2.getSalary()!=null) {
            employee1.setSalary(employee2.getSalary());
        }
        if(employee2.getAddress()!=null) {
            employee1.setAddress(employee2.getAddress());
        }
        if(employee2.getDepartment()!=null) {
            employee1.setDepartment(employee2.getDepartment());
        }
        if(employee2.getDepartment()!=null) {
            employee1.setDepartment(employee2.getDepartment());
        }
        if(employee2.getNote()!=null) {
            employee1.setNote(employee2.getNote());
        }
    }
}
