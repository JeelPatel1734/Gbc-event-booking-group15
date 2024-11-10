package ca.gbc.eventservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private Long bookingId;
    private Long userId;
    private String eventName;
    private String eventType;
    private int expectedAttendees;

    // Getters and Setters
}
