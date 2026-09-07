package com.pro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entity.Airport;

public interface AirportRepo  extends JpaRepository<Airport, Long>{

	List<Airport> findByCodeContainingIgnoreCaseOrNameContainingIgnoreCaseOrCityContainingIgnoreCase(String input, String input2,
			String input3);

}
