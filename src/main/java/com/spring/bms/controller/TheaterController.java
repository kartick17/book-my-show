package com.spring.bms.controller;

import com.spring.bms.dto.TheaterRequest;
import com.spring.bms.entity.Theater;
import com.spring.bms.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theraters")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;

    @PostMapping
    public ResponseEntity<Theater> addTheater(TheaterRequest req)
    {
        return ResponseEntity.ok(theaterService.addTheater(req));
    }

    @GetMapping
    public ResponseEntity<List<Theater>> getALlTheater()
    {
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id)
    {
        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<Theater>> getTheaterByCity(@PathVariable Long id)
    {
        return ResponseEntity.ok(theaterService.getTheaterByCity(id));
    }
}
