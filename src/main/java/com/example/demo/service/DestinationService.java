package com.example.demo.service;


import com.example.demo.entity.Destination;

import java.util.List;

public interface DestinationService {

    Destination createDestination(Destination destinationDTO);

    Destination getDestinationById(Long id);

    List<Destination> getAllDestinations();

    Destination updateDestination(Long id, Destination destinationDTO);

    void deleteDestination(Long id);
}