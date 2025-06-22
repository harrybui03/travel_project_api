package com.example.demo.service;


import com.example.demo.entity.DestinationOnTour;

import java.util.List;

public interface DestinationOnTourService {
    List<DestinationOnTour> getAll();

    DestinationOnTour add(DestinationOnTour dto);
}
