package com.cesi.seatingAPI.repository;

import com.cesi.seatingAPI.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

}
