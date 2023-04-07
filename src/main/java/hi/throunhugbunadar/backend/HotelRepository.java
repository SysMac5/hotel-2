package hi.throunhugbunadar.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class HotelRepository implements iHotelRepository{
    Connection connection;

    /**
     * Býr til nýja tengingu við gagnagrunninn.
     *
     * @throws SQLException þegar tenging við gagnagrunn klikkar
     */
    public HotelRepository() throws SQLException {
        String url = "jdbc:sqlite:/path/to/your/database.db"; // óklárað ! !
        connection = DriverManager.getConnection(url);
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
