package com.example.demo.service;



import com.example.demo.entity.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> getAllActivites();
    Activity getActivityById(Long id);
    void deleteActivity(Long id);
    Activity addActivity(Activity activityDTO);

}
