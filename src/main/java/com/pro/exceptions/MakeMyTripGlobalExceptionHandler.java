package com.pro.exceptions;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pro.constants.ConstantMessage;
import com.pro.dto.ErrorResponseDto;

//@ControllerAdvice
@RestControllerAdvice
public class MakeMyTripGlobalExceptionHandler {

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleFlightnotfound(FlightNotFoundException ex) {

		HashMap<String, Object> hm = new HashMap<>();
		hm.put("Error:", "Flight Not Found");
		hm.put("Detailed Message:", ex.getLocalizedMessage());
		hm.put("Timestamp:", System.currentTimeMillis());
		ErrorResponseDto err = new ErrorResponseDto(HttpURLConnection.HTTP_BAD_REQUEST, ConstantMessage.FAILED,
				"Flight Number MisMatched Try Again", hm);
		return ResponseEntity.ok(err);

	}

	@ExceptionHandler(SeatsUnavailableException.class)
	public ResponseEntity<Object> handleSeatsnotfound(SeatsUnavailableException ex) {

		List<String> list = new ArrayList<>();
		list.add("Error : Required Amount Of Seats Not Available");
		list.add("Detailed Message: " + ex.getLocalizedMessage());
		list.add("Timestamp: " + System.currentTimeMillis());
		ErrorResponseDto err = new ErrorResponseDto(HttpURLConnection.HTTP_BAD_REQUEST, ConstantMessage.FAILED,
				"SEATS-NOT-FOUND", list);
		return ResponseEntity.ok(err);

	}

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<Object> handleBookingNotFound(BookingNotFoundException ex) {

		List<String> list = new ArrayList<>();
		list.add("Error : The Entered Ticket Number is invalid :");
		list.add("Detailed message :" + ex.getLocalizedMessage());
		list.add("Time Stamp :" + System.currentTimeMillis());

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpURLConnection.HTTP_BAD_REQUEST,
				ConstantMessage.FAILED, " Booking Not Found", list);
		return ResponseEntity.ok(errorResponseDto);
	}

	@ExceptionHandler(CancelBookingException.class)
	public ResponseEntity<Object> handleCancelBooking(CancelBookingException ex) {

		List<String> list = new ArrayList<>();
		list.add("Error : The Ticket has already cancelled ");
		list.add(ex.getLocalizedMessage());
		list.add("Time Stamp :" + System.currentTimeMillis());
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpURLConnection.HTTP_BAD_REQUEST,
				ConstantMessage.FAILED, " ", list);

		return ResponseEntity.ok(errorResponseDto);
	}

}