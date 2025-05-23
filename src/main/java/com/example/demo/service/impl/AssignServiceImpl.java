package com.example.demo.service.impl;

import com.example.demo.entity.Tour;
import com.example.demo.entity.TourGuide;
import com.example.demo.entity.TourSchedule;
import com.example.demo.repository.TourGuideRepository;
import com.example.demo.repository.TourScheduleRepository;
import com.example.demo.service.AssignService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AssignServiceImpl implements AssignService {
    private final TourScheduleRepository tourScheduleRepository;
    private final TourGuideRepository tourGuideRepository;

    public AssignServiceImpl(TourScheduleRepository tourScheduleRepository, TourGuideRepository tourGuideRepository) {
        this.tourScheduleRepository = tourScheduleRepository;
        this.tourGuideRepository = tourGuideRepository;
    }

    @Override
    public List<TourSchedule> getListTours() {
        return tourScheduleRepository.findAll();
    }

    @Override
    public List<TourGuide> getListTourGuides() {
        return tourGuideRepository.findAll();
    }

    @Transactional
    public boolean assignTour(Tour tour) {
        if (tour == null || tour.getTourSchedule() == null || tour.getTourSchedule().isEmpty()) {
            return false;
        }

        TourSchedule tourSchedule = tour.getTourSchedule().get(0);
        if (tourSchedule == null) {
            return false;
        }

        Date tourDepartureDate = tourSchedule.getDepartureDate();
        Date tourReturnDate = tourSchedule.getReturnDate();

        List<TourGuide> tourGuidesFromSchedule = tourSchedule.getTourGuides();


        if (tourGuidesFromSchedule == null || tourGuidesFromSchedule.isEmpty()) {
            return false;
        }

        for (TourGuide tourGuideToAssign : tourGuidesFromSchedule) {
            if (tourGuideToAssign.getId() == null) {
                return false;
            }

            Optional<TourGuide> tourGuideOptional = tourGuideRepository.findById(tourGuideToAssign.getId());
            if (tourGuideOptional.isEmpty()) {
                return false;
            }
            TourGuide managedTourGuide = tourGuideOptional.get();

            if (!isTourGuideAvailable(managedTourGuide, tourDepartureDate, tourReturnDate , tourSchedule)) {
                return false;
            }
        }

        tourSchedule.setTour(tour);

        tourScheduleRepository.save(tourSchedule);
        return true;
    }

    private boolean isTourGuideAvailable(TourGuide tourGuide, Date departureDate, Date returnDate , TourSchedule tourSchedule) {
        List<TourSchedule> assignedTours = tourScheduleRepository.findByTourGuides(tourGuide);
        for (TourSchedule assignedTour : assignedTours) {
            if(tourSchedule.getId() == assignedTour.getId()){
                return true;
            }
            Date assignedDepartureDate = assignedTour.getDepartureDate();
            Date assignedReturnDate = assignedTour.getReturnDate();

            if ((departureDate.before(assignedReturnDate) && returnDate.after(assignedDepartureDate)) ||
                    departureDate.equals(assignedDepartureDate) ||
                    returnDate.equals(assignedReturnDate)) {
                return false;
            }
        }
        return true;
    }
}