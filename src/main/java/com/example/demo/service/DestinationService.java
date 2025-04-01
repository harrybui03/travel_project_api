package com.example.demo.service;

import com.example.demo.dto.DestinationDTO;
import java.util.List;

public interface DestinationService {

    DestinationDTO createDestination(DestinationDTO destinationDTO);

    DestinationDTO getDestinationById(Long id);

    List<DestinationDTO> getAllDestinations();

    DestinationDTO updateDestination(Long id, DestinationDTO destinationDTO);

    void deleteDestination(Long id);
}