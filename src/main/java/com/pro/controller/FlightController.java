package com.pro.controller;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pro.constants.ConstantMessage;
import com.pro.dto.FlightServiceDto;
import com.pro.dto.ResponseMessageDto;
import com.pro.entity.Flight;
import com.pro.service.FlightService;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;
    
   

    @PostMapping("/searchFlight")
    public ResponseEntity<ResponseMessageDto> searchFlight(@RequestBody FlightServiceDto flightServiceDto) {

        try {
        	 LocalDate today = LocalDate.now();

            // Validation
            if (flightServiceDto.getFrom().isBlank() ||
                flightServiceDto.getTo().isBlank() ||
                flightServiceDto.getTotalNumber() <= 0 ||
                flightServiceDto.getTravelDate().isBefore(today)) {

                return ResponseEntity.status(HttpURLConnection.HTTP_BAD_REQUEST)
                        .body(new ResponseMessageDto(ConstantMessage.FAILED,
                                "Inserted Data Is Not Correct. Travel date must be today or future."));
            }

            // Correct service call
            List<Flight> flights = flightService.findFlight(flightServiceDto);

            // No flights
            if (flights.isEmpty()) {
                return ResponseEntity.status(HttpURLConnection.HTTP_PRECON_FAILED)
                        .body(new ResponseMessageDto(ConstantMessage.FAILED,
                                "Desired Flight Not Found"));
            }

            // Flights found
            return ResponseEntity.status(HttpURLConnection.HTTP_OK)
                    .body(new ResponseMessageDto(ConstantMessage.SUCCESS,
                            "Flight found for Travel", flights));

        } catch (Exception e) {
            return ResponseEntity.status(HttpURLConnection.HTTP_CONFLICT)
                    .body(new ResponseMessageDto(ConstantMessage.FAILURE,
                            "Internal Server Error, Try again"));
        }
    }
}
