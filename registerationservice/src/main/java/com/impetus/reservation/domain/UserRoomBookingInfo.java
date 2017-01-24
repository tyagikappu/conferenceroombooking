package com.impetus.reservation.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserRoomBookingInfo {
	Long bookingId;
	Long userId;
	Long roomId;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	String bookingStart;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	String bookingEnd;

	public UserRoomBookingInfo(Long bookingId, Long userId, Long roomId,
			String bookingStart, String bookingEnd) {
		this.bookingId = bookingId;
		this.userId = userId;
		this.roomId = roomId;
		this.bookingStart = bookingStart;
		this.bookingEnd = bookingEnd;
	}

	public UserRoomBookingInfo() {
	}

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

	public String getBookingStart() {
		return bookingStart;
	}
	public void setBookingStart(String bookingStart) {
		this.bookingStart = bookingStart;
	}
	public String getBookingEnd() {
		return bookingEnd;
	}
	public void setBookingEnd(String bookingEnd) {
		this.bookingEnd = bookingEnd;
	};
}
