package com.pro.service;

import com.pro.dto.BookingReqDto;
import com.pro.entity.Booking;

public interface BookingService {

	Booking bookTicket(BookingReqDto bookingReqDto);

	Booking cancelTicket(String code);

}
