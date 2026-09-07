package com.pro.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

	// Booking findByBookingCode(String bookingCode);
	 Optional<Booking> findByBookingCode(String bookingCode);

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Booking b WHERE b.bookingCode = :code")
      Object deleteByBookingCode(String code);

	
}
