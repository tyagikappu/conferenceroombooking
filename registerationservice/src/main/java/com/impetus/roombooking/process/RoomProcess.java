package com.impetus.roombooking.process;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.impetus.roombooking.domain.Room;
import com.impetus.roombooking.domain.RoomInfo;
import com.impetus.roombooking.repositories.RoomRepository;
import com.impetus.userregistration.service.Response;

@Component
public class RoomProcess {

	@Autowired
	RoomRepository roomRepository;

	public Response findAllRooms() {
		Response response = new Response();

		Iterable<Room> roomList = roomRepository.findAll();
		String roomResult = "";
		Iterator<Room> it = roomList.iterator();
		if (roomList != null) {
			while (it.hasNext()) {
				Room room = it.next();
				roomResult = roomResult + " Room ID : " + room.getId()
						+ " Room Name : " + room.getRoomName() + "\n";

			}
		}
		response.setMessage("*********Room List******\n" + roomResult);
		response.setStatus("OK");

		return response;

	}

	public Response saveRoom(RoomInfo roomInfo) {
		Response response = new Response();

		Room room = new Room(roomInfo.getId(), roomInfo.getRoomName());
		roomRepository.save(room);
		response.setMessage("*********Room " + roomInfo.getRoomName()
				+ " Saved******");
		response.setStatus("OK");

		return response;

	}
}
