package ca.gbc.bookingservice.dto;

import java.time.LocalDateTime;

public record BookingRequest(Long roomId, String userName, LocalDateTime startTime, LocalDateTime endTime) {}
