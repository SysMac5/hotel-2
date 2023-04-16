package hi.throunhugbunadar.backend;

import java.sql.SQLException;
import java.util.ArrayList;

public interface iBookingRepository {
    int howManyAvailable(Reservation reservation) throws SQLException;

    boolean reserveRooms(Reservation reservation);

    ArrayList<Reservation> getReservations(Hotel hotel);
}
