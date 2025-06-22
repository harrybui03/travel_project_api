package com.example.demo.controller;


import com.example.demo.entity.Activity;
import com.example.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @GetMapping()
    public ResponseEntity<List<Activity>> getAllActivity() {
        List<Activity> activityDTOS = new ArrayList<>();
        activityDTOS = activityService.getAllActivites();
        return new ResponseEntity<>(activityDTOS, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activityDTO) {
        Activity saved = activityService.addActivity(activityDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable("id") Long id) {
        Activity activityDTO = activityService.getActivityById(id);
        return new ResponseEntity<>(activityDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable("id") Long id) {
        activityService.deleteActivity(id);
        return new ResponseEntity<>("successfully removed activity", HttpStatus.OK);
    }

}
