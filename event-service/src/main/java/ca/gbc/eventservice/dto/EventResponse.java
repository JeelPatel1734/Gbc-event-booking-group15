package ca.gbc.eventservice.dto;

public record EventResponse(
        String message,
        String eventId,
        String eventName,
        String eventType,
        int expectedAttendees,
        Long bookingId
) {}
