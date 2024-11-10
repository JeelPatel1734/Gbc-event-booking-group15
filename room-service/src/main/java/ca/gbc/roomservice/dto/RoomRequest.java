package ca.gbc.roomservice.dto;

public record RoomRequest(String roomName, int capacity, boolean available, String features) {
    // `features` is expected as a comma-separated string
}
