package com.impetus.roombooking.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.impetus.roombooking.domain.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
	//List<Room> findRoomByName(String name);
}
