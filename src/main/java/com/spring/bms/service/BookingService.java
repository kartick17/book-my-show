package com.spring.bms.service;

import com.spring.bms.dto.BookingRequest;
import com.spring.bms.entity.*;
import com.spring.bms.enums.BookingStatus;
import com.spring.bms.repository.BookingRepository;
import com.spring.bms.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final UserService userService;
    private final ShowService showService;

    @Transactional
    public Booking createBooking(BookingRequest req)
    {
        User user = userService.getUserById(req.getUserId());
        Show show = showService.getShowById(req.getShowId());

        // Check if any of the requested seat are already booked
        List<Long> alreadyBookedSeats = bookingRepository.findBookedSeatIdsByShowId(show.getId());
        for (Long seadId : req.getSeatIds())
        {
            if(alreadyBookedSeats.contains(seadId))
            {
                throw new RuntimeException("Seat with id " + seadId + " already booked.");
            }

        }

        List<Seat> seats = seatRepository.findAllById(req.getSeatIds());
        if(seats.size() != req.getSeatIds().size())
        {
            throw new RuntimeException("Some seats are invalid.");
        }

        Double totalPrice = seats.size() * show.getTicketPrice();
        Booking booking = Booking.builder()
                .user(user)
                .show(show)
                .seats(seats)
                .totalPrice(totalPrice)
                .status(BookingStatus.CONFIRMED)
                .build();

        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long id)
    {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id."));
    }

    public List<Booking> getBookingByUser(Long userId)
    {
        return bookingRepository.findByUserId(userId);
    }

    public Booking cancelBooking(Long bookingId)
    {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    public List<Seat> getAvailableSeats(Long showId) {
        Show show = showService.getShowById(showId);
        List<Seat> allSeats = seatRepository.findByScreenId(show.getScreen().getId());
        List<Long> bookingSeatIds = bookingRepository.findBookedSeatIdsByShowId(showId);

        return allSeats.stream().filter(seat -> !bookingSeatIds.contains(seat.getId()))
                .toList();
    }
}
