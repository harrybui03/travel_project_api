package com.example.demo.service;


import com.example.demo.entity.ServiceOnTour;

import java.util.List;

public interface ServiceOnTourService {
    ServiceOnTour add(ServiceOnTour dto);

    List<ServiceOnTour> getAll();
}
