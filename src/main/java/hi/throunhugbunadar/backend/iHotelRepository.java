package hi.throunhugbunadar.backend;

import java.util.List;

public interface iHotelRepository {
    int howManyAvailable();

    boolean reserveRooms(Reservation reservation);

    List<Reservation> getReservations(Hotel hotel);
}
