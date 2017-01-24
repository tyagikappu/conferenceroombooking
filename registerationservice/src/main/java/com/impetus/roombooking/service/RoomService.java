package com.impetus.roombooking.service;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.impetus.roombooking.domain.RoomInfo;
import com.impetus.roombooking.process.RoomProcess;

import com.impetus.userregistration.service.Response;

@RestController
@RequestMapping("/room")
public class RoomService {

	@Autowired
	RoomProcess roomProcess;

	@RequestMapping(value="/", method = RequestMethod.GET)
	@ResponseBody
	public Response getRooms() {

		return roomProcess.findAllRooms();
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	@ResponseBody
	public Response getRooms(@RequestBody RoomInfo roomInfo) {

		return roomProcess.saveRoom(roomInfo);
	}

}