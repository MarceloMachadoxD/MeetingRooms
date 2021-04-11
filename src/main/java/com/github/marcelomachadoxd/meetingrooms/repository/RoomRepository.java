package com.github.marcelomachadoxd.meetingrooms.repository;

import com.github.marcelomachadoxd.meetingrooms.model.Room;
import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
