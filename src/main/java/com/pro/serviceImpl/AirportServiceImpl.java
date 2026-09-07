package com.pro.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.entity.Airport;
import com.pro.repo.AirportRepo;
import com.pro.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired
	private AirportRepo airRepo;

	@Override
	public List<Airport> searchBydata(String input) {

		List<Airport> list = airRepo.findByCodeContainingIgnoreCaseOrNameContainingIgnoreCaseOrCityContainingIgnoreCase(
				input, input, input);
		return list;
	}

}
