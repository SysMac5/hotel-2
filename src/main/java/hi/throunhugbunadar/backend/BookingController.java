package hi.throunhugbunadar.backend;

import java.util.ArrayList;

public class BookingController {
    private final iBookingRepository bookingRepository;

    /**
     * Smiður fyrir klasann.
     */
    public BookingController(iBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Skilar fjölda herbergja sem eru laus af sömu tegund og herbergi í bókun yfir sama tímabil og bókun.
     *
     * @param reservation bókun
     * @return fjöldi herbergja laus
     */
    private int howManyAvailable(Reservation reservation) {
        return bookingRepository.howManyAvailable(reservation);
    }

    /**
     * Vistar bókun.
     *
     * @param reservation bókun
     * @return hvort vistun hafi heppnast
     */
    public boolean reserveRooms(Reservation reservation) {
        return bookingRepository.reserveRooms(reservation);
    }

    /**
     * Skilar bókunum fyrir gefið hótel.
     *
     * @param hotel hótel
     * @return bókanir
     */
    public ArrayList<Reservation> getReservations(Hotel hotel) {
        return bookingRepository.getReservations(hotel);
    }
}
