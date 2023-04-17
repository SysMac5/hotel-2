package hi.throunhugbunadar.backend;

import java.util.ArrayList;

public interface iBookingRepository {
    boolean reserveRooms(Reservation reservation);

    ArrayList<Reservation> getReservations(Hotel hotel);

    int howManyAvailable(Reservation reservation);
}
