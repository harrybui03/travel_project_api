package com.example.demo.entity;

import com.example.demo.entity.enums.EmployeeRole;
import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TourGuide extends Member {
    private String position;
    private String department;
    private Double salary;
    private Double bonus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeRole role;

    public TourGuide() {
    }

    public TourGuide(Long i, String johnDoe, String tourGuide) {

    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }




    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
