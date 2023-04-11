package hi.throunhugbunadar.backend;

import java.sql.Date;
import java.util.List;

public class BookingController {
    private final iBookingRepository bookingRepository;

    /**
     * Smiður fyrir klasann.
     */
    public BookingController(iBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    private int howManyAvailable(Reservation reservation) {
        return bookingRepository.howManyAvailable(reservation);
    }

    public boolean reserveRooms(Reservation reservation) { // óklárað ! !
        throw new UnsupportedOperationException();
    }

    public List<Reservation> getReservations(Hotel hotel) { // óklárað ! !
        throw new UnsupportedOperationException();
    }
}
