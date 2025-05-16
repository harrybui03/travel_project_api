package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Tour;
import com.example.demo.entity.TourSchedule;
import com.example.demo.entity.enums.EmployeeRole;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TourRepository;
import com.example.demo.repository.TourScheduleRepository;
import com.example.demo.service.AssignService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AssignServiceImpl implements AssignService {
    private final TourScheduleRepository tourScheduleRepository;
    private final EmployeeRepository employeeRepository;

    public AssignServiceImpl(TourScheduleRepository tourScheduleRepository, EmployeeRepository employeeRepository) {
        this.tourScheduleRepository = tourScheduleRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<TourSchedule> getListTours() {
        return tourScheduleRepository.findAll();
    }

    @Override
    public List<Employee> getListTourGuides() {
        return employeeRepository.findByRole(EmployeeRole.TOURGUIDE);
    }

    public boolean assignTour(Tour tour) {
        TourSchedule tourSchedule = tour.getTourSchedules().get(0);
        Employee employeeToAssign = tourSchedule.getEmployees().get(0);
        Date tourDepartureDate = tourSchedule.getDepartureDate();
        Date tourReturnDate = tourSchedule.getReturnDate();

        boolean isEmployeeAvailable = true;
        List<Employee> allEmployees = employeeRepository.findAll();
        for (Employee employee : allEmployees) {
            if (employee.getId().equals(employeeToAssign.getId())) {
                List<TourSchedule> assignedTours = tourScheduleRepository.findByEmployees(employee);
                for (TourSchedule assignedTour : assignedTours) {
                    Date assignedDepartureDate = assignedTour.getDepartureDate();
                    Date assignedReturnDate = assignedTour.getReturnDate();
                    if ((tourDepartureDate.before(assignedReturnDate) && tourReturnDate.after(assignedDepartureDate)) ||
                            tourDepartureDate.equals(assignedDepartureDate) ||
                            tourReturnDate.equals(assignedReturnDate)) {
                        isEmployeeAvailable = false;
                        break;
                    }
                }
                break;
            }
        }

        if (!isEmployeeAvailable) {
            System.out.println("Employee with ID: " + employeeToAssign.getId() + " is not available during the tour dates: " + tourDepartureDate + " to " + tourReturnDate);
            return false;
        }

        List<Employee> employees = new ArrayList<>();
        employees.add(employeeToAssign);

        tourSchedule.setEmployees(employees);
        tourScheduleRepository.save(tourSchedule);
        System.out.println("Employee with ID: " + employeeToAssign.getId() + " assigned to Tour Schedule with ID: " + tourSchedule.getId());
        return true;
    }
}
