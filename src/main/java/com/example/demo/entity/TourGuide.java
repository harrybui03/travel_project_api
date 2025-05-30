package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TourGuide extends Member {
    private String position;
    private String department;
    private Double salary;

    @OneToMany(mappedBy = "tourGuide", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TourGuideSchedule> tourSchedules;

    public TourGuide() {
    }

    public TourGuide(String position, String department, Double salary) {
        this.position = position;
        this.department = department;
        this.salary = salary;
    }

    public List<TourGuideSchedule> getTourSchedules() {
        return tourSchedules;
    }

    public void setTourSchedules(List<TourGuideSchedule> tourSchedules) {
        this.tourSchedules = tourSchedules;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}