package com.spring.bms.controller;

import com.spring.bms.dto.SeatRequest;
import com.spring.bms.entity.Seat;
import com.spring.bms.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping
    public ResponseEntity<Seat> addSeat(SeatRequest request)
    {
        return ResponseEntity.ok(seatService.addSeat(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id)
    {
        return ResponseEntity.ok(seatService.getSeatById(id));
    }

    @GetMapping("/screen/{screenId}")
    public ResponseEntity<List<Seat>> getSeatsByScreen(@PathVariable Long screenId)
    {
        return ResponseEntity.ok(seatService.getSeatsByScreen(screenId));
    }
}
