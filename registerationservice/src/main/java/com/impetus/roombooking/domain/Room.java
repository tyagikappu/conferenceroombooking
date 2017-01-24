package com.impetus.roombooking.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOMS")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String roomName;

	public Room(Long id, String roomName) {
		this.id = id;
		this.roomName = roomName;

	}

	public Room() {
	};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

}
