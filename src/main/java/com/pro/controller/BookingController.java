package com.pro.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.constants.ConstantMessage;
import com.pro.dto.BookingReqDto;
import com.pro.dto.ResponseMessageDto;
import com.pro.entity.Booking;
import com.pro.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/bookTicket")
	public ResponseEntity<ResponseMessageDto> bookingFlight(@RequestBody BookingReqDto bookingReqDto) {

		if (bookingReqDto.getPassengerName().isBlank() || bookingReqDto.getEmail().isEmpty()
				|| bookingReqDto.getFlightId() == null || bookingReqDto.getPassengers() <= 0
				|| bookingReqDto.getPhone().isBlank()) {
			return ResponseEntity.status(HttpURLConnection.HTTP_BAD_METHOD).body(new ResponseMessageDto(
					ConstantMessage.FAILED, "Inserted Data Is Blank or Empty Try To Fill That "));

		}
		Booking bookTicket = bookingService.bookTicket(bookingReqDto);
		if (bookTicket != null) {
			return ResponseEntity.status(HttpURLConnection.HTTP_OK)
					.body(new ResponseMessageDto(ConstantMessage.SUCCESS, "Flight booked Successfully", bookTicket));
		} else
			return ResponseEntity.status(HttpURLConnection.HTTP_PRECON_FAILED)
					.body(new ResponseMessageDto(ConstantMessage.FAILED, "Flight Booking failed", bookTicket));
	}

	@DeleteMapping("/cancelTicket/{code}")
	public ResponseEntity<ResponseMessageDto> cancelFlight(@PathVariable String code) {
		try {
			if (code == null || code.length() <= 9 || code.isBlank() || code.isEmpty()) {
				return ResponseEntity.status(HttpURLConnection.HTTP_BAD_METHOD)
						.body(new ResponseMessageDto(ConstantMessage.FAILED,
								"Inserted  Code Is Blank or Empty or length is not matching Try To Fill That "));

			}
			Booking cancelTicket = bookingService.cancelTicket(code);
			if (cancelTicket != null) {
				return ResponseEntity.status(HttpURLConnection.HTTP_OK).body(
						new ResponseMessageDto(ConstantMessage.SUCCESS, "Booking Deleted Successfully", cancelTicket));
			} else {

				return ResponseEntity.status(HttpURLConnection.HTTP_CONFLICT)
						.body(new ResponseMessageDto(ConstantMessage.FAILED, " Ticket Cancellation Failed "));

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpURLConnection.HTTP_CLIENT_TIMEOUT).body(
					new ResponseMessageDto(ConstantMessage.FAILED, " Something Getting Error Try Again Later....... "));
		}

	}

}
