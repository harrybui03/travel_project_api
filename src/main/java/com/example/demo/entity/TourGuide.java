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

    @ManyToMany(mappedBy = "tourGuides")
    @JsonIgnore
    private List<TourSchedule> tourSchedules = new ArrayList<>();

    public TourGuide() {
    }

    public TourGuide(String position, String department, Double salary) {
        this.position = position;
        this.department = department;
        this.salary = salary;
    }

    public List<TourSchedule> getTourSchedules() {
        return tourSchedules;
    }

    public void setTourSchedules(List<TourSchedule> tourSchedules) {
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