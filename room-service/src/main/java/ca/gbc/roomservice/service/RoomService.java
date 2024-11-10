package ca.gbc.roomservice.service;

import ca.gbc.roomservice.dto.RoomRequest;
import ca.gbc.roomservice.dto.RoomResponse;

import java.util.List;

public interface RoomService {
    RoomResponse createRoom(RoomRequest roomRequest);
    RoomResponse getRoomById(Long id);
    List<RoomResponse> getAllRooms();
    List<RoomResponse> getRoomsByAvailability(boolean available); // New method
    RoomResponse updateRoom(Long id, RoomRequest roomRequest);
    void deleteRoom(Long id);
}
