package com.example.demo.service.impl;

import com.example.demo.entity.Destination;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DestinationRepository;
import com.example.demo.service.DestinationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Destination destination = new Destination();
        BeanUtils.copyProperties(destinationDTO, destination);
        return destinationRepository.save(destination);
    }

    @Override
    public Destination getDestinationById(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found with id: " + id));
        return destination;
    }

    @Override
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination updateDestination(Long id, Destination destinationDTO) {
        Destination existingDestination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found with id: " + id));

        existingDestination.setName(destinationDTO.getName());
        existingDestination.setLocation(destinationDTO.getLocation());

        Destination updatedDestination = destinationRepository.save(existingDestination);
        return updatedDestination;
    }

    @Override
    public void deleteDestination(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found with id: " + id));
        destinationRepository.delete(destination);
    }

    private Destination convertToDTO(Destination destination) {
        Destination destinationDTO = new Destination();
        BeanUtils.copyProperties(destination, destinationDTO);
        return destinationDTO;
    }

    private Destination convertToEntity(Destination destinationDTO) {
        Destination destination = new Destination();
        BeanUtils.copyProperties(destinationDTO, destination);
        return destination;
    }
}