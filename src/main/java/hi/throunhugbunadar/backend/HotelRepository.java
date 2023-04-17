package hi.throunhugbunadar.backend;

import java.sql.*;
import java.util.ArrayList;

/**
 * Geymsla fyrir hótelin.
 */

public class HotelRepository implements iHotelRepository {
    Connection connection;

    /**
     * Smiður fyrir geymsluna.
     *
     * @param connection tenging við SQLite gagnagrunninn.
     */
    public HotelRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Finnur og skilar lista af hótelum sem uppfylla leit m.t.t. nafn hótels.
     *
     * @param hotelName Nafn á hóteli sem leitað er eftir.
     * @return ArrayList með hótelum sem uppfylla leit.
     */
    @Override
    public ArrayList<Hotel> searchForHotel(String hotelName) {
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hotels WHERE LOWER(hotel_name) LIKE ?");
            statement.setString(1, "%" + hotelName + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                hotels.add(new Hotel(resultSet.getInt("id"),
                        resultSet.getString("hotel_name"),
                        resultSet.getString("information"),
                        HelperFunctions.getImageList(resultSet.getInt("id"), connection),
                        HelperFunctions.getHotelRoomsList(resultSet.getInt("id"), connection),
                        resultSet.getInt("stars"),
                        resultSet.getString("location")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotels;
    }

    /**
     * Finnur og skilar ArrayList sem inniheldur hótelherbergi sem uppfylla gefin leitarskilyrði.
     *
     * @param criteria Leitarskilyrði sem eru notuð til að leita að hótelherbergjum.
     * @return ArrayList með hótelherbergjum sem uppfylla gefin leitarskilyrði.
     */
    @Override
    public ArrayList<Hotel> searchByCriteria(Criteria criteria) {
        ArrayList<Hotel> hotels = new ArrayList<>();
        if (criteria.arrival.after(criteria.departure)) return hotels;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hotel_rooms hr JOIN hotels h ON hr.hotel_id = h.id WHERE h.location = ? AND number_of_guests = ? ORDER BY hr.capacity DESC");
            statement.setString(1, criteria.location);
            statement.setInt(2, criteria.guestCount);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (HelperFunctions.howManyAvailable(criteria.arrival, criteria.departure, resultSet.getInt("id"), connection) > 0) {
                    hotels.add(new Hotel(resultSet.getInt("hotel_id"),
                            resultSet.getString("hotel_name"),
                            resultSet.getString("information"),
                            HelperFunctions.getImageList(resultSet.getInt("hotel_id"), connection),
                            HelperFunctions.getHotelRoomsList(resultSet.getInt("hotel_id"), connection),
                            resultSet.getInt("stars"),
                            resultSet.getString("location")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotels;
    }
}
