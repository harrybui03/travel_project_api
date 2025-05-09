package com.example.demo.controller;

import com.example.demo.dto.AssignRequest;
import com.example.demo.entity.Employee;
import com.example.demo.entity.TourSchedule;
import com.example.demo.service.AssignService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RestController
@RequestMapping("/api/assign")
public class AssignTourController {

    private final AssignService assignService;

    public AssignTourController(AssignService assignService) {
        this.assignService = assignService;
    }

    @GetMapping("/tours")
    public ResponseEntity<List<TourSchedule>> getListTours() {
        List<TourSchedule> tours = assignService.getListTours();
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/tour-guides")
    public ResponseEntity<List<Employee>> getListTourGuides() {
        List<Employee> tourGuides = assignService.getListTourGuides();
        return new ResponseEntity<>(tourGuides, HttpStatus.OK);
    }

    @PostMapping("/assign-employee")
    public ResponseEntity<String> assignEmployeeToTour(@RequestBody AssignRequest assignRequest) {
        boolean assignmentResult = assignService.assignTour(assignRequest.getTourScheduleId(), assignRequest.getEmployeeId());
        if (assignmentResult) {
            return new ResponseEntity<>("Employee assigned to tour successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to assign employee to tour. Please check availability or IDs.", HttpStatus.BAD_REQUEST);
        }
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173" , "http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}