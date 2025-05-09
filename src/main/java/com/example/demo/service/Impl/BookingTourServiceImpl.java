package com.example.demo.service.impl;

import com.example.demo.dto.BillDTO;
import com.example.demo.dto.CreateTourBookingRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.BookingTourService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

@Service
public class BookingTourServiceImpl implements BookingTourService {
    TourRepository tourRepository;
    TourScheduleRepository tourScheduleRepository;
    TourBookingRepository tourBookingRepository;
    BillRepository billRepository;

    CustomerRepository customerRepository;
    TicketRepository ticketRepository;

    public BookingTourServiceImpl(TourRepository tourRepository, TourScheduleRepository tourScheduleRepository, TourBookingRepository tourBookingRepository, BillRepository billRepository, CustomerRepository customerRepository, TicketRepository ticketRepository) {
        this.tourRepository = tourRepository;
        this.tourScheduleRepository = tourScheduleRepository;
        this.tourBookingRepository = tourBookingRepository;
        this.billRepository = billRepository;
        this.customerRepository = customerRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Tour> getListTours(String keyword) {
        return tourRepository.searchTourByTourName(keyword.toLowerCase(Locale.ROOT));
    }

    @Override
    public List<TourSchedule> getListTourSchedule(Long tourId) {
        List<TourSchedule> tourSchedules = tourScheduleRepository.findByTour_Id(tourId).stream().filter(tourSchedule -> {
            return tourSchedule.getEmployee() != null;
        }).toList();
        return tourSchedules;
    }

    @Override

    public Tour getTourById(Long tourId) {
        Optional<Tour> tour = tourRepository.findById(tourId);
        if (tour.isEmpty()) {
            System.out.println("Tour not found with ID: " + tourId);
            return null;
        }

        return tour.get();
    }

    @Override
    public TourBooking createTourBooking(Long tourScheduleId, CreateTourBookingRequest createTourBookingRequest) {
        Optional<TourSchedule> tourSchedule = tourScheduleRepository.findById(tourScheduleId);
        if (tourSchedule.isEmpty()) {
            System.out.println("Tour Schedule not found with ID: " + tourScheduleId);
            return null;
        }

        Customer customer = customerRepository.findById(createTourBookingRequest.getUserId()).get();

        TourBooking booking = tourBookingRepository.save(new TourBooking(createTourBookingRequest.getNumberOfTicket() , tourSchedule.get() ,customer));
        return booking;
    }

    private Long generatePositiveRandomLong() {
        Random random = new Random();
        long randomNumber;
        do {
            randomNumber = random.nextLong();
        } while (randomNumber <= 0);
        return randomNumber;
    }

    @Override
    public Bill createBill(Long tourBookingId, BillDTO billDTO) {
        Optional<TourBooking> tourBooking = tourBookingRepository.findById(tourBookingId);
        if (tourBooking.isEmpty()){
            return null;
        }
        Customer customer = customerRepository.findById(billDTO.getUserId()).get();

        Bill bill = billRepository.save(new Bill(billDTO.getType(), billDTO.getPaymentMethod() , billDTO.getPaymentAmount() ,billDTO.getPaymentDate() , customer , tourBooking.get()));

        int numberOfTickets = tourBooking.get().getNumberOfCustomer();
        List<Ticket> tickets = new java.util.ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            Ticket ticket = new Ticket();
            ticket.setTicketNumber(generatePositiveRandomLong());
            ticket.setBill(bill);
            tickets.add(ticket);
        }
        ticketRepository.saveAll(tickets);

        return bill;
    }
}
