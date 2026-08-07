package com.spring.bms.service;


import com.spring.bms.dto.TheaterRequest;
import com.spring.bms.entity.City;
import com.spring.bms.entity.Theater;
import com.spring.bms.repository.CityRepository;
import com.spring.bms.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.CharacterIterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final CityService cityService;


    public Theater addTheater(TheaterRequest request)
    {
        City city=cityService.getCityById(request.getCityId());
        Theater theater=Theater.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(city)
                .build();
        return theaterRepository.save(theater);
    }

    public List<Theater> getAllTheaters()
    {
        return theaterRepository.findAll();
    }

    public Theater getTheaterById(Long id)
    {
        return theaterRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Theater not found with id: "+id));

    }

    public List<Theater> getTheaterByCity(Long cityId)
    {
        return theaterRepository.findByCityId(cityId);
    }
}