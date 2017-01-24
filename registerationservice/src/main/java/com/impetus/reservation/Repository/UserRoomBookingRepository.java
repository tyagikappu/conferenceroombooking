package com.impetus.reservation.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.impetus.reservation.domain.UserRoomBooking;

@Repository
public interface UserRoomBookingRepository extends CrudRepository<UserRoomBooking, Long> {
	List<UserRoomBooking> findUserRoomBookingByRoomId(Long roomId);
}
