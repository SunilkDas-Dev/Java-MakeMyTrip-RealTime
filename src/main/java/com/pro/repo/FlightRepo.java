package com.pro.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.entity.Flight;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {

	
	List<Flight> findByFromIgnoreCaseAndToIgnoreCaseAndTravelDateAndAvailableSeatsGreaterThanEqual(String from,
			String to, LocalDate travelDate, int totalNumber);

}