package hi.throunhugbunadar.backend;

import java.sql.*;
import java.util.ArrayList;

public class BookingRepository implements iBookingRepository {
    Connection connection;

    /**
     * Býr til nýja tengingu við gagnagrunninn.
     */
    public BookingRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Vistar bókun í gagnagrunn.
     *
     * @param reservation bókun
     * @return hvort vistun hafi heppnast
     */
    public boolean reserveRooms(Reservation reservation) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT max(id) AS max FROM reservations");
            int maxId = statement.executeQuery().getInt("max") + 1;

            statement = connection.prepareStatement("INSERT INTO reservations (id, hotel_rooms_id, user_id, number_of_rooms, arrival_date, departure_date) VALUES (?,?,?,?,?,?)");
            statement.setInt(1, maxId);
            statement.setInt(2, reservation.getHotel().getHotelRoom(reservation.getGuestPerRoom()).getId());
            statement.setString(3, reservation.getUser().getUsername());
            statement.setInt(4, reservation.getNumberOfRooms());
            statement.setDate(5, reservation.getArrival());
            statement.setDate(6, reservation.getDeparture());
            statement.executeUpdate();

            statement = connection.prepareStatement("SELECT max(id) AS max FROM reservations");
            int newMaxId = statement.executeQuery().getInt("max");
            return maxId == newMaxId;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Skilar fjölda herbergja sem eru laus af sömu tegund og herbergi í bókun yfir sama tímabil og bókun.
     *
     * @param reservation bókun
     * @return fjöldi herbergja laus
     */
    public int howManyAvailable(Reservation reservation) {
        try {
            return HelperFunctions.howManyAvailable(reservation.getArrival(), reservation.getDeparture(), reservation.getHotel().getHotelRoom(reservation.getGuestPerRoom()).getId(), connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Skilar bókunum fyrir gefið hótel.
     *
     * @param hotel hótel
     * @return bókanir
     */
    public ArrayList<Reservation> getReservations(Hotel hotel) { // óklárað ! !
        try {
            ArrayList<Reservation> reservationsList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations r LEFT JOIN hotel_rooms hr ON r.hotel_rooms_id = hr.id JOIN hotels h ON hr.hotel_id = h.id JOIN users u ON u.username = r.user_id JOIN payment_info pi ON u.payment_info_id = pi.id WHERE h.id = ?");

            statement.setInt(1, hotel.getId());
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                PaymentInfo paymentInfo = new PaymentInfo(
                        resultSet.getString("card_number"),
                        resultSet.getString("month_valid"),
                        resultSet.getString("year_valid"),
                        resultSet.getString("cvv"));

                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        paymentInfo);

                reservationsList.add(new Reservation(
                        user,
                        hotel,
                        resultSet.getDate("arrival_date"),
                        resultSet.getDate("departure_date"),
                        resultSet.getInt("number_of_guests"),
                        resultSet.getInt("number_of_rooms")));

            }

            return reservationsList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
