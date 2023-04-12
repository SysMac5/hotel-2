package hi.throunhugbunadar.backend;

import java.sql.*;
import java.util.List;

public class BookingRepository implements iBookingRepository {
    Connection connection;

    /**
     * Býr til nýja tengingu við gagnagrunninn.
     *
     * @throws SQLException þegar tenging við gagnagrunn klikkar
     */
    public BookingRepository() throws SQLException {
        String url = "jdbc:sqlite:GG_8.db";
        connection = DriverManager.getConnection(url);
    }

    /**
     * Skilar hversu mörg herbergi eru laus fyrir gefna bókun.
     *
     * @param reservation bókunin
     * @return fjöldi herbergja laus
     */
    public int howManyAvailable(Reservation reservation) { // óklárað ! !
        Date arrival = reservation.getArrival();
        Date departure = reservation.getDeparture();
        int hotelRoomId = reservation.getHotelType().getId();
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
     * Skilar fjölda herbergja sem eru laus nóttina eftir {@code dayBefore}.
     *
     * @param dayBefore dagur fyrir nóttina sem er athuguð
     * @param hotelRoomId auðkenni hotelherbergistegundar
     * @return fjöldi herbergja sem eru laus
     */

    private int howManyAvailable(Date dayBefore, int hotelRoomId) throws SQLException {
        PreparedStatement statement1 = connection.prepareStatement("SELECT SUM(r.number_of_rooms) AS booked_rooms FROM reservations r\n" +
                "JOIN hotel_rooms hr ON r.hotel_rooms_id = hr.id WHERE hr.id = ?\n" +
                "AND (arrival_date <= ? AND departure_date > ?)\n");
        statement1.setInt(1, hotelRoomId);
        statement1.setDate(2, dayBefore);
        statement1.setDate(3, dayBefore);
        Occupied = statement1.executeQuery();

        PreparedStatement statement2 = connection.prepareStatement("SELECT capacity from hotel_rooms where id = ?");
        ResultSet Capacity = statement2.executeQuery();


        return Capacity - Occupied;

        throw new UnsupportedOperationException();

    }


        public boolean reserveRooms(Reservation reservation) { // óklárað ! !
        throw new UnsupportedOperationException();
    }

    public List<Reservation> getReservations(Hotel hotel) { // óklárað ! !
        throw new UnsupportedOperationException();
    }
}
