package com.impetus.reservation.sender;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.impetus.reservation.domain.UserRoomBookingInfo;
import com.impetus.roombooking.domain.RoomInfo;
import com.impetus.userregistration.service.Response;

@RestController
@RequestMapping("/book")
public class BookingRequestSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	String ROUTING_KEY = "room_book";
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	/*
	 * @Scheduled(fixedDelay = 1000L) public void send() {
	 * this.rabbitTemplate.convertAndSend("foo",
	 * "hello"+System.currentTimeMillis()); }
	 */

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public Response getRooms(
			@RequestBody UserRoomBookingInfo userRoomBookingInfo) {
		Response response = new Response();
		response.setMessage("We have received your booking request. We are working on it nad will update you the result over email. Thank you!");
		response.setStatus("OK");
		System.out.println("****Submitting Booking Request*******"+createBookingMap(userRoomBookingInfo));
		this.rabbitTemplate.convertAndSend(ROUTING_KEY,
				createBookingMap(userRoomBookingInfo));
		return response;
	}

	private Map<String, Object> createBookingMap(
			UserRoomBookingInfo userRoomBookingInfo) {
		Map<String, Object> bookingMap = new HashMap<String, Object>();
		bookingMap.put("USER_ID", userRoomBookingInfo.getUserId());
		bookingMap.put("ROOM_ID", userRoomBookingInfo.getRoomId());
		try{
		bookingMap.put("BOOKING_START", dateFormat.parse(userRoomBookingInfo.getBookingStart()));
		bookingMap.put("BOOKING_END", dateFormat.parse(userRoomBookingInfo.getBookingEnd()));
		}
		catch(Exception e)
		{
			System.out.println("Exception e"+e);
		}
		return bookingMap;
	}

}
