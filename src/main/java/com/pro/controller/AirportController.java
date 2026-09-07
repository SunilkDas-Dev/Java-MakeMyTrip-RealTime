package com.pro.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.constants.ConstantMessage;
import com.pro.dto.ResponseMessageDto;
import com.pro.entity.Airport;
import com.pro.service.AirportService;

@RestController
public class AirportController {

	@Autowired
	private AirportService airportService;


	@GetMapping(ConstantMessage.searchAirport)
	//@GetMapping("/searchAirport")
	public ResponseEntity<ResponseMessageDto> searchByAirport(@RequestParam String input) {

	    if (input == null || input.isBlank()) {
	        return ResponseEntity.status(HttpURLConnection.HTTP_BAD_REQUEST)
	                .body(new ResponseMessageDto(ConstantMessage.FAILED,
	                        "You have entered blank Input", null, null));
	    }

	    List<Airport> searchBydata = airportService.searchBydata(input);

	    if (!searchBydata.isEmpty()) {
	        return ResponseEntity.status(HttpURLConnection.HTTP_OK)
	                .body(new ResponseMessageDto(ConstantMessage.SUCCESS,
	                        "The desired Airport Found", null, searchBydata));
	    }

	    return ResponseEntity.status(HttpURLConnection.HTTP_CONFLICT)
	            .body(new ResponseMessageDto(ConstantMessage.FAILED,
	                    "The Airport With Given Data Not Found", null, null));
	}


}
