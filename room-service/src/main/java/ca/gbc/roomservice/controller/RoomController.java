package ca.gbc.roomservice.controller;

import ca.gbc.roomservice.dto.RoomRequest;
import ca.gbc.roomservice.dto.RoomResponse;
import ca.gbc.roomservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public RoomResponse createRoom(@RequestBody RoomRequest roomRequest) {
        return roomService.createRoom(roomRequest);
    }

    @GetMapping("/{id}")
    public RoomResponse getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping
    public List<RoomResponse> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/available")
    public List<RoomResponse> getRoomsByAvailability(@RequestParam boolean available) {
        return roomService.getRoomsByAvailability(available);
    }

    @PutMapping("/{id}")
    public RoomResponse updateRoom(@PathVariable Long id, @RequestBody RoomRequest roomRequest) {
        return roomService.updateRoom(id, roomRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
