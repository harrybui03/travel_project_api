package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

//    @ManyToMany(mappedBy = "destinations", fetch = FetchType.LAZY)
//    private Set<Tour> tours = new HashSet<>();

    public Destination() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<Tour> getTours() {
//        return tours;
//    }
//
//    public void setTours(Set<Tour> tours) {
//        this.tours = tours;
//    }
}
