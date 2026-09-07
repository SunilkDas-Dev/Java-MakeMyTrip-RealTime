package com.pro.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.dto.FlightServiceDto;
import com.pro.entity.Flight;
import com.pro.repo.FlightRepo;
import com.pro.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepo flightRepo;

    @Override
    public List<Flight> findFlight(FlightServiceDto dto) {
//        LocalDate date = dto.getTravelDate();
//        LocalDateTime startOfDay = date.atStartOfDay();
//        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

     //   return flightRepo.findFlfindByFromAndToAndDepartureTime(dto.getFrom(), dto.getTo(), startOfDay, endOfDay);
        return flightRepo.findByFromIgnoreCaseAndToIgnoreCaseAndTravelDateAndAvailableSeatsGreaterThanEqual(dto.getFrom(), dto.getTo(), dto.getTravelDate(),dto.getTotalNumber());
    }
}
