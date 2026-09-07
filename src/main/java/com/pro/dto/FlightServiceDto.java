package com.pro.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FlightServiceDto {
	
	private String from;
	private String to;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate travelDate;
	private int totalNumber;
	

}
