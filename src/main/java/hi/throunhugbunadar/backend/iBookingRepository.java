package hi.throunhugbunadar.backend;

import java.sql.Date;
import java.util.List;

public interface iBookingRepository {
    int howManyAvailable(Date dayBefore);

    boolean reserveRooms(Reservation reservation);

    List<Reservation> getReservations(Hotel hotel);
}
