package com.pro.serviceImpl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.dto.BookingReqDto;
import com.pro.entity.Booking;
import com.pro.entity.Flight;
import com.pro.exceptions.BookingNotFoundException;
import com.pro.exceptions.CancelBookingException;
import com.pro.exceptions.FlightNotFoundException;
import com.pro.exceptions.SeatsUnavailableException;
import com.pro.repo.BookingRepo;
import com.pro.repo.FlightRepo;
import com.pro.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepo bookingRepo;

	@Autowired
	FlightRepo flightRepo;

	private String generateBookingCode(int length) {

		Random random = new Random();
		String prefix = "BK-";
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		StringBuilder code = new StringBuilder(prefix);

		for (int i = 0; i < length; i++) {
			code.append(chars.charAt(random.nextInt(chars.length())));
		}

		return code.toString();
	}

	@Override
	public Booking bookTicket(BookingReqDto bookingReqDto) {

		Flight flight = flightRepo.findById(bookingReqDto.getFlightId())
				.orElseThrow(() -> new FlightNotFoundException("Flight Not Found"));
		if (bookingReqDto.getPassengers() > flight.getAvailableSeats()) {
			throw new SeatsUnavailableException("Flight has not enough seat for booking");
		}
		flight.setAvailableSeats(flight.getAvailableSeats() - bookingReqDto.getPassengers());

		Booking book = Booking.builder().flight(flight).passengerName(bookingReqDto.getPassengerName())
				.email(bookingReqDto.getEmail()).phoneNumber(bookingReqDto.getPhone())
				.totalCost(bookingReqDto.getPassengers() * flight.getPrice()).totalTicket(bookingReqDto.getPassengers())
				.bookingCode(generateBookingCode(10)).build();
		Booking save = bookingRepo.save(book);
		return save;

	}

//	@Override
//	public Booking cancelTicket(String code) {
//
//		Booking booking = bookingRepo.findByBookingCode(code);
//
//		if (booking == null) {
//			throw new BookingNotFoundException("Booking not found with code: " + code);
//		}
//
//		if (booking.isCancelled()) {
//			throw new CancelBookingException("Booking Already Cancelled");
//		}
//		Flight flight = booking.getFlight();
//		int totalTicket = booking.getTotalTicket();
//
//		flight.setAvailableSeats(flight.getAvailableSeats() + totalTicket);
//
//		flightRepo.save(flight);
//		
//		booking.setCancelled(true);
//		Booking save = bookingRepo.save(booking);
//
//		return save;
//
//	}
	
	@Override
	public Booking cancelTicket(String code) {

	   Booking booking = bookingRepo.findByBookingCode(code)
	            .orElseThrow(() -> new BookingNotFoundException("Booking not found with code: " + code));

	    if (booking.isCancelled()) {
	        throw new CancelBookingException("Booking Already Cancelled");
	    }

	    Flight flight = booking.getFlight();
	    int totalTicket = booking.getTotalTicket();

	    flight.setAvailableSeats(flight.getAvailableSeats() + totalTicket);
	    flightRepo.save(flight);

	    booking.setCancelled(true);
	    return bookingRepo.save(booking);
	}


}
