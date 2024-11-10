package ca.gbc.eventservice.service;

import ca.gbc.eventservice.dto.EventRequest;
import ca.gbc.eventservice.dto.EventResponse;
import ca.gbc.eventservice.model.Event;
import ca.gbc.eventservice.repository.EventRepository;
import ca.gbc.eventservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, RestTemplate restTemplate) {
        this.eventRepository = eventRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public EventResponse createEvent(EventRequest eventRequest) {
        // Check if booking exists
        String bookingServiceUrl = "http://localhost:8085/api/bookings/" + eventRequest.bookingId();
        Boolean bookingExists = restTemplate.getForObject(bookingServiceUrl, Boolean.class);

        if (Boolean.FALSE.equals(bookingExists)) {
            return new EventResponse("Booking does not exist", null, null, null, 0, null);
        }

        // Check user role
        String userServiceUrl = "http://localhost:8087/api/users/" + eventRequest.userId() + "/role";
        String userRole = restTemplate.getForObject(userServiceUrl, String.class);

        if ("student".equalsIgnoreCase(userRole) && eventRequest.expectedAttendees() > 50) {
            return new EventResponse("Students cannot have more than 50 attendees", null, null, null, 0, null);
        }

        // Save the event
        Event event = new Event();
        event.setEventName(eventRequest.eventName());
        event.setEventType(eventRequest.eventType());
        event.setExpectedAttendees(eventRequest.expectedAttendees());
        event.setBookingId(eventRequest.bookingId());

        Event savedEvent = eventRepository.save(event);

        return new EventResponse("Event created successfully", savedEvent.getId().toString(), savedEvent.getEventName(),
                savedEvent.getEventType(), savedEvent.getExpectedAttendees(), savedEvent.getBookingId());
    }

    @Override
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(event -> new EventResponse("Event found", event.getId().toString(), event.getEventName(),
                        event.getEventType(), event.getExpectedAttendees(), event.getBookingId()))
                .collect(Collectors.toList());
    }

    @Override
    public EventResponse getEventById(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(String.valueOf(id));
        if (eventOptional.isEmpty()) {
            return new EventResponse("Event not found", null, null, null, 0, null);
        }
        Event event = eventOptional.get();
        return new EventResponse("Event found", event.getId().toString(), event.getEventName(), event.getEventType(),
                event.getExpectedAttendees(), event.getBookingId());
    }
}
