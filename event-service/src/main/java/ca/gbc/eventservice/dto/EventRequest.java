package ca.gbc.eventservice.dto;

public record EventRequest(
        Long bookingId,
        Long userId,
        String eventName,
        String eventType,
        int expectedAttendees
) {}
