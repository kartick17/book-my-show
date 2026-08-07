package com.spring.bms.service;


import com.spring.bms.dto.SeatRequest;
import com.spring.bms.entity.Screen;
import com.spring.bms.entity.Seat;
import com.spring.bms.entity.Theater;
import com.spring.bms.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final ScreenService screenService;

    public Seat addSeat(SeatRequest request)
    {
        Screen screen = screenService.getScreenById(request.getScreenId());
        Seat seat = Seat.builder()
                .seatNumber(request.getSeatNumber())
                .seatType(request.getSeatType())
                .col(request.getCol())
                .row(request.getRow())
                .screen(screen)
                .build();

        return seatRepository.save(seat);
    }

    public List<Seat> getSeatsByScreen(Long screenId)
    {
        return seatRepository.findByScreenId(screenId);
    }

    public Seat getSeatById(Long id)
    {
        return seatRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Seat not found with id: "+id));
    }
}