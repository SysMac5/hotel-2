package hi.throunhugbunadar.backend;

import java.sql.Date;

/**
 * Bókun á herbergi.
 */

public class Reservation {
    private User user;
    private HotelRooms hotelType;
    private Date arrival;
    private Date departure;

    private int numberOfRooms;

    public User getUser() {
        return user;
    }

    public HotelRooms getHotelType() {
        return hotelType;
    }

    public Date getArrival() {
        return arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public int getNumberOfRooms() { return numberOfRooms; }
}
