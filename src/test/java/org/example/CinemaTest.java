package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CinemaTest {
    private Cinema cinema;

    @BeforeEach
    void setUp() {
        cinema = new Cinema();
    }

    @Test
    void testBookingSeats() {
        assertTrue(cinema.bookSeats(0, 0, new int[]{1, 2, 3}), "Should successfully book seats");
    }

    @Test
    void testBookingSameSeatsTwice() {
        cinema.bookSeats(0, 0, new int[]{4, 5, 6});
        assertFalse(cinema.bookSeats(0, 0, new int[]{4, 5, 6}), "Should fail to book already booked seats");
    }

    @Test
    void testCancelBooking() {
        cinema.bookSeats(0, 0, new int[]{7, 8, 9});
        assertTrue(cinema.cancelBooking(0, 0, new int[]{7, 8, 9}), "Should successfully cancel booking");
    }

    @Test
    void testCancelNonExistingBooking() {
        assertFalse(cinema.cancelBooking(0, 0, new int[]{10, 11, 12}), "Should fail to cancel non-existing booking");
    }

    @Test
    void testCheckAvailability() {
        assertTrue(cinema.checkAvailability(0, 3), "Should find available seats");
    }

    @Test
    void testAutoBooking() {
        assertTrue(cinema.autoBook(0, 3), "Should successfully auto book seats");
    }

    @Test
    void testFindBestAvailable() {
        assertNotNull(cinema.findBestAvailable(0, 3), "Should find best available seats");
    }

    @Test
    void testInvalidHallNumber() {
        assertFalse(cinema.bookSeats(-1, 0, new int[]{1, 2, 3}), "Should not book seats for invalid hall number");
    }
}

