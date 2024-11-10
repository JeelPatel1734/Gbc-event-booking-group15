package ca.gbc.roomservice.service;

import ca.gbc.roomservice.dto.RoomRequest;
import ca.gbc.roomservice.dto.RoomResponse;
import ca.gbc.roomservice.model.Room;
import ca.gbc.roomservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomResponse createRoom(RoomRequest roomRequest) {
        Room room = new Room(null, roomRequest.roomName(), roomRequest.capacity(), roomRequest.available(), roomRequest.features());
        room = roomRepository.save(room);
        return mapToRoomResponse(room);
    }

    @Override
    public RoomResponse getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + id));
        return mapToRoomResponse(room);
    }

    @Override
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::mapToRoomResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomResponse> getRoomsByAvailability(boolean available) {
        return roomRepository.findByAvailable(available).stream()
                .map(this::mapToRoomResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoomResponse updateRoom(Long id, RoomRequest roomRequest) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + id));

        room.setRoomName(roomRequest.roomName());
        room.setCapacity(roomRequest.capacity());
        room.setAvailable(roomRequest.available());
        room.setFeatures(roomRequest.features());

        room = roomRepository.save(room);
        return mapToRoomResponse(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    private RoomResponse mapToRoomResponse(Room room) {
        return new RoomResponse(
                room.getId(),
                room.getRoomName(),
                room.getCapacity(),
                room.isAvailable(),
                room.getFeatures()
        );
    }
}
