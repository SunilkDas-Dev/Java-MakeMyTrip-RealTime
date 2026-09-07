package com.pro.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	private Flight flight;

	@Column(name = "psngrName")
	private String passengerName;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "totalTicket")
	private int totalTicket;

	@Column(name = "totalCost")
	private double totalCost;

	@Column(name = "bookingCode")
	private String bookingCode;

	@CreationTimestamp
	@Column(name = "createdDate")
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(name = "updatedDate")
	private LocalDateTime updatedDate;
	
	private boolean cancelled = false;

}
