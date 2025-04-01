package com.example.demo.dto;

import java.util.Objects;

public class DestinationDTO {
    private Long id;
    private String name;
    private String location;

    public DestinationDTO() {
    }

    public DestinationDTO(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DestinationDTO that = (DestinationDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location);
    }

    @Override
    public String toString() {
        return "DestinationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}