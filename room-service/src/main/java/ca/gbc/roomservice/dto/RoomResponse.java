package ca.gbc.roomservice.dto;

public record RoomResponse(Long id, String roomName, int capacity, boolean available, String features) {
    // `features` will be returned as a comma-separated string
}
