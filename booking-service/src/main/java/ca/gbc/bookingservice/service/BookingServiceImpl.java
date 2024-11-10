package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.dto.BookingRequest;
import ca.gbc.bookingservice.dto.BookingResponse;
import ca.gbc.bookingservice.model.Booking;
import ca.gbc.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String ROOM_SERVICE_URL = "http://room-service/api/rooms/";

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Boolean isAvailable = restTemplate.getForObject(ROOM_SERVICE_URL + "available/" + bookingRequest.roomId(), Boolean.class);

        if (isAvailable != null && isAvailable) {
            Booking booking = new Booking(null, bookingRequest.roomId(), bookingRequest.userName(), bookingRequest.startTime(), bookingRequest.endTime());
            booking = bookingRepository.save(booking);
            return new BookingResponse(booking.getId(), booking.getRoomId(), booking.getUserName(), booking.getStartTime(), booking.getEndTime());
        } else {
            throw new RuntimeException("Room is not available for booking.");
        }
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(booking -> new BookingResponse(booking.getId(), booking.getRoomId(), booking.getUserName(), booking.getStartTime(), booking.getEndTime()))
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse getBookingById(String id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
        return new BookingResponse(booking.getId(), booking.getRoomId(), booking.getUserName(), booking.getStartTime(), booking.getEndTime());
    }

    @Override
    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}
