package com.example.demo.service;

import com.example.demo.response.Destination;
import java.util.List;

public interface DestinationService {

    Destination createDestination(Destination destination);

    Destination getDestinationById(Long id);

    List<Destination> getAllDestinations();

    Destination updateDestination(Long id, Destination destination);

    void deleteDestination(Long id);
}