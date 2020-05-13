package com.cesi.seatingAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cesi.seatingAPI.exception.ResourceNotFoundException;
import com.cesi.seatingAPI.model.Room;
import com.cesi.seatingAPI.repository.RoomRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/api/rooms")
    public List<Room> getAllrooms() {
        return roomRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/rooms")
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomRepository.save(room);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/roomsPerId/{id}")
    public Room getRoomById(@PathVariable Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("room", "id", id));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/rooms/{id}")
    public Room updateRoom(@PathVariable Integer id,
                                           @Valid @RequestBody Room roomDetails) {

    	Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("room", "id", id));

        room.setRoomName(roomDetails.getRoomName());
        room.setCols(roomDetails.getCols());
        room.setRows(roomDetails.getRows());

        Room updatedroom = roomRepository.save(room);
        return updatedroom;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/rooms/{id}")
    public ResponseEntity<?> deleteroom(@PathVariable Integer id) {
    	Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("room", "id", id));

        roomRepository.delete(room);

        return ResponseEntity.ok().build();
    }
}