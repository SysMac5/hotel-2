package hi.throunhugbunadar.backend;

import java.sql.Date;

/**
 * Bókun á herbergi.
 */

public class Reservation {
    private final User user;
    private final HotelRooms hotelRooms;
    private final Hotel hotel;
    private final Date arrival;
    private final Date departure;

    private int numberOfRooms;

    public Reservation(User user, HotelRooms hotelRooms, Hotel hotel, Date arrival, Date departure) {
        this.user = user;
        this.hotelRooms = hotelRooms;
        this.hotel = hotel;
        this.arrival = arrival;
        this.departure = departure;
    }

    public User getUser() {
        return user;
    }

    public HotelRooms getHotelRooms() {
        return hotelRooms;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Date getArrival() {
        return arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public int getNumberOfRooms() { return numberOfRooms; }
}
