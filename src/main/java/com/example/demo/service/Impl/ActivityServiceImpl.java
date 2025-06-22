package com.example.demo.service.impl;

import com.example.demo.entity.Activity;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.PartnerRepository;
import com.example.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public List<Activity> getAllActivites() {
        List<Activity> activities = activityRepository.findAll();
        return activities;
    }

    @Override
    public Activity getActivityById(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Partner with id " + id + " does not exist")
        );
        return activity;
    }

    @Override
    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new RuntimeException("Activity with id: " + id+ " does not exist" );
        }
        activityRepository.deleteById(id);
    }

    @Override
    public Activity addActivity(Activity activity) {
        Activity saved = activityRepository.save(activity);
        return saved;
    }
}
