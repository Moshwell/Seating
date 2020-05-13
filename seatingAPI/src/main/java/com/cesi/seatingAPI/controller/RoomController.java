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
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllrooms() {
        return roomRepository.findAll();
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomRepository.save(room);
    }

    @GetMapping("/rooms/{id}")
    public Room getRoomById(@PathVariable(value = "id") Integer roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("room", "id", roomId));
    }

    @PutMapping("/rooms/{id}")
    public Room updateRoom(@PathVariable(value = "id") Integer roomId,
                                           @Valid @RequestBody Room roomDetails) {

    	Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("room", "id", roomId));

        room.setRoomName(roomDetails.getRoomName());
        room.setCols(roomDetails.getCols());
        room.setRows(roomDetails.getRows());

        Room updatedroom = roomRepository.save(room);
        return updatedroom;
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<?> deleteroom(@PathVariable(value = "id") Integer roomId) {
    	Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("room", "id", roomId));

        roomRepository.delete(room);

        return ResponseEntity.ok().build();
    }
}