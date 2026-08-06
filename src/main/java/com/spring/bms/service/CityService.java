package com.spring.bms.service;

import com.spring.bms.entity.City;
import com.spring.bms.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    private City addCity(City city)
    {
        return cityRepository.save(city);
    }

    public List<City> getAllCities()
    {
        return cityRepository.findAll();
    }

    public City getCityById(Long id)
    {
        return cityRepository.findById(id)
                .orElseThrow(()->new RuntimeException("City not found with id: "+id));
    }
}
