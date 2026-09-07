package com.pro.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "flightNumber")
	private String flightNumber;
	@Column(name = "airLine")
	private String air_Line;
	
	@Column(name = "from_location")
	private String from;
	@Column(name = "to_location")
	private String to;

	@Column(name = "travelDate")
	private LocalDate travelDate;
	@Column(name = "departureTime")
	private LocalDateTime departureTime;
	@Column(name = "arrivalTime")
	private LocalDateTime arrivalTime;
	@Column(name = "availableSeats")
	private Integer availableSeats;

	@Column(name = "price")
	private Double price;
}
