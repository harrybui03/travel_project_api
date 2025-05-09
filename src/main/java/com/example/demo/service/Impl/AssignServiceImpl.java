package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.entity.TourSchedule;
import com.example.demo.entity.enums.EmployeeRole;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TourRepository;
import com.example.demo.repository.TourScheduleRepository;
import com.example.demo.service.AssignService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AssignServiceImpl implements AssignService {
    private final TourScheduleRepository tourScheduleRepository;
    private final TourRepository tourRepository;
    private final EmployeeRepository employeeRepository;

    public AssignServiceImpl(TourScheduleRepository tourScheduleRepository, TourRepository tourRepository, EmployeeRepository employeeRepository) {
        this.tourScheduleRepository = tourScheduleRepository;
        this.tourRepository = tourRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<TourSchedule> getListTours() {
        return tourScheduleRepository.findAll();
    }

    @Override
    public List<Employee> getListTourGuides() {
        return employeeRepository.findByRole(EmployeeRole.TOURGUIDE);
    }

    @Transactional
    public boolean assignTour(Long tourScheduleId, Long employeeId) {
        Optional<TourSchedule> tourScheduleOptional = tourScheduleRepository.findById(tourScheduleId);
        if (tourScheduleOptional.isEmpty()) {
            System.out.println("Tour Schedule not found with ID: " + tourScheduleId);
            return false;
        }
        TourSchedule tourSchedule = tourScheduleOptional.get();

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isEmpty()) {
            System.out.println("Employee not found with ID: " + employeeId);
            return false;
        }
        Employee employeeToAssign = employeeOptional.get();

        Date tourDepartureDate = tourSchedule.getDepartureDate();
        Date tourReturnDate = tourSchedule.getReturnDate();

        boolean isEmployeeAvailable = employeeRepository.findAll().stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .flatMap(employee -> tourScheduleRepository.findByEmployee(employee).stream())
                .noneMatch(assignedTour ->
                        (tourDepartureDate.before(assignedTour.getReturnDate()) && tourReturnDate.after(assignedTour.getDepartureDate())) ||
                                tourDepartureDate.equals(assignedTour.getDepartureDate()) ||
                                tourReturnDate.equals(assignedTour.getReturnDate())
                );

        if (!isEmployeeAvailable) {
            System.out.println("Employee with ID: " + employeeId + " is not available during the tour dates: " + tourDepartureDate + " to " + tourReturnDate);
            return false;
        }

        tourSchedule.setEmployee(employeeToAssign);
        tourScheduleRepository.save(tourSchedule);
        System.out.println("Employee with ID: " + employeeId + " assigned to Tour Schedule with ID: " + tourScheduleId);
        return true;
    }
}
