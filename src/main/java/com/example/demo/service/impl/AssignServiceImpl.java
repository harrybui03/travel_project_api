package com.example.demo.service.impl;

import com.example.demo.entity.Tour;
import com.example.demo.entity.TourGuide;
import com.example.demo.entity.TourGuideSchedule;
import com.example.demo.entity.TourSchedule;
import com.example.demo.repository.TourGuideRepository;
import com.example.demo.repository.TourGuideScheduleRepository;
import com.example.demo.repository.TourScheduleRepository;
import com.example.demo.service.AssignService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AssignServiceImpl implements AssignService {
    private final TourScheduleRepository tourScheduleRepository;
    private final TourGuideRepository tourGuideRepository;

    private final TourGuideScheduleRepository tourGuideScheduleRepository;

    public AssignServiceImpl(TourScheduleRepository tourScheduleRepository, TourGuideRepository tourGuideRepository, TourGuideScheduleRepository tourGuideScheduleRepository) {
        this.tourScheduleRepository = tourScheduleRepository;
        this.tourGuideRepository = tourGuideRepository;
        this.tourGuideScheduleRepository = tourGuideScheduleRepository;
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

        for (TourGuideSchedule tourGuideToAssign : tourSchedule.getTourGuides()) {
            if (!isTourGuideAvailable(tourGuideToAssign.getTourGuide(), tourDepartureDate, tourReturnDate , tourSchedule)) {
                return false;
            }
        }

        tourGuideScheduleRepository.saveAll(tourSchedule.getTourGuides());
        return true;
    }

    private boolean isTourGuideAvailable(TourGuide tourGuide, Date departureDate, Date returnDate , TourSchedule tourSchedule) {
        List<TourSchedule> assignedTours = tourGuideScheduleRepository.findSchedulesByTourGuideJPQL(tourGuide);
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