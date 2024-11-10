package ca.gbc.eventservice.service;

import ca.gbc.eventservice.dto.EventRequest;
import ca.gbc.eventservice.dto.EventResponse;

import java.util.List;

public interface EventService {
    EventResponse createEvent(EventRequest request);
    List<EventResponse> getAllEvents();
    EventResponse getEventById(Long id);
}
