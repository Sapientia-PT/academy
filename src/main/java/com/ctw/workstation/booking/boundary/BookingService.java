package com.ctw.workstation.booking.boundary;

import com.ctw.workstation.booking.entity.Booking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookingService {

    @Transactional
    public void addBooking(Booking booking) {
        booking.setCreatedAt(LocalDateTime.now());
        booking.setModifiedAt(LocalDateTime.now());
        booking.persist();
    }

    @Transactional
    public Booking getBooking(UUID id) {
        return Booking.findById(id);
    }

    @Transactional
    public boolean removeBooking(UUID id) {
        return Booking.deleteById(id);
    }

    @Transactional
    public List<Booking> getBookings() {
        return Booking.listAll();
    }

}
