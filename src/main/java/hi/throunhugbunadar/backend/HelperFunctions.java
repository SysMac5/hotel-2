package hi.throunhugbunadar.backend;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class HelperFunctions {
    /**
     * Skilar deginum eftir {@code day}.
     *
     * @param day dagur
     * @return dagurinn eftir {@code day}
     */
    static Date nextDay(Date day) {
        if (day == null) throw new NullPointerException();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(java.util.Calendar.DAY_OF_YEAR, 1);
        java.util.Date dayAfter = calendar.getTime();
        return new java.sql.Date(dayAfter.getTime());
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
    static int howManyAvailable(Date arrival, Date departure, int hotelRoomId, Connection connection) throws SQLException {
        int min = Integer.MAX_VALUE;
        for (Date day = arrival; day.before(departure); day = nextDay(day)) {
            if (howManyAvailable(day, hotelRoomId, connection) < min) {
                min = howManyAvailable(day, hotelRoomId, connection);
            }
        }
        return min;
    }


    /**
     * Hjálparfall skilar fjölda herbergja af ákveðinni hotelherbergjategund
     * sem eru laus nóttina eftir {@code dayBefore}.
     *
     * @param dayBefore dagur fyrir nóttina sem er athuguð
     * @param hotelRoomId auðkenni hotelherbergjategundar
     * @return fjöldi herbergja laus
     */
    private static int howManyAvailable(Date dayBefore, int hotelRoomId, Connection connection) throws SQLException {
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

    /**
     * Skilar lista yfir myndir af hóteli með auðkenni {@code hotelId}.
     *
     * @param hotelId auðkennið
     * @return myndalistinn
     */
    static ArrayList<File> getImageList(int hotelId, Connection connection) {
        ArrayList<File> images = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM pictures WHERE hotel_id = ?");
            statement.setInt(1, hotelId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String url = "src/main/resources/images/" + resultSet.getString("file_name");
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
    static ArrayList<HotelRooms> getHotelRoomsList(int hotelId, Connection connection) {
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
}
