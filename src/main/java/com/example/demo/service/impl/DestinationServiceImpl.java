package com.example.demo.service.impl;

import com.example.demo.response.Destination;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DestinationRepository;
import com.example.demo.service.DestinationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public Destination createDestination(Destination destinationDTO) {
        com.example.demo.entity.Destination destination = new com.example.demo.entity.Destination();
        BeanUtils.copyProperties(destinationDTO, destination);
        com.example.demo.entity.Destination savedDestination = destinationRepository.save(destination);
        return convertToDTO(savedDestination);
    }

    @Override
    public Destination getDestinationById(Long id) {
        com.example.demo.entity.Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found with id: " + id));
        return convertToDTO(destination);
    }

    @Override
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Destination updateDestination(Long id, Destination destination) {
        com.example.demo.entity.Destination existingDestination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found with id: " + id));

        existingDestination.setName(destination.getName());
        existingDestination.setLocation(destination.getLocation());

        com.example.demo.entity.Destination updatedDestination = destinationRepository.save(existingDestination);
        return convertToDTO(updatedDestination);
    }

    @Override
    public void deleteDestination(Long id) {
        com.example.demo.entity.Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found with id: " + id));
        destinationRepository.delete(destination);
    }

    private Destination convertToDTO(com.example.demo.entity.Destination destination) {
        Destination destinationDTO = new Destination();
        BeanUtils.copyProperties(destination, destinationDTO);
        return destinationDTO;
    }

    private com.example.demo.entity.Destination convertToEntity(Destination destinationDTO) {
        com.example.demo.entity.Destination destination = new com.example.demo.entity.Destination();
        BeanUtils.copyProperties(destinationDTO, destination);
        return destination;
    }
}