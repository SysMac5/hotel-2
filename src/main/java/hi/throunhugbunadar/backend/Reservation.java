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
    private final int numberOfRooms;

    /**
     * Smiður fyrir bókunina.
     *
     * @param user notandinn skrifaður fyrir bókun
     * @param hotelRooms tegund af hótelherbergis
     * @param hotel hótelið
     * @param arrival komudagur
     * @param departure brottfarardagur
     * @param numberOfRooms fjöldi herbergja bókuð
     */
    public Reservation(User user, HotelRooms hotelRooms, Hotel hotel, Date arrival, Date departure, int numberOfRooms) {
        this.user = user;
        this.hotelRooms = hotelRooms;
        this.hotel = hotel;
        this.arrival = arrival;
        this.departure = departure;
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Skilar notanda sem er skrifaður fyrir bókun.
     *
     * @return notandi
     */
    public User getUser() {
        return user;
    }

    /**
     * Skilar hótelherbergjategund sem er bókuð.
     *
     * @return hótelherbergjabókun
     */
    public HotelRooms getHotelRooms() {
        return hotelRooms;
    }

    /**
     * Skilar hóteli sem er bókað hjá.
     *
     * @return hótelið
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Skilar fyrsta degi bókunar.
     *
     * @return komudagur
     */
    public Date getArrival() {
        return arrival;
    }

    /**
     * Skilar síðasta degi bókunar.
     *
     * @return brottfarardagur
     */
    public Date getDeparture() {
        return departure;
    }

    /**
     * Skilar fjölda herbergja sem eru bókuð.
     *
     * @return fjöldi herbergja
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }
}
