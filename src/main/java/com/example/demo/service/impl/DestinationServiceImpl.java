package com.example.demo.service.impl;

import com.example.demo.dto.DestinationDTO;
import com.example.demo.entity.Destination;
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
    public DestinationDTO createDestination(DestinationDTO destinationDTO) {
        Destination destination = new Destination();
        BeanUtils.copyProperties(destinationDTO, destination);
        Destination savedDestination = destinationRepository.save(destination);
        return convertToDTO(savedDestination);
    }

    @Override
    public DestinationDTO getDestinationById(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found with id: " + id));
        return convertToDTO(destination);
    }

    @Override
    public List<DestinationDTO> getAllDestinations() {
        return destinationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DestinationDTO updateDestination(Long id, DestinationDTO destinationDTO) {
        Destination existingDestination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found with id: " + id));

        existingDestination.setName(destinationDTO.getName());
        existingDestination.setLocation(destinationDTO.getLocation());

        Destination updatedDestination = destinationRepository.save(existingDestination);
        return convertToDTO(updatedDestination);
    }

    @Override
    public void deleteDestination(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found with id: " + id));
        destinationRepository.delete(destination);
    }

    private DestinationDTO convertToDTO(Destination destination) {
        DestinationDTO destinationDTO = new DestinationDTO();
        BeanUtils.copyProperties(destination, destinationDTO);
        return destinationDTO;
    }

    private Destination convertToEntity(DestinationDTO destinationDTO) {
        Destination destination = new Destination();
        BeanUtils.copyProperties(destinationDTO, destination);
        return destination;
    }
}