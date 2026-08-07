package com.spring.bms.service;


import com.spring.bms.dto.ScreenRequest;
import com.spring.bms.entity.Screen;
import com.spring.bms.entity.Theater;
import com.spring.bms.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final TheaterService theaterService;

    public Screen addScreen(ScreenRequest screenRequest)
    {
        Theater theater = theaterService.getTheaterById(screenRequest.getTheaterId());
        Screen screen = Screen.builder()
                .name(screenRequest.getName())
                .totalSeats(screenRequest.getTotalSeats())
                .theater(theater)
                .build();

        return screenRepository.save(screen);
    }

    public List<Screen> getAllScreen()
    {
        return screenRepository.findAll();
    }

    public Screen getScreenById(Long id)
    {
        return screenRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Screen not found with id: "+id));

    }

    public List<Screen> getScreenByTheater(Long theaterId)
    {
        return screenRepository.findByTheaterId(theaterId);
    }
}