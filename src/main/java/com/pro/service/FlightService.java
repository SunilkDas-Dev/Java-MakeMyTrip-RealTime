package com.pro.service;

import java.util.List;

import com.pro.dto.FlightServiceDto;
import com.pro.entity.Flight;

public interface FlightService {

	List<Flight> findFlight(FlightServiceDto flightServiceDto);

}
