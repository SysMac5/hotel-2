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

    public Reservation(User user, HotelRooms hotelType, Date arrival, Date departure) {
        this.user = user;
        this.hotelType = hotelType;
        this.arrival = arrival;
        this.departure = departure;
    }

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
