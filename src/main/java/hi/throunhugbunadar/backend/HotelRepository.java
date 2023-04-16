package hi.throunhugbunadar.backend;


import java.io.File;
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
                        getImageList(resultSet.getInt("id")),
                        getHotelRoomsList(resultSet.getInt("id")),
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
                if (howManyAvailable(criteria.arrival, criteria.departure, resultSet.getInt("id")) > 0) {
                    hotels.add(new Hotel(resultSet.getInt("hotel_id"),
                            resultSet.getString("hotel_name"),
                            resultSet.getString("information"),
                            getImageList(resultSet.getInt("hotel_id")),
                            getHotelRoomsList(resultSet.getInt("hotel_id")),
                            resultSet.getInt("stars"),
                            resultSet.getString("location")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotels;
    }

    /**
     * Skilar lista yfir myndir af hóteli með auðkenni {@code hotelId}.
     *
     * @param hotelId auðkennið
     * @return myndalistinn
     */
    private ArrayList<File> getImageList(int hotelId) {
        ArrayList<File> images = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM pictures WHERE hotel_id = ?");
            statement.setInt(1, hotelId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String url = "images/" + resultSet.getString("file_name");
                File image = new File(url);
                images.add(image);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return images;
    }

    /**
     * Skilar lista yfir tegundir af hótelherbergjum í hóteli með auðkenni {@code hotelId}.
     *
     * @param hotelId auðkennið
     * @return hótelherbergjategundir
     */
    private ArrayList<HotelRooms> getHotelRoomsList(int hotelId) {
        ArrayList<HotelRooms> hotelRooms = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hotel_rooms WHERE hotel_id = ? ORDER BY number_of_guests DESC");
            statement.setInt(1, hotelId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                HotelRooms hotelRoom = new HotelRooms(resultSet.getInt("capacity"),
                        resultSet.getInt("number_of_guests"),
                        resultSet.getInt("price_per_night"),
                        resultSet.getInt("id"));
                hotelRooms.add(hotelRoom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelRooms;
    }

    /**
     * Hjálparfall sem skilar hversu mörg herbergi af
     * hotelherbergjategund eru laus á gefnu tímabili.
     *
     * @param arrival komudagur
     * @param departure brottfarardagur
     * @param hotelRoomId auðkenni hotelherbergjategundar
     * @return fjöldi herbergja laus
     */
    private int howManyAvailable(Date arrival, Date departure, int hotelRoomId) throws SQLException {
        int min = Integer.MAX_VALUE;
        for (Date day = arrival; day.before(departure); day = nextDay(day)) {
            if (howManyAvailable(day, hotelRoomId) < min) {
                min = howManyAvailable(day, hotelRoomId);
            }
        }
        return min;
    }

    /**
     * Hjálparfall sem skilar deginum eftir {@code day}.
     *
     * @param day dagur
     * @return dagurinn eftir {@code day}
     */
    private Date nextDay(Date day) {
        if (day == null) throw new NullPointerException();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(java.util.Calendar.DAY_OF_YEAR, 1);
        java.util.Date dayAfter = calendar.getTime();
        return new java.sql.Date(dayAfter.getTime());
    }

    /**
     * Hjálparfall skilar fjölda herbergja af ákveðinni hotelherbergjategund
     * sem eru laus nóttina eftir {@code dayBefore}.
     *
     * @param dayBefore dagur fyrir nóttina sem er athuguð
     * @param hotelRoomId auðkenni hotelherbergjategundar
     * @return fjöldi herbergja laus
     */
    private int howManyAvailable(Date dayBefore, int hotelRoomId) throws SQLException {
        PreparedStatement statement1 = connection.prepareStatement("SELECT SUM(r.number_of_rooms) AS booked_rooms FROM reservations r " +
                "JOIN hotel_rooms hr ON r.hotel_rooms_id = hr.id WHERE hr.id = ? " +
                "AND (arrival_date <= ? AND departure_date > ?)");


        statement1.setInt(1, hotelRoomId);
        statement1.setDate(2, dayBefore);
        statement1.setDate(3, dayBefore);
        ResultSet result = statement1.executeQuery();

        int occupied = 0;
        if (result.next()) {
            occupied = result.getInt("booked_rooms");
        }

        PreparedStatement statement2 = connection.prepareStatement("SELECT capacity from hotel_rooms where id = ?");
        statement2.setInt(1, hotelRoomId);

        ResultSet result1 = statement2.executeQuery();
        int capacity = 0;
        if (result1.next()) {
            capacity = result1.getInt("capacity");
        }

        return  capacity - occupied;
    }
}
