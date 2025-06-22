package com.example.demo.service.impl;


import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TourManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourManagementService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private ActivityOnTourRepository activityOnTourRepository;

    @Autowired
    private DestinationOnTourRepository destinationOnTourRepository;

    @Autowired
    private ServiceOnTourRepository serviceOnTourRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private TourServiceRepository serviceRepository;

    @Transactional
    @Override
    public List<Tour> getAllTours() {
        List<Tour> tours = tourRepository.findAll();

        for (Tour tour : tours) {
            // Lấy ActivityOnTour theo tourId
            List<ActivityOnTour> activityLinks = activityOnTourRepository.findByTourId(tour.getId());
            List<Activity> activities = new ArrayList<>();
            for (ActivityOnTour aot : activityLinks) {
                activityRepository.findById(aot.getActivityId()).ifPresent(activities::add);
            }
            tour.setActivities(activities);

            // Lấy DestinationOnTour theo tourId
            List<DestinationOnTour> destinationLinks = destinationOnTourRepository.findByTourId(tour.getId());
            List<Destination> destinations = new ArrayList<>();
            for (DestinationOnTour dot : destinationLinks) {
                destinationRepository.findById(dot.getDestinationId()).ifPresent(destinations::add);
            }
            tour.setDestinations(destinations);

            // Lấy ServiceOnTour theo tourId
            List<ServiceOnTour> serviceLinks = serviceOnTourRepository.findByTourId(tour.getId());
            List<TourService> services = new ArrayList<>();
            for (ServiceOnTour sot : serviceLinks) {
                serviceRepository.findById(sot.getServiceId()).ifPresent(services::add);
            }
            tour.setTourServices(services);
        }
        return tours;
    }

    @Transactional
    @Override
    public Tour addTour(Tour tour) {
        // Lưu tour trước để có id
        Tour savedTour = tourRepository.save(tour);

        Long tourId = savedTour.getId();

        // Lưu ActivityOnTour
        if (tour.getActivities() != null) {
            for (Activity activity : tour.getActivities()) {
                ActivityOnTour aot = new ActivityOnTour();
                aot.setTourId(tourId);
                aot.setActivityId(activity.getId());
                activityOnTourRepository.save(aot);
            }
        }

        // Lưu DestinationOnTour
        if (tour.getDestinations() != null) {
            for (Destination destination : tour.getDestinations()) {
                DestinationOnTour dot = new DestinationOnTour();
                dot.setTourId(tourId);
                dot.setDestinationId(destination.getId());
                destinationOnTourRepository.save(dot);
            }
        }

        // Lưu ServiceOnTour
        if (tour.getTourServices() != null) {
            for (TourService service : tour.getTourServices()) {
                ServiceOnTour sot = new ServiceOnTour();
                sot.setTourId(tourId);
                sot.setServiceId(service.getId());
                serviceOnTourRepository.save(sot);
            }
        }

        // Gán lại list cho tour trả về
        savedTour.setActivities(tour.getActivities());
        savedTour.setDestinations(tour.getDestinations());
        savedTour.setTourServices(tour.getTourServices());

        return savedTour;
    }

    @Transactional
    @Override
    public void deleteTour(Long id) {
        activityOnTourRepository.deleteByTourId(id);
        destinationOnTourRepository.deleteByTourId(id);
        serviceOnTourRepository.deleteByTourId(id);
        tourRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Tour updateTour(Tour tour) {
        Optional<Tour> optionalTour = tourRepository.findById(tour.getId());
        if (optionalTour.isEmpty()) {
            throw new IllegalArgumentException("Tour not found");
        }

        Tour existingTour = optionalTour.get();

        existingTour.setTourName(tour.getTourName());
        existingTour.setTransportation(tour.getTransportation());
        existingTour.setMaxCustomer(tour.getMaxCustomer());
        existingTour.setDescription(tour.getDescription());
        existingTour.setPrice(tour.getPrice());
        existingTour.setTourDuration(tour.getTourDuration());

        tourRepository.save(existingTour);

        Long tourId = existingTour.getId();

        // Xóa các liên kết trung gian cũ
        activityOnTourRepository.deleteByTourId(tourId);
        destinationOnTourRepository.deleteByTourId(tourId);
        serviceOnTourRepository.deleteByTourId(tourId);

        // Thêm lại liên kết mới

        if (tour.getActivities() != null) {
            for (Activity activity : tour.getActivities()) {
                ActivityOnTour aot = new ActivityOnTour();
                aot.setTourId(tourId);
                aot.setActivityId(activity.getId());
                activityOnTourRepository.save(aot);
            }
        }

        if (tour.getDestinations() != null) {
            for (Destination destination : tour.getDestinations()) {
                DestinationOnTour dot = new DestinationOnTour();
                dot.setTourId(tourId);
                dot.setDestinationId(destination.getId());
                destinationOnTourRepository.save(dot);
            }
        }

        if (tour.getTourServices() != null) {
            for (TourService service : tour.getTourServices()) {
                ServiceOnTour sot = new ServiceOnTour();
                sot.setTourId(tourId);
                sot.setServiceId(service.getId());
                serviceOnTourRepository.save(sot);
            }
        }

        existingTour.setActivities(tour.getActivities());
        existingTour.setDestinations(tour.getDestinations());
        existingTour.setTourServices(tour.getTourServices());

        return existingTour;
    }

    @Transactional
    @Override
    public Tour getTourById(Long id) {
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if (optionalTour.isEmpty()) {
            throw new IllegalArgumentException("Tour not found");
        }
        Tour tour = optionalTour.get();

        // Lấy activities
        List<ActivityOnTour> activityLinks = activityOnTourRepository.findByTourId(id);
        List<Activity> activities = new ArrayList<>();
        for (ActivityOnTour aot : activityLinks) {
            activityRepository.findById(aot.getActivityId()).ifPresent(activities::add);
        }
        tour.setActivities(activities);

        // Lấy destinations
        List<DestinationOnTour> destinationLinks = destinationOnTourRepository.findByTourId(id);
        List<Destination> destinations = new ArrayList<>();
        for (DestinationOnTour dot : destinationLinks) {
            destinationRepository.findById(dot.getDestinationId()).ifPresent(destinations::add);
        }
        tour.setDestinations(destinations);

        // Lấy services
        List<ServiceOnTour> serviceLinks = serviceOnTourRepository.findByTourId(id);
        List<TourService> services = new ArrayList<>();
        for (ServiceOnTour sot : serviceLinks) {
            serviceRepository.findById(sot.getServiceId()).ifPresent(services::add);
        }
        tour.setTourServices(services);

        return tour;
    }
}
