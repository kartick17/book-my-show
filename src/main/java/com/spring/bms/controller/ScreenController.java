package com.spring.bms.controller;

import com.spring.bms.dto.ScreenRequest;
import com.spring.bms.dto.SeatRequest;
import com.spring.bms.entity.Screen;
import com.spring.bms.entity.Seat;
import com.spring.bms.service.MovieService;
import com.spring.bms.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screeens")
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenService screenService;
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<Screen> addScreen(ScreenRequest request)
    {
        return ResponseEntity.ok(screenService.addScreen(request));
    }

    @GetMapping
    public ResponseEntity<List<Screen>> getAllScreens()
    {
        return ResponseEntity.ok(screenService.getAllScreen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Screen> getScreenById(@PathVariable Long id)
    {
        return ResponseEntity.ok(screenService.getScreenById(id));
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<Screen>> getScreenByTheater(@PathVariable Long theaterId)
    {
        return ResponseEntity.ok(screenService.getScreenByTheater(theaterId));
    }
}
