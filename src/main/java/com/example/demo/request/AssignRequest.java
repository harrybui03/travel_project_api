package com.example.demo.request;

import com.example.demo.entity.Employee;
import com.example.demo.entity.TourSchedule;

public class AssignRequest {
    private TourSchedule tourSchedule;
    private Employee employee;

    public TourSchedule getTourSchedule() {
        return tourSchedule;
    }

    public void setTourSchedule(TourSchedule tourSchedule) {
        this.tourSchedule = tourSchedule;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private Long tourScheduleId;
    private Long employeeId;

    public AssignRequest(Long tourScheduleId, Long employeeId) {
        this.tourScheduleId = tourScheduleId;
        this.employeeId = employeeId;

    }

    public Long getTourScheduleId() {
        return tourScheduleId;
    }

    public void setTourScheduleId(Long tourScheduleId) {
        this.tourScheduleId = tourScheduleId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}