package com.impetus.reservation.process;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.impetus.reservation.Repository.UserRoomBookingRepository;
import com.impetus.reservation.domain.UserRoomBookingInfo;
import com.impetus.reservation.domain.UserRoomBooking;

@Component
public class ReservationProcess {

	@Autowired
	UserRoomBookingRepository userRoomBookingRepository;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	public void bookRoom(Map<String, Object> bookingMap) {

		UserRoomBooking userRoomBooking = new UserRoomBooking(
				(Long) bookingMap.get("USER_ID"),
				(Long) bookingMap.get("ROOM_ID"),
				(Date) bookingMap.get("BOOKING_START"),
				(Date) bookingMap.get("BOOKING_END"));
		System.out.println("Inside booking Process::::"
				+ userRoomBooking.getRoomId());

		List<UserRoomBooking> roomBookingList = null;
		roomBookingList = userRoomBookingRepository
				.findUserRoomBookingByRoomId(userRoomBooking.getRoomId());
		Date bookingStartDate = (Date) bookingMap.get("BOOKING_START");
		Date bookingEndDate = (Date) bookingMap.get("BOOKING_END");
		/*
		 * Check availability for rooms for the dates
		 */
		// TODO : Will move it to Database level later
		boolean isRoomAvailable = true;
		if (roomBookingList != null && roomBookingList.size() > 0) {
			for (int i = 0; i < roomBookingList.size(); i++) {

				UserRoomBooking roomBooking = roomBookingList.get(i);
				Date bookedStart = roomBooking.getBookingStart();
				Date bookedEnd = roomBooking.getBookingEnd();

				if (bookingStartDate.equals(bookedStart) || bookingStartDate.equals(bookedEnd) || bookingEndDate.equals(bookedEnd) || bookingEndDate.equals(bookedStart) || (bookingStartDate.after(bookedStart) && bookingStartDate
						.before(bookedEnd))
						|| (bookingEndDate.after(bookedStart) && bookingEndDate
								.before(bookedEnd))) {
					isRoomAvailable = false;
				}
			}
		}

		if (isRoomAvailable) {
			userRoomBookingRepository.save(userRoomBooking);
			System.out.println("****Room booking successful!*******");
		} else {
			System.out
					.println("Sorry, The room is not available for this time slot, please change room or time!");
		}

	}
}
