package com.github.marcelomachadoxd.meetingrooms.controller;


import org.springframework.stereotype.Controller;
import com.github.marcelomachadoxd.meetingrooms.exception.ResourceNotFoundException;
import  com.github.marcelomachadoxd.meetingrooms.model.Room;
import com.github.marcelomachadoxd.meetingrooms.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import  org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomRepository repository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return repository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException{
            Room room = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found:: " + id ));

            return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room){
        return repository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public  ResponseEntity<Room> updateRoom(@PathVariable(value = "id") Long id, @Valid @RequestBody Room room)
        throws ResourceNotFoundException{
        room = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id:: " +id ));

        room.setName(room.getName());
        room.setDate(room.getDate());
        room.setStartHour(room.getStartHour());
        room.setEndHour(room.getEndHour());

        final Room updateRoom = repository.save(room);

        return ResponseEntity.ok(updateRoom);

    }


    @DeleteMapping("/rooms{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long id)
        throws ResourceNotFoundException{
        Room room = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(("Room not found for this id:: ") + id));

        repository.delete(room);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }



}

