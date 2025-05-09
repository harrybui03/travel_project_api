package com.example.demo.dto;

public class AssignRequest {
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