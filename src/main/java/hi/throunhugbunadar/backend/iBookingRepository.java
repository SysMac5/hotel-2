package hi.throunhugbunadar.backend;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface iBookingRepository {
    int howManyAvailable(Reservation reservation) throws SQLException;

    boolean reserveRooms(Reservation reservation);

    List<Reservation> getReservations(Hotel hotel);
}
