package hi.throunhugbunadar.backend;

import java.util.List;

public class BookingController {
    private final iHotelRepository hotelRepository;

    /**
     * Smiður fyrir klasann.
     */
    public BookingController(iHotelRepository  hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public int howManyAvailable() { // óklárað ! !
        throw new UnsupportedOperationException();
    }

    public boolean reserveRooms(Reservation reservation) { // óklárað ! !
        throw new UnsupportedOperationException();
    }

    public List<Reservation> getReservations(Hotel hotel) { // óklárað ! !
        throw new UnsupportedOperationException();
    }
}
