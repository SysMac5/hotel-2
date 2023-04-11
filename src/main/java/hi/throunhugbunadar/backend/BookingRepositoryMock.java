package hi.throunhugbunadar.backend;

import java.sql.*;
import java.util.List;

public class BookingRepositoryMock implements iBookingRepository {
    Connection connection;

    /**
     * Býr til nýja tengingu við gagnagrunninn.
     *
     * @throws SQLException þegar tenging við gagnagrunn klikkar
     */
    public BookingRepositoryMock() throws SQLException {
        String url = "jdbc:sqlite:/path/to/your/database.db.db.db"; // óklárað ! !
        connection = DriverManager.getConnection(url);
    }

    public int howManyAvailable(Reservation reservation) { // óklárað ! !
        throw new UnsupportedOperationException();
    }

    public boolean reserveRooms(Reservation reservation) { // óklárað ! !
        throw new UnsupportedOperationException();
    }

    public List<Reservation> getReservations(Hotel hotel) { // óklárað ! !
        throw new UnsupportedOperationException();
    }
}
