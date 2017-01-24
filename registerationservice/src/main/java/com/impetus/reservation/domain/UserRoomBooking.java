package com.impetus.reservation.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROOM_MAPPING")
public class UserRoomBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOKING_ID")
	Long bookingId;
	@Column(name = "USER_ID")
	Long userId;
	@Column(name = "ROOM_ID")
	Long roomId;
	@Column(name = "BOOKING_START")
	Date bookingStart;
	@Column(name = "BOOKING_END")
	Date bookingEnd;

	public UserRoomBooking(Long userId, Long roomId, Date bookingStart, Date bookingEnd) {
		this.userId = userId;
		this.roomId = roomId;
		this.bookingStart = bookingStart;
		this.bookingEnd = bookingEnd;
	}
	public UserRoomBooking(){}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public Date getBookingStart() {
		return bookingStart;
	}
	public void setBookingStart(Date bookingStart) {
		this.bookingStart = bookingStart;
	}
	public Date getBookingEnd() {
		return bookingEnd;
	}
	public void setBookingEnd(Date bookingEnd) {
		this.bookingEnd = bookingEnd;
	};

	

}
